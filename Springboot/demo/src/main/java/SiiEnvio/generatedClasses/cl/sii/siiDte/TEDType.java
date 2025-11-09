//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvio.generatedClasses.cl.sii.siiDte;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Clase Java para TEDType complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType name="TEDType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="DD">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
 *                   <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
 *                   <element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/>
 *                   <element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   <element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/>
 *                   <element name="RSR">
 *                     <simpleType>
 *                       <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         <minLength value="1"/>
 *                         <maxLength value="40"/>
 *                       </restriction>
 *                     </simpleType>
 *                   </element>
 *                   <element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/>
 *                   <element name="IT1">
 *                     <simpleType>
 *                       <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         <minLength value="1"/>
 *                         <maxLength value="40"/>
 *                       </restriction>
 *                     </simpleType>
 *                   </element>
 *                   <element name="CAF" type="{http://www.sii.cl/SiiDte}CAFType"/>
 *                   <element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="FRMT">
 *           <complexType>
 *             <simpleContent>
 *               <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
 *                 <attribute name="algoritmo" use="required">
 *                   <simpleType>
 *                     <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       <enumeration value="SHA1withRSA"/>
 *                       <enumeration value="SHA1withDSA"/>
 *                     </restriction>
 *                   </simpleType>
 *                 </attribute>
 *               </extension>
 *             </simpleContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEDType", propOrder = {
    "dd",
    "frmt"
})
public class TEDType {

