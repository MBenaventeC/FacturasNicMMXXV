
package SiiBoletaTest;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MedioPagoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="MedioPagoType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CH"/&gt;
 *     &lt;enumeration value="LT"/&gt;
 *     &lt;enumeration value="EF"/&gt;
 *     &lt;enumeration value="PE"/&gt;
 *     &lt;enumeration value="TC"/&gt;
 *     &lt;enumeration value="CF"/&gt;
 *     &lt;enumeration value="OT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MedioPagoType", namespace = "http://www.sii.cl/SiiDte")
@XmlEnum
public enum MedioPagoType {


    /**
     * Cheque
     * 
     */
    CH,

    /**
     * Letra
     * 
     */
    LT,

    /**
     * Efectivo
     * 
     */
    EF,

    /**
     * Pago a Cuenta Corriente
     * 
     */
    PE,

    /**
     * Tarjeta de Credito
     * 
     */
    TC,

    /**
     * Cheque a Fecha
     * 
     */
    CF,

    /**
     * Otro
     * 
     */
    OT;

    public String value() {
        return name();
    }

    public static MedioPagoType fromValue(String v) {
        return valueOf(v);
    }

}
