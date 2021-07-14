/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app020.rest;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoPstUserCred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import id.co.ahm.sd.nis.app020.service.Nis020Service;

/**
 *
 * @author septirio
 */
@Controller
@RequestMapping("/sd/nis020")
public class Nis020Rest {
    
    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;
    
    VoPstUserCred voUser = new VoPstUserCred();
    {
        voUser.setUserid("1234");
    }
    
    @Autowired
    @Qualifier(value = "nis020Service")
    private Nis020Service nis020Service;
    
    @RequestMapping(value = "sendnotif", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse createVaNumber(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
        nis020Service.kirimEmail();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
    }
    
}
