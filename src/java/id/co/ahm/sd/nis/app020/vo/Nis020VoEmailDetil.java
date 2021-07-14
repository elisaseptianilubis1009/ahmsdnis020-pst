package id.co.ahm.sd.nis.app020.vo;

import java.io.Serializable;

import id.co.ahm.sd.nis.app020.util.Nis020GeonetHeader;
import id.co.ahm.sd.nis.app020.util.Nis020GeonetUtil;

/**
 * The persistent class for the AHMSDNIS_TXNDLVTMSTS database table.
 *
 */
@Nis020GeonetHeader(sqldefault = "SELECT "
        + "	DLV.VPERIODUPLD, "
        + "	PER.VPERIODE,"
        + "	DLV.VLISTUPLD,"
        + "	LIST.VREMINDATA,"
        + "	LIST.VMAILSUBJ,"
        + "	LIST.VMAILDESC,"
        + "	PIC.VUSERMAIL"
        + " FROM "
        + "	AHMSDNIS_TXNDLVTMSTS DLV "
        + " INNER JOIN "
        + "	AHMSDNIS_MSTPERUPLDS PER ON DLV.VPERIODUPLD = PER.VPERIODUPLD"
        + " INNER JOIN "
        + "	AHMSDNIS_MSTLISTUPLDS LIST ON DLV.VLISTUPLD = LIST.VLISTUPLD"
        + " INNER JOIN"
        + "	AHMSDNIS_TXNRMNDPICS PIC ON DLV.VLISTUPLD = PIC.VLISTUPLD"
        + " where 1=1 ", tableName = "AHMSDNIS_TXNDLVTMSTS")
public class Nis020VoEmailDetil implements Serializable {

    private static final long serialVersionUID = 6050579124990442141L;

    private Integer no;

    @Nis020GeonetUtil(isSearchField = true, query = " and lower(DLV.VPERIODUPLD) = :vperiodupld ")
    private String vperiodupld;

    private String vperiode;

    @Nis020GeonetUtil(isSearchField = true, query = " and lower(DLV.VLISTUPLD) = :vlistupld ")
    private String vlistupld;

    private String vremindata;

    private String vmailsubj;

    private String vmaildesc;

    private String vusermail;

    public Nis020VoEmailDetil(Integer no, Object[] val) {
        this.no = no;
        this.vperiodupld = (String) val[0];
        this.vperiode = (String) val[1];
        this.vlistupld = (String) val[2];
        this.vremindata = (String) val[3];
        this.vmailsubj = (String) val[4];
        this.vmaildesc = (String) val[5];
        this.vusermail = (String) val[6];

    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getVperiode() {
        return vperiode;
    }

    public void setVperiode(String vperiode) {
        this.vperiode = vperiode;
    }

    public String getVremindata() {
        return vremindata;
    }

    public void setVremindata(String vremindata) {
        this.vremindata = vremindata;
    }

    public String getVperiodupld() {
        return vperiodupld;
    }

    public void setVperiodupld(String vperiodupld) {
        this.vperiodupld = vperiodupld;
    }

    public String getVlistupld() {
        return vlistupld;
    }

    public void setVlistupld(String vlistupld) {
        this.vlistupld = vlistupld;
    }

    public Nis020VoEmailDetil() {
    }

    public String getVmaildesc() {
        return this.vmaildesc;
    }

    public void setVmaildesc(String vmaildesc) {
        this.vmaildesc = vmaildesc;
    }

    public String getVmailsubj() {
        return vmailsubj;
    }

    public void setVmailsubj(String vmailsubj) {
        this.vmailsubj = vmailsubj;
    }

    public String getVusermail() {
        return vusermail;
    }

    public void setVusermail(String vusermail) {
        this.vusermail = vusermail;
    }

}
