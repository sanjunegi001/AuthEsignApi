//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.06 at 04:38:08 PM IST 
//

package com.auth.model; 


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;




/**
 * <p>Java class for UsesFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UsesFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="y"/>
 *     &lt;enumeration value="n"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */


/*1.This class is used for Conversion of
 * i)POJO To XML
 * ii)XML To POJO
 *2.XML Element Map with class variables.
 * */
@XmlType(name = "UsesFlag")
@XmlEnum
public enum UsesFlag {

    @XmlEnumValue("y")
    Y("y"),
    @XmlEnumValue("n")
    N("n");
    private final String value;

    UsesFlag(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UsesFlag fromValue(String v) {
        for (UsesFlag c: UsesFlag.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
