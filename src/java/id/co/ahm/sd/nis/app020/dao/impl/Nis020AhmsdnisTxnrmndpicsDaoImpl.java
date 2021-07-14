package id.co.ahm.sd.nis.app020.dao.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnrmndpics;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnrmndpicsPK;
import id.co.ahm.sd.nis.app020.util.Nis020GeonetBackendUtil;
import id.co.ahm.sd.nis.app020.vo.Nis020VoGeonetBackendUtil;
import id.co.ahm.sd.nis.app020.vo.Nis020VoTable;
import id.co.ahm.sd.nis.app020.dao.Nis020AhmsdnisTxnrmndpicsDao;

@Repository("nis020AhmsdnisTxnrmndpicDao")
public class Nis020AhmsdnisTxnrmndpicsDaoImpl extends DefaultHibernateDao<AhmsdnisTxnrmndpics, AhmsdnisTxnrmndpicsPK>
        implements Nis020AhmsdnisTxnrmndpicsDao {

    @Override
    public Nis020VoTable retrieve(DtoParamPaging input, Class clazz) {
        Nis020VoGeonetBackendUtil vo = Nis020GeonetBackendUtil.retrieveObj(getCurrentSession(), input, true, false,
                clazz, null);

        int index = 0;
        if (input != null) {
            index = input.getOffset() + 1;
        }
        List<Object[]> list = vo.getList();
        List<Object> lobj = new ArrayList<>();

        for (Object[] val : list) {
            try {
                Constructor construct = clazz.getConstructor(Integer.class, Object[].class);
                Object newInstance = construct.newInstance(index, val);
                lobj.add(newInstance);
                index++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new Nis020VoTable(vo.getTotal(), lobj);

    }

}
