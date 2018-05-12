package com.auth.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*<?xml version="1.0" encoding="UTF-8"?>
 <OTPResp xmlns="http://cca.gov.in/2016/06/esign#" resCode="d309c4bacfb92019888f35260231ef01" status="1" ts="2016-08-31T17:49:54.489+05:30" txn="AuthDemoClient:AUANSDL001:20160831174705614">
 <AadhaarResp>PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPE90cFJlcyB0eG49IkF1dGhEZW1vQ2xpZW50OkFVQU5TREwwMDE6MjAxNjA4MzExNzQ3MDU2MTQiIGNvZGU9IjNmOWIyODEzOTlmMzQwMzhhZTlmOTQ2ODU5MWNjZDMyIiB0cz0iMjAxNi0wOC0zMVQxNzo0OTo1NC40ODkrMDU6MzAiIHJldD0ieSIvPg==</AadhaarResp>
 <Signature xmlns="http://www.w3.org/2000/09/xmldsig#">
 <SignedInfo>
 <CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"/>
 <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
 <Reference URI="">
 <Transforms>
 <Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
 </Transforms>
 <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
 <DigestValue>H8XHeIfx/DM/Vfy8s9wqOIg8Yog=</DigestValue>
 </Reference>
 </SignedInfo>
 <SignatureValue>xvk4wnso+RwUbtf/8tnzUGF2zIUYO+UXK6VlxsmBMubolqsLE6WOoKddiCM2S651KJ0DvDDWbhrR
 dQJ2dUSyIjiQn0bTx+OfjFA/D+5N20ZG/qfJO5wZj6gJ+c3W/gBiPeTv1hJdigXD0HgzWP8C2ZoA
 MnOu0q+UnWH6wr2LypGVvOALT7uJ30HW3pCj5hNKhOsXkQINMm3wBORRnMJ+sbu8Io71Z8JjATpg
 2D/d41wiMne8Ywf5oB2unrRuP0b7RMBQh3ufoZqVjd8S8gu91eeF48yXjE9qldz7UpBEFezQb2Fj
 okZqdtnuR69KoJHhUrTHSIOUlO+Cuf8IoycXMg==</SignatureValue>
 <KeyInfo>
 <X509Data>
 <X509SubjectName>CN=neXus eSign Provider,O=Technology Nexus AB,C=IN</X509SubjectName>
 <X509Certificate>MIIDSzCCAjOgAwIBAgICJyowDQYJKoZIhvcNAQELBQAwSzELMAkGA1UEBhMCSU4xHDAaBgNVBAoT
 E1RlY2hub2xvZ3kgTmV4dXMgQUIxHjAcBgNVBAMTFU9mZmljZXIgYW5kIHN5c3RlbSBDQTAeFw0x
 NTA4MDcwNTE1MjZaFw0xNzA4MDcwNTE1MjZaMEoxCzAJBgNVBAYTAklOMRwwGgYDVQQKExNUZWNo
 bm9sb2d5IE5leHVzIEFCMR0wGwYDVQQDExRuZVh1cyBlU2lnbiBQcm92aWRlcjCCASIwDQYJKoZI
 hvcNAQEBBQADggEPADCCAQoCggEBANsF2Z7lG9cs5rBxoqrspUULmxzJCHqdw4/az1TlvgvZsVDS
 AYiPP7xAWMeisYCOAVYjJq2xEAR3Y3UkBnj31+pTgTKCvgRFU/1bWvxo6H7+qkykuO506bKy3E/H
 aXUtUCEylWSb3LI/AyCmnVQ/sQelFKnp91tsJzlcv+3bJcYcyv2jVmV/+h5BMC/x7EugsV4Lfcd0
 Q1m7u38aUVA2L57yKN2VQFaMLOyjoEz6sjilZ1VvG4WWhIv4vZ27vGuPSIfWmg8gkhXxzfhSlonT
 ehangOLOzVx+Ow6KIZbWD1ZGt1ujlfC0nt+3XSMIL/y2yk68bjZZw3DD38BQYX94k2kCAwEAAaM6
 MDgwEQYDVR0OBAoECEipSQfyvOClMBMGA1UdIwQMMAqACEdcWBbGA+TMMA4GA1UdDwEB/wQEAwIH
 gDANBgkqhkiG9w0BAQsFAAOCAQEAvtXMr2P4uOnyG1WyNIre5pZymEN87FV/H/5DwzjVtlt8/0AU
 yVdC/zeNgj0WhQq4O7Ls2kH56UwH7g2ijIET1YbFRAS5weM+eE2R1TxZgrpwwXE3gF8zYL3aB0Wd
 SychF2Q15EbVYQsusZ8zzavERm0o1XKQej51KnumPYzeafLN1YAX5Yz+k1hv6dXalbNVkt6MkQVm
 6LuVs1S2lnJCYVq+X2ny1wo3JUN7Bhw729XQrxuq/NE3kc+6dI7M/uViQBbvkwg7oNxScpLdlPA2
 OYpZoezRg2ep/TD6gYIytww64wf50jbgrg2VrL6d8eqy1Y4/dnz7qkGj2B0C64oJHQ==</X509Certificate>
 </X509Data>
 </KeyInfo>
 </Signature>
 </OTPResp>
 */

@XmlRootElement(name = "OTPResp")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"aadhaarResp", "signature" })
public class OTPResp {
	@XmlElement(name = "AadhaarResp", required = true)
	protected String aadhaarResp;
	@XmlElement(name = "Signature", required = true)
	protected String signature;
	@XmlAttribute(name = "resCode", required = true)
	protected String resCode;
	@XmlAttribute(name = "status", required = true)
	protected String status;
	@XmlAttribute(name = "ts", required = true)
	protected String ts;
	@XmlAttribute(name = "txn", required = true)
	protected String txn;
	@XmlAttribute(name = "errCode", required = true)
	protected String errCode;
	@XmlAttribute(name = "errMsg", required = true)
	protected String errMsg;
	
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAadhaarResp() {
		return aadhaarResp;
	}

	public void setAadhaarResp(String aadhaarResp) {
		this.aadhaarResp = aadhaarResp;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
