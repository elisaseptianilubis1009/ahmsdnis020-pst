/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app020.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.ahm.jx.email.service.EmailService;
import id.co.ahm.jx.sms.service.SmsService;
import id.co.ahm.jx.sys.app000.service.AhmmoerpTxnrunnumsService;
import id.co.ahm.jx.uam.app000.dao.AhmmoerpHdrsettingsDao;
import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.sd.nis.app020.dao.impl.Nis020AhmsdnisTxnrmndpicsDaoImpl;
import id.co.ahm.sd.nis.app020.vo.Nis020VoEmailAhmsdnisTxndlvtmsts;
import id.co.ahm.sd.nis.app020.vo.Nis020VoEmailDetil;
import id.co.ahm.sd.nis.app020.vo.Nis020VoTable;
import id.co.ahm.sd.nis.app020.service.Nis020Service;

/**
 *
 * @author septirio
 */
@Service("nis020Service")
@Transactional(readOnly = false)
public class Nis020ServiceImpl implements Nis020Service {

    @Autowired
    private AhmmoerpHdrsettingsDao ahmmoerpHdrsettingsDao;

    @Autowired
    @Qualifier("ahmmoerpTxnrunnumsService")
    private AhmmoerpTxnrunnumsService ahmmoerpTxnrunnumsService;

    @Autowired
    private Nis020AhmsdnisTxnrmndpicsDaoImpl nis016AhmsdnisTxnrmndpicDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("smsService")
    private SmsService smsService;

    @Override
    public String getCronJob() {
        AhmmoerpDtlsettings settings = ahmmoerpHdrsettingsDao.retrieveDetailSetting("SDEML", "JOB-1");
        if (settings != null) {
            return settings.getVitemname();
        }
        return null;
    }

    @Override
    public DtoResponse kirimEmail() {
        Class clazz = Nis020VoEmailAhmsdnisTxndlvtmsts.class;
        DtoResponse resp = new DtoResponse();
        DtoParamPaging param = new DtoParamPaging();

        Map<String, Object> msg = new HashMap<>();

        Calendar now = Calendar.getInstance();
        List<Object> lobj = new ArrayList<>();
        Nis020VoTable nvps = nis016AhmsdnisTxnrmndpicDao.retrieve(param, clazz);
        List list = nvps.getList();
        if (!list.isEmpty()) {

            for (Object object : list) {
                Nis020VoEmailAhmsdnisTxndlvtmsts x = (Nis020VoEmailAhmsdnisTxndlvtmsts) object;
                Date d4 = getFormulaInterval(x, x.getNinterval4());
                Date d3 = getFormulaInterval(x, x.getNinterval3());
                Date d2 = getFormulaInterval(x, x.getInterval2());
                Date d1 = getFormulaInterval(x, x.getNinterval1());

                x.setDinterval1(d1);
                x.setDinterval2(d2);
                x.setDinterval3(d3);
                x.setDinterval4(d4);

                if (isEqualDate(now, d1) || isEqualDate(now, d2) || isEqualDate(now, d3) || isEqualDate(now, d4)) {

                    DtoParamPaging paramdetil = new DtoParamPaging();
                    Map<String, Object> searchMap = new HashMap<>();
                    searchMap.put("vperiodupld", x.getVperiodupld());
                    searchMap.put("vlistupld", x.getVlistupld());
                    paramdetil.setSearch(searchMap);
                    Nis020VoTable detil = nis016AhmsdnisTxnrmndpicDao.retrieve(paramdetil, Nis020VoEmailDetil.class);
                    sendMail(detil);
                    lobj.addAll(detil.getList());
                }

            }

        }
        nvps.setList(lobj);
        nvps.setTotal(lobj.size());

        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }

    private void sendMail(Nis020VoTable detil) {
        List list = detil.getList();
        for (Object object : list) {
            Nis020VoEmailDetil x = (Nis020VoEmailDetil) object;

            String emailTo = x.getVusermail();
            String reply=emailService.callProcSendMail(x.getVmailsubj(), "noreply@astra-honda.com", emailTo, null, x.getVmaildesc());
            System.out.println("Reply from email procedure:================================ " + reply);

        }

    }

    private boolean isEqualDate(Calendar now, Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        if (now.get(Calendar.YEAR) == c.get(Calendar.YEAR)
                && now.get(Calendar.MONTH) == c.get(Calendar.MONTH)
                && now.get(Calendar.DATE) == c.get(Calendar.DATE)) {
            return true;
        }

        return false;
    }

    private Date getFormulaInterval(Nis020VoEmailAhmsdnisTxndlvtmsts x, BigDecimal interval) {
        Calendar now = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();

        cal.set(now.get(Calendar.YEAR), x.getNmonth().intValue(), x.getNdate().intValue() + interval.intValue());

        return cal.getTime();
    }

    public SmsService getSmsService() {
        return smsService;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

}
