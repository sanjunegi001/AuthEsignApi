/*This Class is used for setter and getter method
 * For Esign
 */
package com.auth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Esign.
 */
@Entity
@Table(name = "ab_esigning_details")
public class Esign {

	/** The id. */
	@Id
	@SequenceGenerator(name = "seq_verification", sequenceName = "seq_verification")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_verification")
	private int id;

	/** The esign status. */
	@Column
	private int esign_status;

	/** The esign file path. */
	@Column
	private String esign_file_path;

	/** The esign reasoncode. */
	@Column
	private String esign_reasoncode;

	/** The esign reason. */
	@Column
	private String esign_reason;

	/** The esign transaction id. */
	@Column
	private String esign_transaction_id;

	/** The esign timestamp. */
	@Column
	private String esign_timestamp;

	/** The esign request on. */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date esign_request_on;

	/** The esign response on. */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date esign_response_on;

	/** The esign request by. */
	@Column
	private String esign_request_by;

	/** The esign auth type. */
	@Column
	private String esign_auth_type;

	/** The esign ip address. */
	@Column
	private String esign_ip_address;

	/** The esign env. */
	@Column
	private String esign_env;

	/** The esign aadhaar. */
	@Column
	private String esign_aadhaar;
	
	/** The esign aspid. */
	@Column
	private String esign_aspid;
	
	/** The esign api. */
	@Column
	private String esign_api;

	/**
	 * Instantiates a new esign.
	 */
	public Esign() {

	}

