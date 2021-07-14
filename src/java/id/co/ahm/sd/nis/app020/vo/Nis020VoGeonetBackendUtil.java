package id.co.ahm.sd.nis.app020.vo;

import java.util.ArrayList;
import java.util.List;

public class Nis020VoGeonetBackendUtil {

    private List<Object[]> list = new ArrayList<Object[]>();

    private List<Object> listObj = new ArrayList<Object>();

    private Class clazz;

    private Integer total = 0;

    public List<Object> getListObj() {
        return listObj;
    }

    public void setListObj(List<Object> listObj) {
        this.listObj = listObj;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<Object[]> getList() {
        return list;
    }

    public void setList(List<Object[]> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
