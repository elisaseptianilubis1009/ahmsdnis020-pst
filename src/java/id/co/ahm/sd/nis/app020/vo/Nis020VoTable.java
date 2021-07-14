package id.co.ahm.sd.nis.app020.vo;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iwan
 */
public class Nis020VoTable<T> {

    private int total;
    private List<T> list;

    public Nis020VoTable() {
        total = 0;
        list = new ArrayList<>();
    }

    public Nis020VoTable(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
