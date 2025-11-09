//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvio.generatedClasses.cl.sii.siiDte;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Medios de Pago
 * 
 * <p>Clase Java para MedioPagoType.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * <pre>{@code
 * <simpleType name="MedioPagoType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="CH"/>
 *     <enumeration value="LT"/>
 *     <enumeration value="EF"/>
 *     <enumeration value="PE"/>
 *     <enumeration value="TC"/>
 *     <enumeration value="CF"/>
 *     <enumeration value="OT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "MedioPagoType")
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
