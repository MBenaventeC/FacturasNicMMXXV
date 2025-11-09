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
 * Codigo Autorizacion Folios
 * 
 * <p>Clase Java para CAFType complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType name="CAFType">
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
@XmlType(name = "CAFType", propOrder = {
    "da",
    "frma"
})
public class CAFType {

    /**
     * Datos de Autorizacion de Folios
     * 
     */
    @XmlElement(name = "DA", required = true)
    protected CAFType.DA da;
    /**
     * Firma Digital (RSA) del SII Sobre DA
     * 
     */
    @XmlElement(name = "FRMA", required = true)
    protected CAFType.FRMA frma;
    @XmlAttribute(name = "version", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String version;

    /**
     * Datos de Autorizacion de Folios
     * 
     * @return
     *     possible object is
     *     {@link CAFType.DA }
     *     
     */
    public CAFType.DA getDA() {
        return da;
    }

    /**
     * Define el valor de la propiedad da.
     * 
     * @param value
     *     allowed object is
     *     {@link CAFType.DA }
     *     
     * @see #getDA()
     */
    public void setDA(CAFType.DA value) {
        this.da = value;
    }

    /**
     * Firma Digital (RSA) del SII Sobre DA
     * 
     * @return
     *     possible object is
     *     {@link CAFType.FRMA }
     *     
     */
    public CAFType.FRMA getFRMA() {
        return frma;
    }

    /**
     * Define el valor de la propiedad frma.
     * 
     * @param value
     *     allowed object is
     *     {@link CAFType.FRMA }
     *     
     * @see #getFRMA()
     */
    public void setFRMA(CAFType.FRMA value) {
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
         * RUT Emisor
         * 
         */
        @XmlElement(name = "RE", required = true)
        protected String re;
        /**
         * Razon Social Emisor
         * 
         */
        @XmlElement(name = "RS", required = true)
        protected String rs;
        /**
         * Tipo DTE
         * 
         */
        @XmlElement(name = "TD", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger td;
        /**
         * Rango Autorizado de Folios
         * 
         */
        @XmlElement(name = "RNG", required = true)
        protected CAFType.DA.RNG rng;
        /**
         * Fecha Autorizacion en Formato
         * 									AAAA-MM-DD
         * 
         */
        @XmlElement(name = "FA", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fa;
        /**
         * Clave Publica RSA del
         * 										Solicitante
         * 
         */
        @XmlElement(name = "RSAPK")
        protected CAFType.DA.RSAPK rsapk;
        /**
         * Clave Publica DSA del
         * 										Solicitante
         * 
         */
        @XmlElement(name = "DSAPK")
        protected CAFType.DA.DSAPK dsapk;
        /**
         * Identificador de Llave
         * 
         */
        @XmlElement(name = "IDK")
        protected long idk;

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
         * Razon Social Emisor
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
         * Rango Autorizado de Folios
         * 
         * @return
         *     possible object is
         *     {@link CAFType.DA.RNG }
         *     
         */
        public CAFType.DA.RNG getRNG() {
            return rng;
        }

        /**
         * Define el valor de la propiedad rng.
         * 
         * @param value
         *     allowed object is
         *     {@link CAFType.DA.RNG }
         *     
         * @see #getRNG()
         */
        public void setRNG(CAFType.DA.RNG value) {
            this.rng = value;
        }

        /**
         * Fecha Autorizacion en Formato
         * 									AAAA-MM-DD
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
         * Clave Publica RSA del
         * 										Solicitante
         * 
         * @return
         *     possible object is
         *     {@link CAFType.DA.RSAPK }
         *     
         */
        public CAFType.DA.RSAPK getRSAPK() {
            return rsapk;
        }

        /**
         * Define el valor de la propiedad rsapk.
         * 
         * @param value
         *     allowed object is
         *     {@link CAFType.DA.RSAPK }
         *     
         * @see #getRSAPK()
         */
        public void setRSAPK(CAFType.DA.RSAPK value) {
            this.rsapk = value;
        }

        /**
         * Clave Publica DSA del
         * 										Solicitante
         * 
         * @return
         *     possible object is
         *     {@link CAFType.DA.DSAPK }
         *     
         */
        public CAFType.DA.DSAPK getDSAPK() {
            return dsapk;
        }

        /**
         * Define el valor de la propiedad dsapk.
         * 
         * @param value
         *     allowed object is
         *     {@link CAFType.DA.DSAPK }
         *     
         * @see #getDSAPK()
         */
        public void setDSAPK(CAFType.DA.DSAPK value) {
            this.dsapk = value;
        }

        /**
         * Identificador de Llave
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
             * Modulo Primo
             * 
             */
            @XmlElement(name = "P", required = true)
            protected byte[] p;
            /**
             * Entero Divisor de P
             * 													- 1
             * 
             */
            @XmlElement(name = "Q", required = true)
            protected byte[] q;
            /**
             * Entero f(P, Q)
             * 
             */
            @XmlElement(name = "G", required = true)
            protected byte[] g;
            /**
             * G**X mod P
             * 
             */
            @XmlElement(name = "Y", required = true)
            protected byte[] y;

            /**
             * Modulo Primo
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
             * Entero Divisor de P
             * 													- 1
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
             * Entero f(P, Q)
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
             * G**X mod P
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
             * Folio Inicial (Desde)
             * 
             */
            @XmlElement(name = "D", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger d;
            /**
             * Folio Final (Hasta)
             * 
             */
            @XmlElement(name = "H", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger h;

            /**
             * Folio Inicial (Desde)
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
             * Folio Final (Hasta)
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
             * Modulo RSA
             * 
             */
            @XmlElement(name = "M", required = true)
            protected byte[] m;
            /**
             * Exponente RSA
             * 
             */
            @XmlElement(name = "E", required = true)
            protected byte[] e;

            /**
             * Modulo RSA
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
             * Exponente RSA
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