    /**
     * Datos Basicos de Documento
     * 
     */
    @XmlElement(name = "DD", required = true)
    protected TEDType.DD dd;
    /**
     * Valor de Firma Digital sobre DD
     * 
     */
    @XmlElement(name = "FRMT", required = true)
    protected TEDType.FRMT frmt;
    @XmlAttribute(name = "version", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String version;

    /**
     * Datos Basicos de Documento
     * 
     * @return
     *     possible object is
     *     {@link TEDType.DD }
     *     
     */
    public TEDType.DD getDD() {
        return dd;
    }

    /**
     * Define el valor de la propiedad dd.
     * 
     * @param value
     *     allowed object is
     *     {@link TEDType.DD }
     *     
     * @see #getDD()
     */
    public void setDD(TEDType.DD value) {
        this.dd = value;
    }

    /**
     * Valor de Firma Digital sobre DD
     * 
     * @return
     *     possible object is
     *     {@link TEDType.FRMT }
     *     
     */
    public TEDType.FRMT getFRMT() {
        return frmt;
    }

    /**
     * Define el valor de la propiedad frmt.
     * 
     * @param value
     *     allowed object is
     *     {@link TEDType.FRMT }
     *     
     * @see #getFRMT()
     */
    public void setFRMT(TEDType.FRMT value) {
        this.frmt = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.0";
        } else {
            return version;
        }
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     *         <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
     *         <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
     *         <element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/>
     *         <element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         <element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/>
     *         <element name="RSR">
     *           <simpleType>
     *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               <minLength value="1"/>
     *               <maxLength value="40"/>
     *             </restriction>
     *           </simpleType>
     *         </element>
     *         <element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/>
     *         <element name="IT1">
     *           <simpleType>
     *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               <minLength value="1"/>
     *               <maxLength value="40"/>
     *             </restriction>
     *           </simpleType>
     *         </element>
     *         <element name="CAF" type="{http://www.sii.cl/SiiDte}CAFType"/>
     *         <element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
        "re",
        "td",
        "f",
        "fe",
        "rr",
        "rsr",
        "mnt",
        "it1",
        "caf",
        "tsted"
    })
    public static class DD {

        /**
         * RUT Emisor
         * 
         */
        @XmlElement(name = "RE", required = true)
        protected String re;
        /**
         * Tipo DTE
         * 
         */
        @XmlElement(name = "TD", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger td;
        /**
         * Folio DTE
         * 
         */
        @XmlElement(name = "F", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger f;
        /**
         * Fecha Emision DTE en Formato
         * 									AAAA-MM-DD
         * 
         */
        @XmlElement(name = "FE", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fe;
        /**
         * RUT Receptor
         * 
         */
        @XmlElement(name = "RR", required = true)
        protected String rr;
        /**
         * Razon Social Receptor
         * 
         */
        @XmlElement(name = "RSR", required = true)
        protected String rsr;
        /**
         * Monto Total DTE
         * 
         */
        @XmlElement(name = "MNT", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger mnt;
        /**
         * Descripcion Primer Item de Detalle
         * 
         */
        @XmlElement(name = "IT1", required = true)
        protected String it1;
        /**
         * Codigo Autorizacion Folios
         * 
         */
        @XmlElement(name = "CAF", required = true)
        protected CAFType caf;
        /**
         * TimeStamp de Generacion del Timbre
         * 
         */
        @XmlElement(name = "TSTED", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsted;

        /**
         * RUT Emisor
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRE() {
            return re;
        }

        /**
         * Define el valor de la propiedad re.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getRE()
         */
        public void setRE(String value) {
            this.re = value;
        }

        /**
         * Tipo DTE
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTD() {
            return td;
        }

        /**
         * Define el valor de la propiedad td.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         * @see #getTD()
         */
        public void setTD(BigInteger value) {
            this.td = value;
        }

        /**
         * Folio DTE
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getF() {
            return f;
        }

        /**
         * Define el valor de la propiedad f.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         * @see #getF()
         */
        public void setF(BigInteger value) {
            this.f = value;
        }

        /**
         * Fecha Emision DTE en Formato
         * 									AAAA-MM-DD
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFE() {
            return fe;
        }

        /**
         * Define el valor de la propiedad fe.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         * @see #getFE()
         */
        public void setFE(XMLGregorianCalendar value) {
            this.fe = value;
        }

        /**
         * RUT Receptor
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRR() {
            return rr;
        }

        /**
         * Define el valor de la propiedad rr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getRR()
         */
        public void setRR(String value) {
            this.rr = value;
        }

        /**
         * Razon Social Receptor
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRSR() {
            return rsr;
        }

        /**
         * Define el valor de la propiedad rsr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getRSR()
         */
        public void setRSR(String value) {
            this.rsr = value;
        }

        /**
         * Monto Total DTE
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMNT() {
            return mnt;
        }

        /**
         * Define el valor de la propiedad mnt.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         * @see #getMNT()
         */
        public void setMNT(BigInteger value) {
            this.mnt = value;
        }

        /**
         * Descripcion Primer Item de Detalle
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIT1() {
            return it1;
        }

        /**
         * Define el valor de la propiedad it1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getIT1()
         */
        public void setIT1(String value) {
            this.it1 = value;
        }

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
         * TimeStamp de Generacion del Timbre
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTSTED() {
            return tsted;
        }

        /**
         * Define el valor de la propiedad tsted.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         * @see #getTSTED()
         */
        public void setTSTED(XMLGregorianCalendar value) {
            this.tsted = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.</p>
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <simpleContent>
     *     <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
     *       <attribute name="algoritmo" use="required">
     *         <simpleType>
     *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             <enumeration value="SHA1withRSA"/>
     *             <enumeration value="SHA1withDSA"/>
     *           </restriction>
     *         </simpleType>
     *       </attribute>
     *     </extension>
     *   </simpleContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class FRMT {

        @XmlValue
        protected byte[] value;
        @XmlAttribute(name = "algoritmo", required = true)
        protected String algoritmo;

        /**
         * Obtiene el valor de la propiedad value.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Define el valor de la propiedad value.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

        /**
         * Obtiene el valor de la propiedad algoritmo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlgoritmo() {
            return algoritmo;
        }

        /**
         * Define el valor de la propiedad algoritmo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlgoritmo(String value) {
            this.algoritmo = value;
        }

    }

}
