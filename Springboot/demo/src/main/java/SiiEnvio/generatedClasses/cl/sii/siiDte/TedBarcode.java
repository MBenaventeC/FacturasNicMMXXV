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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


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
 *         <element name="TED">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="DD">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
 *                             <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
 *                             <element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/>
 *                             <element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             <element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/>
 *                             <element name="RSR">
 *                               <simpleType>
 *                                 <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   <minLength value="1"/>
 *                                   <maxLength value="40"/>
 *                                 </restriction>
 *                               </simpleType>
 *                             </element>
 *                             <element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/>
 *                             <element name="IT1">
 *                               <simpleType>
 *                                 <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   <minLength value="1"/>
 *                                   <maxLength value="40"/>
 *                                 </restriction>
 *                               </simpleType>
 *                             </element>
 *                             <element name="CAF">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="DA">
 *                                         <complexType>
 *                                           <complexContent>
 *                                             <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               <sequence>
 *                                                 <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
 *                                                 <element name="RS">
 *                                                   <simpleType>
 *                                                     <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       <minLength value="1"/>
 *                                                       <maxLength value="40"/>
 *                                                     </restriction>
 *                                                   </simpleType>
 *                                                 </element>
 *                                                 <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
 *                                                 <element name="RNG">
 *                                                   <complexType>
 *                                                     <complexContent>
 *                                                       <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         <sequence>
 *                                                           <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
 *                                                           <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
 *                                                         </sequence>
 *                                                       </restriction>
 *                                                     </complexContent>
 *                                                   </complexType>
 *                                                 </element>
 *                                                 <element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                                                 <choice>
 *                                                   <element name="RSAPK">
 *                                                     <complexType>
 *                                                       <complexContent>
 *                                                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                           <sequence>
 *                                                             <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                             <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                           </sequence>
 *                                                         </restriction>
 *                                                       </complexContent>
 *                                                     </complexType>
 *                                                   </element>
 *                                                   <element name="DSAPK">
 *                                                     <complexType>
 *                                                       <complexContent>
 *                                                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                           <sequence>
 *                                                             <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                             <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                             <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                             <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                                           </sequence>
 *                                                         </restriction>
 *                                                       </complexContent>
 *                                                     </complexType>
 *                                                   </element>
 *                                                 </choice>
 *                                                 <element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                                               </sequence>
 *                                             </restriction>
 *                                           </complexContent>
 *                                         </complexType>
 *                                       </element>
 *                                       <element name="FRMA">
 *                                         <complexType>
 *                                           <simpleContent>
 *                                             <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
 *                                               <attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" />
 *                                             </extension>
 *                                           </simpleContent>
 *                                         </complexType>
 *                                       </element>
 *                                     </sequence>
 *                                     <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="FRMT">
 *                     <complexType>
 *                       <simpleContent>
 *                         <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
 *                           <attribute name="algoritmo" use="required">
 *                             <simpleType>
 *                               <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 <enumeration value="SHA1withRSA"/>
 *                                 <enumeration value="SHA1withDSA"/>
 *                               </restriction>
 *                             </simpleType>
 *                           </attribute>
 *                         </extension>
 *                       </simpleContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *                 <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="barcode">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="columns" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   <element name="quiteZone" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   <element name="ec-level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   <element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   <element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
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
    "ted",
    "barcode"
})
@XmlRootElement(name = "TedBarcode", namespace = "http://www.nic.cl/SiiDte")
public class TedBarcode {

    /**
     * Timbre Electronico de DTE
     * 
     */
    @XmlElement(name = "TED", namespace = "http://www.nic.cl/SiiDte", required = true)
    protected TedBarcode.TED ted;
    @XmlElement(namespace = "http://www.nic.cl/SiiDte", required = true)
    protected TedBarcode.Barcode barcode;

    /**
     * Timbre Electronico de DTE
     * 
     * @return
     *     possible object is
     *     {@link TedBarcode.TED }
     *     
     */
    public TedBarcode.TED getTED() {
        return ted;
    }

    /**
     * Define el valor de la propiedad ted.
     * 
     * @param value
     *     allowed object is
     *     {@link TedBarcode.TED }
     *     
     * @see #getTED()
     */
    public void setTED(TedBarcode.TED value) {
        this.ted = value;
    }

