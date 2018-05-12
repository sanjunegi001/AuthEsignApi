
package com.auth.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*<?xml version="1.0" encoding="UTF-8" standalone="no"?><OTP xmlns="http://cca.gov.in/2016/06/esign#" aspId="nxASPdEm0" ts="2016-08-31T10:40:31" txn="eSign:33455:20160817025131696" uid="770471097560" ver="1.6">
 <Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/><Reference URI=""><Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/><DigestValue>Ew3/XcW7Wd1+3xh73p3lzN+ubpA=</DigestValue></Reference></SignedInfo><SignatureValue>ClXKTlMHSdzZEqx1ecNs8j794fiEMsl6WhUon8Fl/vBexyeRB3v+QE2FD/1cDmB47fwd8Uel4AEN
 2cz17lnph0V3rv5Qp36ODAyx267piMnOvzP1x/hj9sBAO3pPrq7pf58gKDtexWcMLF7HU9KOkOYe
 UrvmcoS4Rvxqopa9aKel9dVp2hAin9sQeybeTUe+0tCDMRZuef/dEiCLknAsSY4aJx2Xs/E8T1kb
 m/FSYgcpsEM8dTjacuuJXyEcBKyfhIRiWoY5upkZO1p+RaHel0TJXp/MXw5QjW44oFm/Voqn94cL
 XNAZpDgNw39ZNGRquTZF90mSfpea/0Cr9NTmeQ==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=neXus Application Service Provider,O=Technology Nexus AB,C=IN</X509SubjectName><X509Certificate>MIIDWTCCAkGgAwIBAgICJyswDQYJKoZIhvcNAQELBQAwSzELMAkGA1UEBhMCSU4xHDAaBgNVBAoT
 E1RlY2hub2xvZ3kgTmV4dXMgQUIxHjAcBgNVBAMTFU9mZmljZXIgYW5kIHN5c3RlbSBDQTAeFw0x
 NTA4MDcwNTE3MzdaFw0xNzA4MDcwNTE3MzdaMFgxCzAJBgNVBAYTAklOMRwwGgYDVQQKExNUZWNo
 bm9sb2d5IE5leHVzIEFCMSswKQYDVQQDEyJuZVh1cyBBcHBsaWNhdGlvbiBTZXJ2aWNlIFByb3Zp
 ZGVyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkdorLj4Ns/t0Ape70qf7LFoP3odA
 t6PHak+0ZDKeZb5G00ZVZ6Ht4h2xuyfWjL5kAGU9PuNUfKHEp6dREcv0DyZNkxQeUhpOdjvZm5op
 I0P5Mtqvu6V5oNAQFYw33vXPg2DkHHuyG4QPJcqCXOTBB6sZg+uJRnlmx0aZGwyUpGDbkZF7Kkkf
 cb/fbcSVIgWdPVDd1+s/0qVyCd0n2eWowUkucl21pJsUwDdlnSu4AJkDYzZpOwq0jd7y7yrNIBOu
 K3lPz0ZzEY2vw2pe6J0kFwUz6lJaAITrrL1bgIPZTCk8tjsBnf+Vp+fzH1zyLQWPchcj4OdN7iUv
 KUDOkK8gnQIDAQABozowODARBgNVHQ4ECgQISM1YlmhJjsowEwYDVR0jBAwwCoAIR1xYFsYD5Mww
 DgYDVR0PAQH/BAQDAgeAMA0GCSqGSIb3DQEBCwUAA4IBAQCujcOjG4CxPpKmcu22O2MdoRgQ+aUg
 iq76n0yS+HxiHo1odXY/Z1lH7d8sI7vk3jdeS4osEL4jKTIfMV7jDtX8eykGGMyZjxWLv51d81/p
 SO1CXB149PnqmE6JYYmPGTGtQfUhdFOykussFNBV2BLOD6muiP28PpMgvb7b6KMsBUuQYpuS6TwU
 LM0THQgJtttJQPns1X90JUTx6/MvLUxV+Hgr/SnMOTpUwagxD7M3NVwUhQ3mhXFMyP9K+2TdfaAZ
 eatiQ5yefE6Arh0GKI68K/XRsoSQwgk+rMmvikksf/aSmqa4ReFoVwcKtuGt8rjJok/yQv0popfr
 x6JQqAFB</X509Certificate></X509Data></KeyInfo></Signature></OTP>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "signature" })
@XmlRootElement(name="OTP")
public class OTP {
	@XmlElement(name = "Signature", required = true)
	protected String signature;
	@XmlAttribute(name="xmlns",required = true)
	protected String xmlns;
	@XmlAttribute(name="aspId",required = true)
	protected String aspId;
	@XmlAttribute(name="ts",required = true)
	protected String ts;
	@XmlAttribute(name="txn",required = true)
	protected String txn;
	@XmlAttribute(name="uid",required = true)
	protected String uid;
	@XmlAttribute(name="ver",required = true)
	protected String ver;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public String getAspId() {
		return aspId;
	}

	public void setAspId(String aspId) {
		this.aspId = aspId;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

}
