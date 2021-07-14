package id.co.ahm.sd.nis.app000.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the AHMSDNIS_TXNRMNDPICS database table.
 * 
 */
@Entity
@Table(name="AHMSDNIS_TXNRMNDPICS")
@NamedQuery(name="AhmsdnisTxnrmndpics.findAll", query="SELECT a FROM AhmsdnisTxnrmndpics a")
public class AhmsdnisTxnrmndpics implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AhmsdnisTxnrmndpicsPK id;

	@Temporal(TemporalType.DATE)
	private Date dcrea;

	@Temporal(TemporalType.DATE)
	private Date dmodi;

	private String vcrea;

	private String vmodi;

	private String vusermail;

	public AhmsdnisTxnrmndpics() {
	}

	public void setAhmsdnisTxnrmndpicsPK(AhmsdnisTxnrmndpicsPK x) {
		this.id = x;
	}
	public AhmsdnisTxnrmndpicsPK getId() {
		return this.id;
	}

	public void setId(AhmsdnisTxnrmndpicsPK id) {
		this.id = id;
	}

	public Date getDcrea() {
		return this.dcrea;
	}

	public void setDcrea(Date dcrea) {
		this.dcrea = dcrea;
	}

	public Date getDmodi() {
		return this.dmodi;
	}

	public void setDmodi(Date dmodi) {
		this.dmodi = dmodi;
	}

	public String getVcrea() {
		return this.vcrea;
	}

	public void setVcrea(String vcrea) {
		this.vcrea = vcrea;
	}

	public String getVmodi() {
		return this.vmodi;
	}

	public void setVmodi(String vmodi) {
		this.vmodi = vmodi;
	}

	public String getVusermail() {
		return this.vusermail;
	}

	public void setVusermail(String vusermail) {
		this.vusermail = vusermail;
	}


}