    /**
     * Obtiene el valor de la propiedad barcode.
     * 
     * @return
     *     possible object is
     *     {@link TedBarcode.Barcode }
     *     
     */
    public TedBarcode.Barcode getBarcode() {
        return barcode;
    }

    /**
     * Define el valor de la propiedad barcode.
     * 
     * @param value
     *     allowed object is
     *     {@link TedBarcode.Barcode }
     *     
     */
    public void setBarcode(TedBarcode.Barcode value) {
        this.barcode = value;
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
     *         <element name="columns" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         <element name="quiteZone" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         <element name="ec-level" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         <element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         <element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "columns",
        "quiteZone",
        "ecLevel",
        "width",
        "height"
    })
    public static class Barcode {

        @XmlElement(namespace = "http://www.nic.cl/SiiDte")
        protected int columns;
        @XmlElement(namespace = "http://www.nic.cl/SiiDte")
        protected int quiteZone;
        @XmlElement(name = "ec-level", namespace = "http://www.nic.cl/SiiDte")
        protected int ecLevel;
        @XmlElement(namespace = "http://www.nic.cl/SiiDte")
        protected int width;
        @XmlElement(namespace = "http://www.nic.cl/SiiDte")
        protected int height;

        /**
         * Obtiene el valor de la propiedad columns.
         * 
         */
        public int getColumns() {
            return columns;
        }

        /**
         * Define el valor de la propiedad columns.
         * 
         */
        public void setColumns(int value) {
            this.columns = value;
        }

        /**
         * Obtiene el valor de la propiedad quiteZone.
         * 
         */
        public int getQuiteZone() {
            return quiteZone;
        }

        /**
         * Define el valor de la propiedad quiteZone.
         * 
         */
        public void setQuiteZone(int value) {
            this.quiteZone = value;
        }

        /**
         * Obtiene el valor de la propiedad ecLevel.
         * 
         */
        public int getEcLevel() {
            return ecLevel;
        }

        /**
         * Define el valor de la propiedad ecLevel.
         * 
         */
        public void setEcLevel(int value) {
            this.ecLevel = value;
        }

        /**
         * Obtiene el valor de la propiedad width.
         * 
         */
        public int getWidth() {
            return width;
        }

        /**
         * Define el valor de la propiedad width.
         * 
         */
        public void setWidth(int value) {
            this.width = value;
        }

        /**
         * Obtiene el valor de la propiedad height.
         * 
         */
        public int getHeight() {
            return height;
        }

        /**
         * Define el valor de la propiedad height.
         * 
         */
        public void setHeight(int value) {
            this.height = value;
        }

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
     *                   <element name="CAF">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <sequence>
     *                             <element name="DA">
     *                               <complexType>
     *                                 <complexContent>
     *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     <sequence>
     *                                       <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
     *                                       <element name="RS">
     *                                         <simpleType>
     *                                           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             <minLength value="1"/>
     *                                             <maxLength value="40"/>
     *                                           </restriction>
     *                                         </simpleType>
     *                                       </element>
     *                                       <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
     *                                       <element name="RNG">
     *                                         <complexType>
     *                                           <complexContent>
     *                                             <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               <sequence>
     *                                                 <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
     *                                                 <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
     *                                               </sequence>
     *                                             </restriction>
     *                                           </complexContent>
     *                                         </complexType>
     *                                       </element>
     *                                       <element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                                       <choice>
     *                                         <element name="RSAPK">
     *                                           <complexType>
     *                                             <complexContent>
     *                                               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                 <sequence>
     *                                                   <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                   <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                 </sequence>
     *                                               </restriction>
     *                                             </complexContent>
     *                                           </complexType>
     *                                         </element>
     *                                         <element name="DSAPK">
     *                                           <complexType>
     *                                             <complexContent>
     *                                               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                 <sequence>
     *                                                   <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                   <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                   <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                   <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                                                 </sequence>
     *                                               </restriction>
     *                                             </complexContent>
     *                                           </complexType>
     *                                         </element>
     *                                       </choice>
     *                                       <element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                                     </sequence>
     *                                   </restriction>
     *                                 </complexContent>
     *                               </complexType>
     *                             </element>
     *                             <element name="FRMA">
     *                               <complexType>
     *                                 <simpleContent>
     *                                   <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
     *                                     <attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" />
     *                                   </extension>
     *                                 </simpleContent>
     *                               </complexType>
     *                             </element>
     *                           </sequence>
     *                           <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
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
    @XmlType(name = "", propOrder = {
        "dd",
        "frmt"
    })
    public static class TED {

        /**
         * Datos Basicos de Documento
         * 
         */
        @XmlElement(name = "DD", namespace = "http://www.nic.cl/SiiDte", required = true)
        protected TedBarcode.TED.DD dd;
        /**
         * Valor de Firma Digital sobre DD
         * 
         */
        @XmlElement(name = "FRMT", namespace = "http://www.nic.cl/SiiDte", required = true)
        protected TedBarcode.TED.FRMT frmt;
        @XmlAttribute(name = "version", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String version;

        /**
         * Datos Basicos de Documento
         * 
         * @return
         *     possible object is
         *     {@link TedBarcode.TED.DD }
         *     
         */
        public TedBarcode.TED.DD getDD() {
            return dd;
        }

        /**
         * Define el valor de la propiedad dd.
         * 
         * @param value
         *     allowed object is
         *     {@link TedBarcode.TED.DD }
         *     
         * @see #getDD()
         */
        public void setDD(TedBarcode.TED.DD value) {
            this.dd = value;
        }

        /**
         * Valor de Firma Digital sobre DD
         * 
         * @return
         *     possible object is
         *     {@link TedBarcode.TED.FRMT }
         *     
         */
        public TedBarcode.TED.FRMT getFRMT() {
            return frmt;
        }

        /**
         * Define el valor de la propiedad frmt.
         * 
         * @param value
         *     allowed object is
         *     {@link TedBarcode.TED.FRMT }
         *     
         * @see #getFRMT()
         */
        public void setFRMT(TedBarcode.TED.FRMT value) {
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
         *         <element name="CAF">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <sequence>
         *                   <element name="DA">
         *                     <complexType>
         *                       <complexContent>
         *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           <sequence>
         *                             <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
         *                             <element name="RS">
         *                               <simpleType>
         *                                 <restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   <minLength value="1"/>
         *                                   <maxLength value="40"/>
         *                                 </restriction>
         *                               </simpleType>
         *                             </element>
         *                             <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
         *                             <element name="RNG">
         *                               <complexType>
         *                                 <complexContent>
         *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     <sequence>
         *                                       <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
         *                                       <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
         *                                     </sequence>
         *                                   </restriction>
         *                                 </complexContent>
         *                               </complexType>
         *                             </element>
         *                             <element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *                             <choice>
         *                               <element name="RSAPK">
         *                                 <complexType>
         *                                   <complexContent>
         *                                     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                       <sequence>
         *                                         <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                         <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                       </sequence>
         *                                     </restriction>
         *                                   </complexContent>
         *                                 </complexType>
         *                               </element>
         *                               <element name="DSAPK">
         *                                 <complexType>
         *                                   <complexContent>
         *                                     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                       <sequence>
         *                                         <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                         <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                         <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                         <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                                       </sequence>
         *                                     </restriction>
         *                                   </complexContent>
         *                                 </complexType>
         *                               </element>
         *                             </choice>
         *                             <element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *                           </sequence>
         *                         </restriction>
         *                       </complexContent>
         *                     </complexType>
         *                   </element>
         *                   <element name="FRMA">
         *                     <complexType>
         *                       <simpleContent>
         *                         <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
         *                           <attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" />
         *                         </extension>
         *                       </simpleContent>
         *                     </complexType>
         *                   </element>
         *                 </sequence>
         *                 <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
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
            @XmlElement(name = "RE", namespace = "http://www.nic.cl/SiiDte", required = true)
            protected String re;
            /**
             * Tipo DTE
             * 
             */
            @XmlElement(name = "TD", namespace = "http://www.nic.cl/SiiDte", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger td;
            /**
             * Folio DTE
             * 
             */
            @XmlElement(name = "F", namespace = "http://www.nic.cl/SiiDte", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger f;
            /**
             * Fecha Emision DTE en
             * 													Formato AAAA-MM-DD
             * 
             */
            @XmlElement(name = "FE", namespace = "http://www.nic.cl/SiiDte", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fe;
            /**
             * RUT Receptor
             * 
             */
            @XmlElement(name = "RR", namespace = "http://www.nic.cl/SiiDte", required = true)
            protected String rr;
            /**
             * Razon Social
             * 													Receptor
             * 
             */
            @XmlElement(name = "RSR", namespace = "http://www.nic.cl/SiiDte", required = true)
            protected String rsr;
            /**
             * Monto Total DTE
             * 
             */
            @XmlElement(name = "MNT", namespace = "http://www.nic.cl/SiiDte", required = true)
            @XmlSchemaType(name = "unsignedLong")
            protected BigInteger mnt;
            /**
             * Descripcion Primer
             * 													Item de Detalle
             * 
             */
            @XmlElement(name = "IT1", namespace = "http://www.nic.cl/SiiDte", required = true)
            protected String it1;
            /**
             * Codigo Autorizacion
             * 													Folios
             * 
             */
            @XmlElement(name = "CAF", namespace = "http://www.nic.cl/SiiDte", required = true)
            protected TedBarcode.TED.DD.CAF caf;
            /**
             * TimeStamp de
             * 													Generacion del
             * 													Timbre
             * 
             */
            @XmlElement(name = "TSTED", namespace = "http://www.nic.cl/SiiDte", required = true)
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
             * Fecha Emision DTE en
             * 													Formato AAAA-MM-DD
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
             * Razon Social
             * 													Receptor
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
             * Descripcion Primer
             * 													Item de Detalle
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
             * Codigo Autorizacion
             * 													Folios
             * 
             * @return
             *     possible object is
             *     {@link TedBarcode.TED.DD.CAF }
             *     
             */
            public TedBarcode.TED.DD.CAF getCAF() {
                return caf;
            }

            /**
             * Define el valor de la propiedad caf.
             * 
             * @param value
             *     allowed object is
             *     {@link TedBarcode.TED.DD.CAF }
             *     
             * @see #getCAF()
             */
            public void setCAF(TedBarcode.TED.DD.CAF value) {
                this.caf = value;
            }

            /**
             * TimeStamp de
             * 													Generacion del
             * 													Timbre
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
             *         <element name="DA">
             *           <complexType>
             *             <complexContent>
             *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 <sequence>
             *                   <element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/>
             *                   <element name="RS">
             *                     <simpleType>
             *                       <restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         <minLength value="1"/>
             *                         <maxLength value="40"/>
             *                       </restriction>
             *                     </simpleType>
             *                   </element>
             *                   <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
             *                   <element name="RNG">
             *                     <complexType>
             *                       <complexContent>
             *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           <sequence>
             *                             <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
             *                             <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
             *                           </sequence>
             *                         </restriction>
             *                       </complexContent>
             *                     </complexType>
             *                   </element>
             *                   <element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/>
             *                   <choice>
             *                     <element name="RSAPK">
             *                       <complexType>
             *                         <complexContent>
             *                           <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                             <sequence>
             *                               <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                               <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                             </sequence>
             *                           </restriction>
             *                         </complexContent>
             *                       </complexType>
             *                     </element>
             *                     <element name="DSAPK">
             *                       <complexType>
             *                         <complexContent>
             *                           <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                             <sequence>
             *                               <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                               <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                               <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                               <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *                             </sequence>
             *                           </restriction>
             *                         </complexContent>
             *                       </complexType>
             *                     </element>
             *                   </choice>
             *                   <element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/>
             *                 </sequence>
             *               </restriction>
             *             </complexContent>
             *           </complexType>
             *         </element>
             *         <element name="FRMA">
             *           <complexType>
             *             <simpleContent>
             *               <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
             *                 <attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" />
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
            @XmlType(name = "", propOrder = {
                "da",
                "frma"
            })
            public static class CAF {

                /**
                 * Datos de
                 * 																Autorizacion
                 * 																de
                 * 																Folios
                 * 
                 */
                @XmlElement(name = "DA", namespace = "http://www.nic.cl/SiiDte", required = true)
                protected TedBarcode.TED.DD.CAF.DA da;
                /**
                 * Firma
                 * 																Digital
                 * 																(RSA)
                 * 																del SII
                 * 																Sobre DA
                 * 
                 */
                @XmlElement(name = "FRMA", namespace = "http://www.nic.cl/SiiDte", required = true)
                protected TedBarcode.TED.DD.CAF.FRMA frma;
                @XmlAttribute(name = "version", required = true)
                @XmlSchemaType(name = "anySimpleType")
                protected String version;

                /**
                 * Datos de
                 * 																Autorizacion
                 * 																de
                 * 																Folios
                 * 
                 * @return
                 *     possible object is
                 *     {@link TedBarcode.TED.DD.CAF.DA }
                 *     
                 */
                public TedBarcode.TED.DD.CAF.DA getDA() {
                    return da;
                }

                /**
                 * Define el valor de la propiedad da.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link TedBarcode.TED.DD.CAF.DA }
                 *     
                 * @see #getDA()
                 */
                public void setDA(TedBarcode.TED.DD.CAF.DA value) {
                    this.da = value;
                }

                /**
                 * Firma
                 * 																Digital
                 * 																(RSA)
                 * 																del SII
                 * 																Sobre DA
                 * 
                 * @return
                 *     possible object is
                 *     {@link TedBarcode.TED.DD.CAF.FRMA }
                 *     
                 */
                public TedBarcode.TED.DD.CAF.FRMA getFRMA() {
                    return frma;
                }

                /**
                 * Define el valor de la propiedad frma.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link TedBarcode.TED.DD.CAF.FRMA }
                 *     
                 * @see #getFRMA()
                 */
                public void setFRMA(TedBarcode.TED.DD.CAF.FRMA value) {
                    this.frma = value;
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
                 *         <element name="RS">
                 *           <simpleType>
                 *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               <minLength value="1"/>
                 *               <maxLength value="40"/>
                 *             </restriction>
                 *           </simpleType>
                 *         </element>
                 *         <element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/>
                 *         <element name="RNG">
                 *           <complexType>
                 *             <complexContent>
                 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 <sequence>
                 *                   <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
                 *                   <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
                 *                 </sequence>
                 *               </restriction>
                 *             </complexContent>
                 *           </complexType>
                 *         </element>
                 *         <element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/>
                 *         <choice>
                 *           <element name="RSAPK">
                 *             <complexType>
                 *               <complexContent>
                 *                 <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                   <sequence>
                 *                     <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                     <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                   </sequence>
                 *                 </restriction>
                 *               </complexContent>
                 *             </complexType>
                 *           </element>
                 *           <element name="DSAPK">
                 *             <complexType>
                 *               <complexContent>
                 *                 <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                   <sequence>
                 *                     <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                     <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                     <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                     <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                 *                   </sequence>
                 *                 </restriction>
                 *               </complexContent>
                 *             </complexType>
                 *           </element>
                 *         </choice>
                 *         <element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
                    "rs",
                    "td",
                    "rng",
                    "fa",
                    "rsapk",
                    "dsapk",
                    "idk"
                })
                public static class DA {

                    /**
                     * RUT
                     * 																			Emisor
                     * 
                     */
                    @XmlElement(name = "RE", namespace = "http://www.nic.cl/SiiDte", required = true)
                    protected String re;
                    /**
                     * Razon
                     * 																			Social
                     * 																			Emisor
                     * 
                     */
                    @XmlElement(name = "RS", namespace = "http://www.nic.cl/SiiDte", required = true)
                    protected String rs;
                    /**
                     * Tipo
                     * 																			DTE
                     * 
                     */
                    @XmlElement(name = "TD", namespace = "http://www.nic.cl/SiiDte", required = true)
                    @XmlSchemaType(name = "positiveInteger")
                    protected BigInteger td;
                    /**
                     * Rango
                     * 																			Autorizado
                     * 																			de
                     * 																			Folios
                     * 
                     */
                    @XmlElement(name = "RNG", namespace = "http://www.nic.cl/SiiDte", required = true)
                    protected TedBarcode.TED.DD.CAF.DA.RNG rng;
                    /**
                     * Fecha
                     * 																			Autorizacion
                     * 																			en
                     * 																			Formato
                     * 																			AAAA-MM-DD
                     * 
                     */
                    @XmlElement(name = "FA", namespace = "http://www.nic.cl/SiiDte", required = true)
                    @XmlSchemaType(name = "date")
                    protected XMLGregorianCalendar fa;
                    /**
                     * Clave
                     * 																				Publica
                     * 																				RSA
                     * 																				del
                     * 																				Solicitante
                     * 
                     */
                    @XmlElement(name = "RSAPK", namespace = "http://www.nic.cl/SiiDte")
                    protected TedBarcode.TED.DD.CAF.DA.RSAPK rsapk;
                    /**
                     * Clave
                     * 																				Publica
                     * 																				DSA
                     * 																				del
                     * 																				Solicitante
                     * 
                     */
                    @XmlElement(name = "DSAPK", namespace = "http://www.nic.cl/SiiDte")
                    protected TedBarcode.TED.DD.CAF.DA.DSAPK dsapk;
                    /**
                     * Identificador
                     * 																			de
                     * 																			Llave
                     * 
                     */
                    @XmlElement(name = "IDK", namespace = "http://www.nic.cl/SiiDte")
                    protected long idk;

                    /**
                     * RUT
                     * 																			Emisor
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
                     * Razon
                     * 																			Social
                     * 																			Emisor
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getRS() {
                        return rs;
                    }

                    /**
                     * Define el valor de la propiedad rs.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     * @see #getRS()
                     */
                    public void setRS(String value) {
                        this.rs = value;
                    }

                    /**
                     * Tipo
                     * 																			DTE
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
                     * Rango
                     * 																			Autorizado
                     * 																			de
                     * 																			Folios
                     * 
                     * @return
                     *     possible object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.RNG }
                     *     
                     */
                    public TedBarcode.TED.DD.CAF.DA.RNG getRNG() {
                        return rng;
                    }

                    /**
                     * Define el valor de la propiedad rng.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.RNG }
                     *     
                     * @see #getRNG()
                     */
                    public void setRNG(TedBarcode.TED.DD.CAF.DA.RNG value) {
                        this.rng = value;
                    }

                    /**
                     * Fecha
                     * 																			Autorizacion
                     * 																			en
                     * 																			Formato
                     * 																			AAAA-MM-DD
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getFA() {
                        return fa;
                    }

                    /**
                     * Define el valor de la propiedad fa.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     * @see #getFA()
                     */
                    public void setFA(XMLGregorianCalendar value) {
                        this.fa = value;
                    }

                    /**
                     * Clave
                     * 																				Publica
                     * 																				RSA
                     * 																				del
                     * 																				Solicitante
                     * 
                     * @return
                     *     possible object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.RSAPK }
                     *     
                     */
                    public TedBarcode.TED.DD.CAF.DA.RSAPK getRSAPK() {
                        return rsapk;
                    }

                    /**
                     * Define el valor de la propiedad rsapk.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.RSAPK }
                     *     
                     * @see #getRSAPK()
                     */
                    public void setRSAPK(TedBarcode.TED.DD.CAF.DA.RSAPK value) {
                        this.rsapk = value;
                    }

                    /**
                     * Clave
                     * 																				Publica
                     * 																				DSA
                     * 																				del
                     * 																				Solicitante
                     * 
                     * @return
                     *     possible object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.DSAPK }
                     *     
                     */
                    public TedBarcode.TED.DD.CAF.DA.DSAPK getDSAPK() {
                        return dsapk;
                    }

                    /**
                     * Define el valor de la propiedad dsapk.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link TedBarcode.TED.DD.CAF.DA.DSAPK }
                     *     
                     * @see #getDSAPK()
                     */
                    public void setDSAPK(TedBarcode.TED.DD.CAF.DA.DSAPK value) {
                        this.dsapk = value;
                    }

                    /**
                     * Identificador
                     * 																			de
                     * 																			Llave
                     * 
                     */
                    public long getIDK() {
                        return idk;
                    }

                    /**
                     * Define el valor de la propiedad idk.
                     * 
                     */
                    public void setIDK(long value) {
                        this.idk = value;
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
                     *         <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                     *         <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                     *         <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                     *         <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
                        "p",
                        "q",
                        "g",
                        "y"
                    })
                    public static class DSAPK {

                        /**
                         * Modulo
                         * 																							Primo
                         * 
                         */
                        @XmlElement(name = "P", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] p;
                        /**
                         * Entero
                         * 																							Divisor
                         * 																							de
                         * 																							P
                         * 																							-
                         * 																							1
                         * 
                         */
                        @XmlElement(name = "Q", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] q;
                        /**
                         * Entero
                         * 																							f(P,
                         * 																							Q)
                         * 
                         */
                        @XmlElement(name = "G", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] g;
                        /**
                         * G**X
                         * 																							mod
                         * 																							P
                         * 
                         */
                        @XmlElement(name = "Y", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] y;

                        /**
                         * Modulo
                         * 																							Primo
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getP() {
                            return p;
                        }

                        /**
                         * Define el valor de la propiedad p.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getP()
                         */
                        public void setP(byte[] value) {
                            this.p = value;
                        }

                        /**
                         * Entero
                         * 																							Divisor
                         * 																							de
                         * 																							P
                         * 																							-
                         * 																							1
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getQ() {
                            return q;
                        }

                        /**
                         * Define el valor de la propiedad q.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getQ()
                         */
                        public void setQ(byte[] value) {
                            this.q = value;
                        }

                        /**
                         * Entero
                         * 																							f(P,
                         * 																							Q)
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getG() {
                            return g;
                        }

                        /**
                         * Define el valor de la propiedad g.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getG()
                         */
                        public void setG(byte[] value) {
                            this.g = value;
                        }

                        /**
                         * G**X
                         * 																							mod
                         * 																							P
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getY() {
                            return y;
                        }

                        /**
                         * Define el valor de la propiedad y.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getY()
                         */
                        public void setY(byte[] value) {
                            this.y = value;
                        }

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
                     *         <element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/>
                     *         <element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/>
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
                        "d",
                        "h"
                    })
                    public static class RNG {

                        /**
                         * Folio
                         * 																						Inicial
                         * 																						(Desde)
                         * 
                         */
                        @XmlElement(name = "D", namespace = "http://www.nic.cl/SiiDte", required = true)
                        @XmlSchemaType(name = "positiveInteger")
                        protected BigInteger d;
                        /**
                         * Folio
                         * 																						Final
                         * 																						(Hasta)
                         * 
                         */
                        @XmlElement(name = "H", namespace = "http://www.nic.cl/SiiDte", required = true)
                        @XmlSchemaType(name = "positiveInteger")
                        protected BigInteger h;

                        /**
                         * Folio
                         * 																						Inicial
                         * 																						(Desde)
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigInteger }
                         *     
                         */
                        public BigInteger getD() {
                            return d;
                        }

                        /**
                         * Define el valor de la propiedad d.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigInteger }
                         *     
                         * @see #getD()
                         */
                        public void setD(BigInteger value) {
                            this.d = value;
                        }

                        /**
                         * Folio
                         * 																						Final
                         * 																						(Hasta)
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigInteger }
                         *     
                         */
                        public BigInteger getH() {
                            return h;
                        }

                        /**
                         * Define el valor de la propiedad h.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigInteger }
                         *     
                         * @see #getH()
                         */
                        public void setH(BigInteger value) {
                            this.h = value;
                        }

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
                     *         <element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
                     *         <element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
                        "m",
                        "e"
                    })
                    public static class RSAPK {

                        /**
                         * Modulo
                         * 																							RSA
                         * 
                         */
                        @XmlElement(name = "M", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] m;
                        /**
                         * Exponente
                         * 																							RSA
                         * 
                         */
                        @XmlElement(name = "E", namespace = "http://www.nic.cl/SiiDte", required = true)
                        protected byte[] e;

                        /**
                         * Modulo
                         * 																							RSA
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getM() {
                            return m;
                        }

                        /**
                         * Define el valor de la propiedad m.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getM()
                         */
                        public void setM(byte[] value) {
                            this.m = value;
                        }

                        /**
                         * Exponente
                         * 																							RSA
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getE() {
                            return e;
                        }

                        /**
                         * Define el valor de la propiedad e.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         * @see #getE()
                         */
                        public void setE(byte[] value) {
                            this.e = value;
                        }

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
                 *       <attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" />
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
                public static class FRMA {

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
                        if (algoritmo == null) {
                            return "SHA1withRSA";
                        } else {
                            return algoritmo;
                        }
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

}
