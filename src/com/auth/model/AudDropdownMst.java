/**
 * AudDropdownMst.java
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
@Table(name="cqtpub.aud_dropdown_mst")
public class AudDropdownMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="adm_pmk")
	private Integer admPmk;

	@Column(name="adm_active_flag")
	private String admActiveFlag;

	@Column(name="adm_aud_crtn_tmstmp")
	private Timestamp admAudCrtnTmstmp;

	@Column(name="adm_crtn_ip_address")
	private String admCrtnIpAddress;

	@Column(name="adm_crtn_tmstmp")
	private Timestamp admCrtnTmstmp;

	@Column(name="adm_crtn_user")
	private String admCrtnUser;

	@Column(name="adm_dropdown_nm")
	private String admDropdownNm;

	@Column(name="adm_mod_ip_address")
	private String admModIpAddress;

	@Column(name="adm_mod_tmstmp")
	private Timestamp admModTmstmp;

	@Column(name="adm_mod_user")
	private String admModUser;

	@Column(name="adm_old_value")
	private String admOldValue;

	@Column(name="adm_seq_no")
	private Integer admSeqNo;

	@Column(name="adm_value")
	private String admValue;

	public AudDropdownMst() {
	}

	public Integer getAdmPmk() {
		return this.admPmk;
	}

	public void setAdmPmk(Integer admPmk) {
		this.admPmk = admPmk;
	}

	public String getAdmActiveFlag() {
		return this.admActiveFlag;
	}

	public void setAdmActiveFlag(String admActiveFlag) {
		this.admActiveFlag = admActiveFlag;
	}

	public Timestamp getAdmAudCrtnTmstmp() {
		return this.admAudCrtnTmstmp;
	}

	public void setAdmAudCrtnTmstmp(Timestamp admAudCrtnTmstmp) {
		this.admAudCrtnTmstmp = admAudCrtnTmstmp;
	}

	public String getAdmCrtnIpAddress() {
		return this.admCrtnIpAddress;
	}

	public void setAdmCrtnIpAddress(String admCrtnIpAddress) {
		this.admCrtnIpAddress = admCrtnIpAddress;
	}

	public Timestamp getAdmCrtnTmstmp() {
		return this.admCrtnTmstmp;
	}

	public void setAdmCrtnTmstmp(Timestamp admCrtnTmstmp) {
		this.admCrtnTmstmp = admCrtnTmstmp;
	}

	public String getAdmCrtnUser() {
		return this.admCrtnUser;
	}

	public void setAdmCrtnUser(String admCrtnUser) {
		this.admCrtnUser = admCrtnUser;
	}

	public String getAdmDropdownNm() {
		return this.admDropdownNm;
	}

	public void setAdmDropdownNm(String admDropdownNm) {
		this.admDropdownNm = admDropdownNm;
	}

	public String getAdmModIpAddress() {
		return this.admModIpAddress;
	}

	public void setAdmModIpAddress(String admModIpAddress) {
		this.admModIpAddress = admModIpAddress;
	}

	public Timestamp getAdmModTmstmp() {
		return this.admModTmstmp;
	}

	public void setAdmModTmstmp(Timestamp admModTmstmp) {
		this.admModTmstmp = admModTmstmp;
	}

	public String getAdmModUser() {
		return this.admModUser;
	}

	public void setAdmModUser(String admModUser) {
		this.admModUser = admModUser;
	}

	public String getAdmOldValue() {
		return this.admOldValue;
	}

	public void setAdmOldValue(String admOldValue) {
		this.admOldValue = admOldValue;
	}

	public Integer getAdmSeqNo() {
		return this.admSeqNo;
	}

	public void setAdmSeqNo(Integer admSeqNo) {
		this.admSeqNo = admSeqNo;
	}

	public String getAdmValue() {
		return this.admValue;
	}

	public void setAdmValue(String admValue) {
		this.admValue = admValue;
	}

}