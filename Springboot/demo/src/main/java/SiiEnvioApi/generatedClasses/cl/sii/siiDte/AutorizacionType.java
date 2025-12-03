//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvioApi.generatedClasses.cl.sii.siiDte;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AutorizacionType complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType name="AutorizacionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="CAF" type="{http://www.sii.cl/SiiDte}CAFType"/>
 *         <element name="RSASK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="RSAPUBK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutorizacionType", propOrder = {
    "caf",
    "rsask",
    "rsapubk"
})
public class AutorizacionType {

    /**
     * Codigo Autorizacion Folios
     * 
     */
    @XmlElement(name = "CAF", required = true)
    protected CAFType caf;
    /**
     * LLave privada en PEM format
     * 
     */
    @XmlElement(name = "RSASK", required = true)
    protected String rsask;
    /**
     * LLave public en PEM format
     * 
     */
    @XmlElement(name = "RSAPUBK", required = true)
    protected String rsapubk;

    /**
     * Codigo Autorizacion Folios
     * 
     * @return
     *     possible object is
     *     {@link CAFType }
     *     
     */
    public CAFType getCAF() {
        return caf;
    }

    /**
     * Define el valor de la propiedad caf.
     * 
     * @param value
     *     allowed object is
     *     {@link CAFType }
     *     
     * @see #getCAF()
     */
    public void setCAF(CAFType value) {
        this.caf = value;
    }

    /**
     * LLave privada en PEM format
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRSASK() {
        return rsask;
    }

    /**
     * Define el valor de la propiedad rsask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRSASK()
     */
    public void setRSASK(String value) {
        this.rsask = value;
    }

    /**
     * LLave public en PEM format
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRSAPUBK() {
        return rsapubk;
    }

    /**
     * Define el valor de la propiedad rsapubk.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRSAPUBK()
     */
    public void setRSAPUBK(String value) {
        this.rsapubk = value;
    }

}
