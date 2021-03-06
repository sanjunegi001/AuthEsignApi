//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.06 at 04:38:08 PM IST 
//


package com.auth.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;




/**
 * <p>Java class for BiometricPosition.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BiometricPosition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LEFT_IRIS"/>
 *     &lt;enumeration value="RIGHT_IRIS"/>
 *     &lt;enumeration value="LEFT_INDEX"/>
 *     &lt;enumeration value="LEFT_LITTLE"/>
 *     &lt;enumeration value="LEFT_MIDDLE"/>
 *     &lt;enumeration value="LEFT_RING"/>
 *     &lt;enumeration value="LEFT_THUMB"/>
 *     &lt;enumeration value="RIGHT_INDEX"/>
 *     &lt;enumeration value="RIGHT_LITTLE"/>
 *     &lt;enumeration value="RIGHT_MIDDLE"/>
 *     &lt;enumeration value="RIGHT_RING"/>
 *     &lt;enumeration value="RIGHT_THUMB"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */

/* 1.This Class is used For Conversion of
 * i).POJO To XML
 * ii).XML To POJO Viceversa
 * 2.Defining Enumeration Class(inside of we are declaring Biometric position)
 * 3.Mapping @XmlEnum and Class variables.
 * * */
@XmlType(name = "BiometricPosition")
@XmlEnum
public enum BiometricPosition {

    LEFT_IRIS,
    RIGHT_IRIS,
    LEFT_INDEX,
    LEFT_LITTLE,
    LEFT_MIDDLE,
    LEFT_RING,
    LEFT_THUMB,
    RIGHT_INDEX,
    RIGHT_LITTLE,
    RIGHT_MIDDLE,
    RIGHT_RING,
    RIGHT_THUMB,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static BiometricPosition fromValue(String v) {
        return valueOf(v);
    }

}
