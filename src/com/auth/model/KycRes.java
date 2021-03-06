//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.03 at 04:17:43 PM IST 
//


package com.auth.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for KycRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KycRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Rar" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="UidData" type="{http://www.uidai.gov.in/kyc/uid-kyc-response/1.0}UidDataType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ret" type="{http://www.uidai.gov.in/kyc/common/types/1.0}YesNoType" />
 *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="txn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="err" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ts" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */


/*1.This class is used for Conversion of
 * i)POJO To XML
 * ii)XML To POJO
 *2.XML Element Map with class variables.
 *3.Generating Setter and getter method.
 * */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KycRes", propOrder = {
    "rar",
    "uidData"
})
@XmlRootElement(name = "KycRes")
public class KycRes {

    @XmlElement(name = "Rar")
    protected byte[] rar;
    @XmlElement(name = "UidData")
    protected UidDataType uidData;
   /* @XmlElement(name = "Signature")
    protected String signature;*/
    @XmlAttribute
    protected YesNoType ret;
    @XmlAttribute
    protected String code;
    @XmlAttribute
    protected String txn;    
    @XmlAttribute
    protected String ttl;
    @XmlAttribute(required = false)
    protected String actn;
    @XmlAttribute
    protected String err;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ts;

    /**
     * Gets the value of the rar property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    
    
    public byte[] getRar() {
        return rar;
    }
/*
    public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
*/
	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getActn() {
		return actn;
	}

	public void setActn(String actn) {
		this.actn = actn;
	}

	/**
     * Sets the value of the rar property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setRar(byte[] value) {
        this.rar = ((byte[]) value);
    }

    /**
     * Gets the value of the uidData property.
     * 
     * @return
     *     possible object is
     *     {@link UidDataType }
     *     
     */
    public UidDataType getUidData() {
        return uidData;
    }

    /**
     * Sets the value of the uidData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UidDataType }
     *     
     */
    public void setUidData(UidDataType value) {
        this.uidData = value;
    }

    /**
     * Gets the value of the ret property.
     * 
     * @return
     *     possible object is
     *     {@link YesNoType }
     *     
     */
    public YesNoType getRet() {
        return ret;
    }

    /**
     * Sets the value of the ret property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoType }
     *     
     */
    public void setRet(YesNoType value) {
        this.ret = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the txn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxn() {
        return txn;
    }

    /**
     * Sets the value of the txn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxn(String value) {
        this.txn = value;
    }

    /**
     * Gets the value of the err property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErr() {
        return err;
    }

    /**
     * Sets the value of the err property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErr(String value) {
        this.err = value;
    }

    /**
     * Gets the value of the ts property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTs() {
        return ts;
    }

    /**
     * Sets the value of the ts property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTs(XMLGregorianCalendar value) {
        this.ts = value;
    }

}
