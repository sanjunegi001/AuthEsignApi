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
 * <p>Java class for DataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="X"/>
 *     &lt;enumeration value="P"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataType")
@XmlEnum
public enum DataType {

    X,
    P;

    public String value() {
        return name();
    }

    public static DataType fromValue(String v) {
        return valueOf(v);
    }

}
