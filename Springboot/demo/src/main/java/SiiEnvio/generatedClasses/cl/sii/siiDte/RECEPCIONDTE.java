//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvio.generatedClasses.cl.sii.siiDte;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="RUTSENDER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="RUTCOMPANY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="FILE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="TIMESTAMP" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="TRACKID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           <element name="DETAIL">
 *             <complexType>
 *               <complexContent>
 *                 <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   <sequence>
 *                     <element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   </sequence>
 *                 </restriction>
 *               </complexContent>
 *             </complexType>
 *           </element>
 *         </choice>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rutsender",
    "rutcompany",
    "file",
    "timestamp",
    "status",
    "trackidOrDETAIL"
})
@XmlRootElement(name = "RECEPCIONDTE")
public class RECEPCIONDTE {

    @XmlElement(name = "RUTSENDER", required = true)
    protected String rutsender;
    @XmlElement(name = "RUTCOMPANY", required = true)
    protected String rutcompany;
    @XmlElement(name = "FILE", required = true)
    protected String file;
    @XmlElement(name = "TIMESTAMP", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "STATUS")
    protected int status;
    @XmlElements({
        @XmlElement(name = "TRACKID", type = Integer.class),
        @XmlElement(name = "DETAIL", type = RECEPCIONDTE.DETAIL.class)
    })
    protected List<Object> trackidOrDETAIL;

    /**
     * Obtiene el valor de la propiedad rutsender.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUTSENDER() {
        return rutsender;
    }

    /**
     * Define el valor de la propiedad rutsender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUTSENDER(String value) {
        this.rutsender = value;
    }

    /**
     * Obtiene el valor de la propiedad rutcompany.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUTCOMPANY() {
        return rutcompany;
    }

    /**
     * Define el valor de la propiedad rutcompany.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUTCOMPANY(String value) {
        this.rutcompany = value;
    }

    /**
     * Obtiene el valor de la propiedad file.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFILE() {
        return file;
    }

    /**
     * Define el valor de la propiedad file.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFILE(String value) {
        this.file = value;
    }

    /**
     * Obtiene el valor de la propiedad timestamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTIMESTAMP() {
        return timestamp;
    }

    /**
     * Define el valor de la propiedad timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTIMESTAMP(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     */
    public int getSTATUS() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     */
    public void setSTATUS(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the trackidOrDETAIL property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackidOrDETAIL property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTRACKIDOrDETAIL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RECEPCIONDTE.DETAIL }
     * {@link Integer }
     * </p>
     * 
     * 
     * @return
     *     The value of the trackidOrDETAIL property.
     */
    public List<Object> getTRACKIDOrDETAIL() {
        if (trackidOrDETAIL == null) {
            trackidOrDETAIL = new ArrayList<>();
        }
        return this.trackidOrDETAIL;
    }


    /**
     * <p>Clase Java para anonymous complex type.</p>
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "error"
    })
    public static class DETAIL {

        @XmlElement(name = "ERROR")
        protected List<String> error;

        /**
         * Gets the value of the error property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the error property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getERROR().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * </p>
         * 
         * 
         * @return
         *     The value of the error property.
         */
        public List<String> getERROR() {
            if (error == null) {
                error = new ArrayList<>();
            }
            return this.error;
        }

    }

}
