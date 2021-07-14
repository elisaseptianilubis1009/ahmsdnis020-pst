package id.co.ahm.sd.nis.app020.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import id.co.ahm.sd.nis.app020.util.Nis020GeonetHeader;
import id.co.ahm.sd.nis.app020.util.Nis020GeonetUtil;

/**
 * The persistent class for the AHMSDNIS_TXNDLVTMSTS database table.
 *
 */
@Table(name = "AHMSDNIS_TXNDLVTMSTS")
@Nis020GeonetHeader(sqldefault = "SELECT  "
        + " DLV.VPERIODUPLD, "
        + "	PER.VPERIODE, "
        + "	DLV.VLISTUPLD, "
        + "	LIST.VREMINDATA, "
        + "	DLV.NDATE, "
        + "	DLV.NMONTH, "
        + "	DLV.NINTERVAL4, "
        + "	DLV.NINTERVAL3, "
        + "	DLV.INTERVAL2, "
        + "	DLV.NINTERVAL1, "
        + "	LIST.VMAILSUBJ, "
        + "	LIST.VMAILDESC "
        + " from AHMSDNIS_TXNDLVTMSTS DLV "
        + " INNER JOIN AHMSDNIS_MSTPERUPLDS PER ON DLV.VPERIODUPLD = PER.VPERIODUPLD "
        + " INNER JOIN  AHMSDNIS_MSTLISTUPLDS LIST ON DLV.VLISTUPLD = LIST.VLISTUPLD "
        + " where 1=1 ", tableName = "AHMSDNIS_TXNDLVTMSTS")
public class Nis020VoEmailAhmsdnisTxndlvtmsts implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer no;

    @Nis020GeonetUtil(isSearchField = true, query = " and (lower(DLV.VPERIODUPLD) = :vperiodupld  OR upper(DLV.VPERIODUPLD) = :vperiodupld) ")
    private String vperiodupld;
    
    private String vperiode;
    
    private String vlistupld;

    private String vremindata;
    
    private BigDecimal ndate;
    
    private BigDecimal nmonth;

    private BigDecimal ninterval4;
    
    private BigDecimal ninterval3;
    
    private BigDecimal interval2;

    private BigDecimal ninterval1;
 
    private String vmailsubj;
    
    private String vmaildesc;
    
    private Date dinterval4;
    
    private Date dinterval3;
    
    private Date dinterval2;

    private Date dinterval1;
   


    public Nis020VoEmailAhmsdnisTxndlvtmsts(Integer no, Object[] val) {
        this.no = no;
        this.vperiodupld = (String) val[0];
        this.vperiode = (String) val[1];
        this.vlistupld = (String) val[2];
        this.vremindata = (String) val[3];
        this.ndate = (BigDecimal) val[4];
        this.nmonth = (BigDecimal) val[5];
        this.ninterval4 = (BigDecimal) val[6];
        this.ninterval3 = (BigDecimal) val[7];
        this.interval2 = (BigDecimal) val[8];
        this.ninterval1 = (BigDecimal) val[9];
        this.vmailsubj = (String) val[10];
        this.vmaildesc = (String) val[11];

    }
    
    

   


	public Date getDinterval4() {
		return dinterval4;
	}






	public void setDinterval4(Date dinterval4) {
		this.dinterval4 = dinterval4;
	}






	public Date getDinterval3() {
		return dinterval3;
	}






	public void setDinterval3(Date dinterval3) {
		this.dinterval3 = dinterval3;
	}






	public Date getDinterval2() {
		return dinterval2;
	}






	public void setDinterval2(Date dinterval2) {
		this.dinterval2 = dinterval2;
	}






	public Date getDinterval1() {
		return dinterval1;
	}






	public void setDinterval1(Date dinterval1) {
		this.dinterval1 = dinterval1;
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

    public Nis020VoEmailAhmsdnisTxndlvtmsts() {
    }

    

    public BigDecimal getInterval2() {
        return this.interval2;
    }

    public void setInterval2(BigDecimal interval2) {
        this.interval2 = interval2;
    }

    public BigDecimal getNdate() {
        return this.ndate;
    }

    public void setNdate(BigDecimal ndate) {
        this.ndate = ndate;
    }

    public BigDecimal getNinterval1() {
        return this.ninterval1;
    }

    public void setNinterval1(BigDecimal ninterval1) {
        this.ninterval1 = ninterval1;
    }

    public BigDecimal getNinterval3() {
        return this.ninterval3;
    }

    public void setNinterval3(BigDecimal ninterval3) {
        this.ninterval3 = ninterval3;
    }

    public BigDecimal getNinterval4() {
        return this.ninterval4;
    }

    public void setNinterval4(BigDecimal ninterval4) {
        this.ninterval4 = ninterval4;
    }

    public BigDecimal getNmonth() {
        return nmonth;
    }

    public void setNmonth(BigDecimal nmonth) {
        this.nmonth = nmonth;
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


}
