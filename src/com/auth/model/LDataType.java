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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="co" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="house" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="street" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vtc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subdist" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dist" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="po" type="{http://www.w3.org/2001/XMLSchema}string" />
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
 *2.Inside of @XmlRootElement calling the esignxml url
 *3.XML Element Map with class variables.
 *4.Generating Setter and getter method.
 * */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LDataType")
@XmlRootElement(name = "LDataType")
public class LDataType {

    @XmlAttribute
    protected String lang;
    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected String co;
    @XmlAttribute
    protected String house;
    @XmlAttribute
    protected String street;
    @XmlAttribute
    protected String lm;
    @XmlAttribute
    protected String lc;
    @XmlAttribute
    protected String vtc;
    @XmlAttribute
    protected String subdist;
    @XmlAttribute
    protected String dist;
    @XmlAttribute
    protected String state;
    @XmlAttribute
    protected String pc;
    @XmlAttribute
    protected String po;

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the co property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCo() {
        return co;
    }

    /**
     * Sets the value of the co property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCo(String value) {
        this.co = value;
    }

    /**
     * Gets the value of the house property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets the value of the house property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouse(String value) {
        this.house = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the lm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLm() {
        return lm;
    }

    /**
     * Sets the value of the lm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLm(String value) {
        this.lm = value;
    }

    /**
     * Gets the value of the lc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLc() {
        return lc;
    }

    /**
     * Sets the value of the lc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLc(String value) {
        this.lc = value;
    }

    /**
     * Gets the value of the vtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtc() {
        return vtc;
    }

    /**
     * Sets the value of the vtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtc(String value) {
        this.vtc = value;
    }

    /**
     * Gets the value of the subdist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubdist() {
        return subdist;
    }

    /**
     * Sets the value of the subdist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubdist(String value) {
        this.subdist = value;
    }

    /**
     * Gets the value of the dist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDist() {
        return dist;
    }

    /**
     * Sets the value of the dist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDist(String value) {
        this.dist = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the pc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPc() {
        return pc;
    }

    /**
     * Sets the value of the pc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPc(String value) {
        this.pc = value;
    }

    /**
     * Gets the value of the po property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPo() {
        return po;
    }

    /**
     * Sets the value of the po property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPo(String value) {
        this.po = value;
    }

}