	/**
	 * Instantiates a new esign.
	 *
	 * @param id
	 *            the id
	 * @param esign_status
	 *            the esign status
	 * @param esign_file_path
	 *            the esign file path
	 * @param esign_reasoncode
	 *            the esign reasoncode
	 * @param esign_reason
	 *            the esign reason
	 * @param esign_transaction_id
	 *            the esign transaction id
	 * @param esign_timestamp
	 *            the esign timestamp
	 * @param esign_request_on
	 *            the esign request on
	 * @param esign_response_on
	 *            the esign response on
	 * @param esign_request_by
	 *            the esign request by
	 * @param esign_auth_type
	 *            the esign auth type
	 * @param esign_ip_address
	 *            the esign ip address
	 * @param esign_env
	 *            the esign env
	 * @param esign_aadhaar
	 *            the esign aadhaar
	 */
	public Esign(int id, int esign_status, String esign_file_path, String esign_reasoncode, String esign_reason,
			String esign_transaction_id, String esign_timestamp, Date esign_request_on, Date esign_response_on,
			String esign_request_by, String esign_auth_type, String esign_ip_address, String esign_env,
			String esign_aadhaar,String aspid,String esign_api) {
		super();
		this.esign_status = esign_status;
		this.esign_file_path = esign_file_path;
		this.esign_reasoncode = esign_reasoncode;
		this.esign_reason = esign_reason;
		this.esign_transaction_id = esign_transaction_id;
		this.esign_timestamp = esign_timestamp;
		this.esign_request_on = esign_request_on;
		this.esign_response_on = esign_response_on;
		this.esign_request_by = esign_request_by;
		this.esign_auth_type = esign_auth_type;
		this.esign_ip_address = esign_ip_address;
		this.esign_env = esign_env;
		this.esign_aadhaar = esign_aadhaar;
		this.esign_aspid=aspid;
		this.esign_api=esign_api;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the esign status.
	 *
	 * @return the esign status
	 */
	public int getEsign_status() {
		return esign_status;
	}

	/**
	 * Sets the esign status.
	 *
	 * @param esign_status
	 *            the new esign status
	 */
	public void setEsign_status(int esign_status) {
		this.esign_status = esign_status;
	}

	/**
	 * Gets the esign file path.
	 *
	 * @return the esign file path
	 */
	public String getEsign_file_path() {
		return esign_file_path;
	}

	/**
	 * Sets the esign file path.
	 *
	 * @param esign_file_path
	 *            the new esign file path
	 */
	public void setEsign_file_path(String esign_file_path) {
		this.esign_file_path = esign_file_path;
	}

	/**
	 * Gets the esign reasoncode.
	 *
	 * @return the esign reasoncode
	 */
	public String getEsign_reasoncode() {
		return esign_reasoncode;
	}

	/**
	 * Sets the esign reasoncode.
	 *
	 * @param esign_reasoncode
	 *            the new esign reasoncode
	 */
	public void setEsign_reasoncode(String esign_reasoncode) {
		this.esign_reasoncode = esign_reasoncode;
	}

	/**
	 * Gets the esign reason.
	 *
	 * @return the esign reason
	 */
	public String getEsign_reason() {
		return esign_reason;
	}

	/**
	 * Sets the esign reason.
	 *
	 * @param esign_reason
	 *            the new esign reason
	 */
	public void setEsign_reason(String esign_reason) {
		this.esign_reason = esign_reason;
	}

	/**
	 * Gets the esign transaction id.
	 *
	 * @return the esign transaction id
	 */
	public String getEsign_transaction_id() {
		return esign_transaction_id;
	}

	/**
	 * Sets the esign transaction id.
	 *
	 * @param esign_transaction_id
	 *            the new esign transaction id
	 */
	public void setEsign_transaction_id(String esign_transaction_id) {
		this.esign_transaction_id = esign_transaction_id;
	}

	/**
	 * Gets the esign timestamp.
	 *
	 * @return the esign timestamp
	 */
	public String getEsign_timestamp() {
		return esign_timestamp;
	}

	/**
	 * Sets the esign timestamp.
	 *
	 * @param esign_timestamp
	 *            the new esign timestamp
	 */
	public void setEsign_timestamp(String esign_timestamp) {
		this.esign_timestamp = esign_timestamp;
	}

	/**
	 * Gets the esign request on.
	 *
	 * @return the esign request on
	 */
	public Date getEsign_request_on() {
		return esign_request_on;
	}

	/**
	 * Sets the esign request on.
	 *
	 * @param esign_request_on
	 *            the new esign request on
	 */
	public void setEsign_request_on(Date esign_request_on) {
		this.esign_request_on = esign_request_on;
	}

	/**
	 * Gets the esign response on.
	 *
	 * @return the esign response on
	 */
	public Date getEsign_response_on() {
		return esign_response_on;
	}

	/**
	 * Sets the esign response on.
	 *
	 * @param esign_response_on
	 *            the new esign response on
	 */
	public void setEsign_response_on(Date esign_response_on) {
		this.esign_response_on = esign_response_on;
	}

	/**
	 * Gets the esign request by.
	 *
	 * @return the esign request by
	 */
	public String getEsign_request_by() {
		return esign_request_by;
	}

	/**
	 * Sets the esign request by.
	 *
	 * @param esign_request_by
	 *            the new esign request by
	 */
	public void setEsign_request_by(String esign_request_by) {
		this.esign_request_by = esign_request_by;
	}

	/**
	 * Gets the esign auth type.
	 *
	 * @return the esign auth type
	 */
	public String getEsign_auth_type() {
		return esign_auth_type;
	}

	/**
	 * Sets the esign auth type.
	 *
	 * @param esign_auth_type
	 *            the new esign auth type
	 */
	public void setEsign_auth_type(String esign_auth_type) {
		this.esign_auth_type = esign_auth_type;
	}

	/**
	 * Gets the esign ip address.
	 *
	 * @return the esign ip address
	 */
	public String getEsign_ip_address() {
		return esign_ip_address;
	}

	/**
	 * Sets the esign ip address.
	 *
	 * @param esign_ip_address
	 *            the new esign ip address
	 */
	public void setEsign_ip_address(String esign_ip_address) {
		this.esign_ip_address = esign_ip_address;
	}

	/**
	 * Gets the esign env.
	 *
	 * @return the esign env
	 */
	public String getEsign_env() {
		return esign_env;
	}

	/**
	 * Sets the esign env.
	 *
	 * @param esign_env
	 *            the new esign env
	 */
	public void setEsign_env(String esign_env) {
		this.esign_env = esign_env;
	}

	/**
	 * Gets the esign aadhaar.
	 *
	 * @return the esign aadhaar
	 */
	public String getEsign_aadhaar() {
		return esign_aadhaar;
	}

	/**
	 * Sets the esign aadhaar.
	 *
	 * @param esign_aadhaar
	 *            the new esign aadhaar
	 */
	public void setEsign_aadhaar(String esign_aadhaar) {
		this.esign_aadhaar = esign_aadhaar;
	}

	public String getEsign_aspid() {
		return esign_aspid;
	}

	public void setEsign_aspid(String esign_aspid) {
		this.esign_aspid = esign_aspid;
	}

	public String getEsign_api() {
		return esign_api;
	}

	public void setEsign_api(String esign_api) {
		this.esign_api = esign_api;
	}

	
	
}
