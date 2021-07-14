package id.co.ahm.sd.nis.app000.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AHMSDNIS_TXNRMNDPICS database table.
 * 
 */
@Embeddable
public class AhmsdnisTxnrmndpicsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private String vusercd;

	@Column(insertable=false, updatable=false)
	private String vperiodupld;

	@Column(insertable=false, updatable=false)
	private String vlistupld;

	public AhmsdnisTxnrmndpicsPK() {
	}
	public String getVusercd() {
		return this.vusercd;
	}
	public void setVusercd(String vusercd) {
		this.vusercd = vusercd;
	}
	public String getVperiodupld() {
		return this.vperiodupld;
	}
	public void setVperiodupld(String vperiodupld) {
		this.vperiodupld = vperiodupld;
	}
	public String getVlistupld() {
		return this.vlistupld;
	}
	public void setVlistupld(String vlistupld) {
		this.vlistupld = vlistupld;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AhmsdnisTxnrmndpicsPK)) {
			return false;
		}
		AhmsdnisTxnrmndpicsPK castOther = (AhmsdnisTxnrmndpicsPK)other;
		return 
			this.vusercd.equals(castOther.vusercd)
			&& this.vperiodupld.equals(castOther.vperiodupld)
			&& this.vlistupld.equals(castOther.vlistupld);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vusercd.hashCode();
		hash = hash * prime + this.vperiodupld.hashCode();
		hash = hash * prime + this.vlistupld.hashCode();
		
		return hash;
	}
}