package com.auth.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/*<Esign ver="" sc="" ts="" txn="" aspId="" esignClass=""
preferredCa="" gatewayPin="" responseSigType="" >
<Input>Document Hash in Hex</Input>
<Aadhaar>base-64 encoded Aadhaar Auth XML as per UIDAI specifications</Aadhaar>
<Signature>Digital signature of ASP</Signature>
</Esign>*/


/*1.This class is used for Conversion of
 * i)POJO To XML
 * ii)XML To POJO
 *2.Inside of @XmlRootElement calling the esignxml url
 *3.XML Element Map with class variables.
 *4.Generating Setter and getter method.
 * */


@XmlRootElement(name = "Esign",namespace="http://www.w3.org/2000/09/xmldsig#")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Esign", propOrder = {"input", "aadhaar","signature" })
public class Esign {
	@XmlElement(name = "Input", required = true)
	protected String input;
	@XmlElement(name = "Aadhaar", required = true)
	protected String aadhaar;
	@XmlElement(name = "Signature", required = true)
	protected String Signature;
	@XmlAttribute(name = "ver", required = true)
	protected String ver;
	@XmlAttribute(name = "sc", required = true)
	protected String sc;
	@XmlAttribute(name = "ts", required = true)
	protected String ts;
	@XmlAttribute(name = "txn", required = true)
	protected String txn;
	@XmlAttribute(name = "aspId", required = true)
	protected String aspId;
	@XmlAttribute(name = "esignClass", required = true)
	protected String esignClass;
	@XmlAttribute(name = "preferredCa", required = true)
	protected String preferredCa;
	@XmlAttribute(name = "gatewayPin", required = true)
	protected String gatewayPin;
	@XmlAttribute(name = "responseSigType", required = true)
	protected String responseSigType;
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getTxn() {
		return txn;
	}
	public void setTxn(String txn) {
		this.txn = txn;
	}
	public String getAspId() {
		return aspId;
	}
	public void setAspId(String aspId) {
		this.aspId = aspId;
	}
	public String getEsignClass() {
		return esignClass;
	}
	public void setEsignClass(String esignClass) {
		this.esignClass = esignClass;
	}
	public String getPreferredCa() {
		return preferredCa;
	}
	public void setPreferredCa(String preferredCa) {
		this.preferredCa = preferredCa;
	}
	public String getGatewayPin() {
		return gatewayPin;
	}
	public void setGatewayPin(String gatewayPin) {
		this.gatewayPin = gatewayPin;
	}
	public String getResponseSigType() {
		return responseSigType;
	}
	public void setResponseSigType(String responseSigType) {
		this.responseSigType = responseSigType;
	}
}
