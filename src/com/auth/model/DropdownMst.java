/**
 * DropdownMst.java
 * PurnimaS
 * Aug 3, 2016
 */
package com.auth.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author PurnimaS
 *
 */
@Entity
@Table(name="cqtpub.dropdown_mst")
public class DropdownMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dm_pmk")
	private Integer dmPmk;

	@Column(name="dm_active_flag")
	private String dmActiveFlag;

	@Column(name="dm_crtn_ip")
	private String dmCrtnIp;

	@Column(name="dm_crtn_tmstmp")
	private Timestamp dmCrtnTmstmp;

	@Column(name="dm_crtn_user")
	private String dmCrtnUser;

	@Column(name="dm_dropdown_nm")
	private String dmDropdownNm;

	@Column(name="dm_mod_ip_address")
	private String dmModIpAddress;

	@Column(name="dm_mod_tmstmp")
	private Timestamp dmModTmstmp;

	@Column(name="dm_mod_user")
	private String dmModUser;

	@Column(name="dm_seq_no")
	private Integer dmSeqNo;

	@Column(name="dm_value")
	private String dmValue;

	public DropdownMst() {
	}

	public Integer getDmPmk() {
		return this.dmPmk;
	}

	public void setDmPmk(Integer dmPmk) {
		this.dmPmk = dmPmk;
	}

	public String getDmActiveFlag() {
		return this.dmActiveFlag;
	}

	public void setDmActiveFlag(String dmActiveFlag) {
		this.dmActiveFlag = dmActiveFlag;
	}

	public String getDmCrtnIp() {
		return this.dmCrtnIp;
	}

	public void setDmCrtnIp(String dmCrtnIp) {
		this.dmCrtnIp = dmCrtnIp;
	}

	public Timestamp getDmCrtnTmstmp() {
		return this.dmCrtnTmstmp;
	}

	public void setDmCrtnTmstmp(Timestamp dmCrtnTmstmp) {
		this.dmCrtnTmstmp = dmCrtnTmstmp;
	}

	public String getDmCrtnUser() {
		return this.dmCrtnUser;
	}

	public void setDmCrtnUser(String dmCrtnUser) {
		this.dmCrtnUser = dmCrtnUser;
	}

	public String getDmDropdownNm() {
		return this.dmDropdownNm;
	}

	public void setDmDropdownNm(String dmDropdownNm) {
		this.dmDropdownNm = dmDropdownNm;
	}

	public String getDmModIpAddress() {
		return this.dmModIpAddress;
	}

	public void setDmModIpAddress(String dmModIpAddress) {
		this.dmModIpAddress = dmModIpAddress;
	}

	public Timestamp getDmModTmstmp() {
		return this.dmModTmstmp;
	}

	public void setDmModTmstmp(Timestamp dmModTmstmp) {
		this.dmModTmstmp = dmModTmstmp;
	}

	public String getDmModUser() {
		return this.dmModUser;
	}

	public void setDmModUser(String dmModUser) {
		this.dmModUser = dmModUser;
	}

	public Integer getDmSeqNo() {
		return this.dmSeqNo;
	}

	public void setDmSeqNo(Integer dmSeqNo) {
		this.dmSeqNo = dmSeqNo;
	}

	public String getDmValue() {
		return this.dmValue;
	}

	public void setDmValue(String dmValue) {
		this.dmValue = dmValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dmActiveFlag == null) ? 0 : dmActiveFlag.hashCode());
		result = prime * result + ((dmCrtnIp == null) ? 0 : dmCrtnIp.hashCode());
		result = prime * result + ((dmCrtnTmstmp == null) ? 0 : dmCrtnTmstmp.hashCode());
		result = prime * result + ((dmCrtnUser == null) ? 0 : dmCrtnUser.hashCode());
		result = prime * result + ((dmDropdownNm == null) ? 0 : dmDropdownNm.hashCode());
		result = prime * result + ((dmModIpAddress == null) ? 0 : dmModIpAddress.hashCode());
		result = prime * result + ((dmModTmstmp == null) ? 0 : dmModTmstmp.hashCode());
		result = prime * result + ((dmModUser == null) ? 0 : dmModUser.hashCode());
		result = prime * result + ((dmPmk == null) ? 0 : dmPmk.hashCode());
		result = prime * result + ((dmSeqNo == null) ? 0 : dmSeqNo.hashCode());
		result = prime * result + ((dmValue == null) ? 0 : dmValue.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DropdownMst other = (DropdownMst) obj;
		if (dmActiveFlag == null) {
			if (other.dmActiveFlag != null)
				return false;
		} else if (!dmActiveFlag.equals(other.dmActiveFlag))
			return false;
		if (dmCrtnIp == null) {
			if (other.dmCrtnIp != null)
				return false;
		} else if (!dmCrtnIp.equals(other.dmCrtnIp))
			return false;
		if (dmCrtnTmstmp == null) {
			if (other.dmCrtnTmstmp != null)
				return false;
		} else if (!dmCrtnTmstmp.equals(other.dmCrtnTmstmp))
			return false;
		if (dmCrtnUser == null) {
			if (other.dmCrtnUser != null)
				return false;
		} else if (!dmCrtnUser.equals(other.dmCrtnUser))
			return false;
		if (dmDropdownNm == null) {
			if (other.dmDropdownNm != null)
				return false;
		} else if (!dmDropdownNm.equals(other.dmDropdownNm))
			return false;
		if (dmModIpAddress == null) {
			if (other.dmModIpAddress != null)
				return false;
		} else if (!dmModIpAddress.equals(other.dmModIpAddress))
			return false;
		if (dmModTmstmp == null) {
			if (other.dmModTmstmp != null)
				return false;
		} else if (!dmModTmstmp.equals(other.dmModTmstmp))
			return false;
		if (dmModUser == null) {
			if (other.dmModUser != null)
				return false;
		} else if (!dmModUser.equals(other.dmModUser))
			return false;
		if (dmPmk == null) {
			if (other.dmPmk != null)
				return false;
		} else if (!dmPmk.equals(other.dmPmk))
			return false;
		if (dmSeqNo == null) {
			if (other.dmSeqNo != null)
				return false;
		} else if (!dmSeqNo.equals(other.dmSeqNo))
			return false;
		if (dmValue == null) {
			if (other.dmValue != null)
				return false;
		} else if (!dmValue.equals(other.dmValue))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DropdownMst [dmPmk=" + dmPmk + ", dmActiveFlag=" + dmActiveFlag + ", dmCrtnIp=" + dmCrtnIp
				+ ", dmCrtnTmstmp=" + dmCrtnTmstmp + ", dmCrtnUser=" + dmCrtnUser + ", dmDropdownNm=" + dmDropdownNm
				+ ", dmModIpAddress=" + dmModIpAddress + ", dmModTmstmp=" + dmModTmstmp + ", dmModUser=" + dmModUser
				+ ", dmSeqNo=" + dmSeqNo + ", dmValue=" + dmValue + "]";
	}
	
}