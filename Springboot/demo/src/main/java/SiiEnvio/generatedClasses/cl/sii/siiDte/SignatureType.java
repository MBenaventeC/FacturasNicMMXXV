//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvio.generatedClasses.cl.sii.siiDte;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Firma Digital con Restricciones
 * 
 * <p>Clase Java para SignatureType complex type.</p>
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.</p>
 * 
 * <pre>{@code
 * <complexType name="SignatureType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="SignedInfo">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="CanonicalizationMethod">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="SignatureMethod">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="Algorithm" use="required">
 *                             <simpleType>
 *                               <restriction base="{http://www.w3.org/2001/XMLSchema}anyURI">
 *                                 <enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
 *                                 <enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
 *                               </restriction>
 *                             </simpleType>
 *                           </attribute>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="Reference">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="Transforms" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="Transform">
 *                                         <complexType>
 *                                           <complexContent>
 *                                             <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                                             </restriction>
 *                                           </complexContent>
 *                                         </complexType>
 *                                       </element>
 *                                     </sequence>
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="DigestMethod">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                           </sequence>
 *                           <attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="SignatureValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         <element name="KeyInfo">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="KeyValue">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <choice>
 *                             <element name="RSAKeyValue">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                       <element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                     </sequence>
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="DSAKeyValue">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                       <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                       <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                       <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                                     </sequence>
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </choice>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="X509Data">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
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
@XmlType(name = "SignatureType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
    "signedInfo",
    "signatureValue",
    "keyInfo"
})
public class SignatureType {

    /**
     * Descripcion de la Informacion Firmada y del Metodo de Firma
     * 
     */
    @XmlElement(name = "SignedInfo", required = true)
    protected SignatureType.SignedInfo signedInfo;
    /**
     * Valor de la Firma Digital
     * 
     */
    @XmlElement(name = "SignatureValue", required = true)
    protected byte[] signatureValue;
    /**
     * Informacion de Claves Publicas y Certificado
     * 
     */
    @XmlElement(name = "KeyInfo", required = true)
    protected SignatureType.KeyInfo keyInfo;

    /**
     * Descripcion de la Informacion Firmada y del Metodo de Firma
     * 
     * @return
     *     possible object is
     *     {@link SignatureType.SignedInfo }
     *     
     */
    public SignatureType.SignedInfo getSignedInfo() {
        return signedInfo;
    }

    /**
     * Define el valor de la propiedad signedInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType.SignedInfo }
     *     
     * @see #getSignedInfo()
     */
    public void setSignedInfo(SignatureType.SignedInfo value) {
        this.signedInfo = value;
    }

    /**
     * Valor de la Firma Digital
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignatureValue() {
        return signatureValue;
    }

    /**
     * Define el valor de la propiedad signatureValue.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     * @see #getSignatureValue()
     */
    public void setSignatureValue(byte[] value) {
        this.signatureValue = value;
    }

    /**
     * Informacion de Claves Publicas y Certificado
     * 
     * @return
     *     possible object is
     *     {@link SignatureType.KeyInfo }
     *     
     */
    public SignatureType.KeyInfo getKeyInfo() {
        return keyInfo;
    }

    /**
     * Define el valor de la propiedad keyInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType.KeyInfo }
     *     
     * @see #getKeyInfo()
     */
    public void setKeyInfo(SignatureType.KeyInfo value) {
        this.keyInfo = value;
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
     *         <element name="KeyValue">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <choice>
     *                   <element name="RSAKeyValue">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <sequence>
     *                             <element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                             <element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                           </sequence>
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="DSAKeyValue">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <sequence>
     *                             <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                             <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                             <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                             <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                           </sequence>
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </choice>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="X509Data">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
        "keyValue",
        "x509Data"
    })
    public static class KeyInfo {

        @XmlElement(name = "KeyValue", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.KeyInfo.KeyValue keyValue;
        /**
         * Informacion del Certificado Publico
         * 
         */
        @XmlElement(name = "X509Data", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.KeyInfo.X509Data x509Data;

        /**
         * Obtiene el valor de la propiedad keyValue.
         * 
         * @return
         *     possible object is
         *     {@link SignatureType.KeyInfo.KeyValue }
         *     
         */
        public SignatureType.KeyInfo.KeyValue getKeyValue() {
            return keyValue;
        }

        /**
         * Define el valor de la propiedad keyValue.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.KeyInfo.KeyValue }
         *     
         */
        public void setKeyValue(SignatureType.KeyInfo.KeyValue value) {
            this.keyValue = value;
        }

        /**
         * Informacion del Certificado Publico
         * 
         * @return
         *     possible object is
         *     {@link SignatureType.KeyInfo.X509Data }
         *     
         */
        public SignatureType.KeyInfo.X509Data getX509Data() {
            return x509Data;
        }

        /**
         * Define el valor de la propiedad x509Data.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.KeyInfo.X509Data }
         *     
         * @see #getX509Data()
         */
        public void setX509Data(SignatureType.KeyInfo.X509Data value) {
            this.x509Data = value;
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
         *       <choice>
         *         <element name="RSAKeyValue">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <sequence>
         *                   <element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                   <element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                 </sequence>
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="DSAKeyValue">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <sequence>
         *                   <element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                   <element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                   <element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                   <element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *                 </sequence>
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </choice>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "rsaKeyValue",
            "dsaKeyValue"
        })
        public static class KeyValue {

            /**
             * Informacion de Claves Publicas RSA
             * 
             */
            @XmlElement(name = "RSAKeyValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.KeyInfo.KeyValue.RSAKeyValue rsaKeyValue;
            /**
             * Informacion de Claves Publicas DSA
             * 
             */
            @XmlElement(name = "DSAKeyValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.KeyInfo.KeyValue.DSAKeyValue dsaKeyValue;

            /**
             * Informacion de Claves Publicas RSA
             * 
             * @return
             *     possible object is
             *     {@link SignatureType.KeyInfo.KeyValue.RSAKeyValue }
             *     
             */
            public SignatureType.KeyInfo.KeyValue.RSAKeyValue getRSAKeyValue() {
                return rsaKeyValue;
            }

            /**
             * Define el valor de la propiedad rsaKeyValue.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.KeyInfo.KeyValue.RSAKeyValue }
             *     
             * @see #getRSAKeyValue()
             */
            public void setRSAKeyValue(SignatureType.KeyInfo.KeyValue.RSAKeyValue value) {
                this.rsaKeyValue = value;
            }

            /**
             * Informacion de Claves Publicas DSA
             * 
             * @return
             *     possible object is
             *     {@link SignatureType.KeyInfo.KeyValue.DSAKeyValue }
             *     
             */
            public SignatureType.KeyInfo.KeyValue.DSAKeyValue getDSAKeyValue() {
                return dsaKeyValue;
            }

            /**
             * Define el valor de la propiedad dsaKeyValue.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.KeyInfo.KeyValue.DSAKeyValue }
             *     
             * @see #getDSAKeyValue()
             */
            public void setDSAKeyValue(SignatureType.KeyInfo.KeyValue.DSAKeyValue value) {
                this.dsaKeyValue = value;
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
            public static class DSAKeyValue {

                /**
                 * Modulo Primo
                 * 
                 */
                @XmlElement(name = "P", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] p;
                /**
                 * Entero Divisor de P - 1
                 * 
                 */
                @XmlElement(name = "Q", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] q;
                /**
                 * Entero f(P, Q)
                 * 
                 */
                @XmlElement(name = "G", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] g;
                /**
                 * G**X mod P
                 * 
                 */
                @XmlElement(name = "Y", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
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
                 * Entero Divisor de P - 1
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
             *         <element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
             *         <element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
                "modulus",
                "exponent"
            })
            public static class RSAKeyValue {

                /**
                 * Modulo Clave RSA
                 * 
                 */
                @XmlElement(name = "Modulus", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] modulus;
                /**
                 * Exponente Clave RSA
                 * 
                 */
                @XmlElement(name = "Exponent", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] exponent;

                /**
                 * Modulo Clave RSA
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getModulus() {
                    return modulus;
                }

                /**
                 * Define el valor de la propiedad modulus.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 * @see #getModulus()
                 */
                public void setModulus(byte[] value) {
                    this.modulus = value;
                }

                /**
                 * Exponente Clave RSA
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getExponent() {
                    return exponent;
                }

                /**
                 * Define el valor de la propiedad exponent.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 * @see #getExponent()
                 */
                public void setExponent(byte[] value) {
                    this.exponent = value;
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
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
            "x509Certificate"
        })
        public static class X509Data {

            /**
             * Certificado Publico
             * 
             */
            @XmlElement(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected byte[] x509Certificate;

            /**
             * Certificado Publico
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getX509Certificate() {
                return x509Certificate;
            }

            /**
             * Define el valor de la propiedad x509Certificate.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             * @see #getX509Certificate()
             */
            public void setX509Certificate(byte[] value) {
                this.x509Certificate = value;
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
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="CanonicalizationMethod">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="SignatureMethod">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <attribute name="Algorithm" use="required">
     *                   <simpleType>
     *                     <restriction base="{http://www.w3.org/2001/XMLSchema}anyURI">
     *                       <enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
     *                       <enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
     *                     </restriction>
     *                   </simpleType>
     *                 </attribute>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="Reference">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="Transforms" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <sequence>
     *                             <element name="Transform">
     *                               <complexType>
     *                                 <complexContent>
     *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *                                   </restriction>
     *                                 </complexContent>
     *                               </complexType>
     *                             </element>
     *                           </sequence>
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="DigestMethod">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                 </sequence>
     *                 <attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
        "canonicalizationMethod",
        "signatureMethod",
        "reference"
    })
    public static class SignedInfo {

        /**
         * Algoritmo de Canonicalizacion
         * 
         */
        @XmlElement(name = "CanonicalizationMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.CanonicalizationMethod canonicalizationMethod;
        /**
         * Algoritmo de Firma
         * 
         */
        @XmlElement(name = "SignatureMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.SignatureMethod signatureMethod;
        /**
         * Referencia a Elemento Firmado
         * 
         */
        @XmlElement(name = "Reference", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.Reference reference;

        /**
         * Algoritmo de Canonicalizacion
         * 
         * @return
         *     possible object is
         *     {@link SignatureType.SignedInfo.CanonicalizationMethod }
         *     
         */
        public SignatureType.SignedInfo.CanonicalizationMethod getCanonicalizationMethod() {
            return canonicalizationMethod;
        }

        /**
         * Define el valor de la propiedad canonicalizationMethod.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.CanonicalizationMethod }
         *     
         * @see #getCanonicalizationMethod()
         */
        public void setCanonicalizationMethod(SignatureType.SignedInfo.CanonicalizationMethod value) {
            this.canonicalizationMethod = value;
        }

        /**
         * Algoritmo de Firma
         * 
         * @return
         *     possible object is
         *     {@link SignatureType.SignedInfo.SignatureMethod }
         *     
         */
        public SignatureType.SignedInfo.SignatureMethod getSignatureMethod() {
            return signatureMethod;
        }

        /**
         * Define el valor de la propiedad signatureMethod.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.SignatureMethod }
         *     
         * @see #getSignatureMethod()
         */
        public void setSignatureMethod(SignatureType.SignedInfo.SignatureMethod value) {
            this.signatureMethod = value;
        }

        /**
         * Referencia a Elemento Firmado
         * 
         * @return
         *     possible object is
         *     {@link SignatureType.SignedInfo.Reference }
         *     
         */
        public SignatureType.SignedInfo.Reference getReference() {
            return reference;
        }

        /**
         * Define el valor de la propiedad reference.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.Reference }
         *     
         * @see #getReference()
         */
        public void setReference(SignatureType.SignedInfo.Reference value) {
            this.reference = value;
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
         *       <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CanonicalizationMethod {

            @XmlAttribute(name = "Algorithm", required = true)
            @XmlSchemaType(name = "anyURI")
            protected String algorithm;

            /**
             * Obtiene el valor de la propiedad algorithm.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAlgorithm() {
                if (algorithm == null) {
                    return "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
                } else {
                    return algorithm;
                }
            }

            /**
             * Define el valor de la propiedad algorithm.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAlgorithm(String value) {
                this.algorithm = value;
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
         *         <element name="Transforms" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <sequence>
         *                   <element name="Transform">
         *                     <complexType>
         *                       <complexContent>
         *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *                         </restriction>
         *                       </complexContent>
         *                     </complexType>
         *                   </element>
         *                 </sequence>
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="DigestMethod">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *       </sequence>
         *       <attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "transforms",
            "digestMethod",
            "digestValue"
        })
        public static class Reference {

            /**
             * Algoritmo de Transformacion
             * 
             */
            @XmlElement(name = "Transforms", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.SignedInfo.Reference.Transforms transforms;
            /**
             * Algoritmo de Digest
             * 
             */
            @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected SignatureType.SignedInfo.Reference.DigestMethod digestMethod;
            /**
             * Valor de Digest
             * 
             */
            @XmlElement(name = "DigestValue", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected byte[] digestValue;
            @XmlAttribute(name = "URI", required = true)
            @XmlSchemaType(name = "anyURI")
            protected String uri;

            /**
             * Algoritmo de Transformacion
             * 
             * @return
             *     possible object is
             *     {@link SignatureType.SignedInfo.Reference.Transforms }
             *     
             */
            public SignatureType.SignedInfo.Reference.Transforms getTransforms() {
                return transforms;
            }

            /**
             * Define el valor de la propiedad transforms.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.SignedInfo.Reference.Transforms }
             *     
             * @see #getTransforms()
             */
            public void setTransforms(SignatureType.SignedInfo.Reference.Transforms value) {
                this.transforms = value;
            }

            /**
             * Algoritmo de Digest
             * 
             * @return
             *     possible object is
             *     {@link SignatureType.SignedInfo.Reference.DigestMethod }
             *     
             */
            public SignatureType.SignedInfo.Reference.DigestMethod getDigestMethod() {
                return digestMethod;
            }

            /**
             * Define el valor de la propiedad digestMethod.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.SignedInfo.Reference.DigestMethod }
             *     
             * @see #getDigestMethod()
             */
            public void setDigestMethod(SignatureType.SignedInfo.Reference.DigestMethod value) {
                this.digestMethod = value;
            }

            /**
             * Valor de Digest
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getDigestValue() {
                return digestValue;
            }

            /**
             * Define el valor de la propiedad digestValue.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             * @see #getDigestValue()
             */
            public void setDigestValue(byte[] value) {
                this.digestValue = value;
            }

            /**
             * Obtiene el valor de la propiedad uri.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getURI() {
                return uri;
            }

            /**
             * Define el valor de la propiedad uri.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setURI(String value) {
                this.uri = value;
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
             *       <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DigestMethod {

                @XmlAttribute(name = "Algorithm", required = true)
                @XmlSchemaType(name = "anyURI")
                protected String algorithm;

                /**
                 * Obtiene el valor de la propiedad algorithm.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAlgorithm() {
                    if (algorithm == null) {
                        return "http://www.w3.org/2000/09/xmldsig#sha1";
                    } else {
                        return algorithm;
                    }
                }

                /**
                 * Define el valor de la propiedad algorithm.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAlgorithm(String value) {
                    this.algorithm = value;
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
             *         <element name="Transform">
             *           <complexType>
             *             <complexContent>
             *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
                "transform"
            })
            public static class Transforms {

                @XmlElement(name = "Transform", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected SignatureType.SignedInfo.Reference.Transforms.Transform transform;

                /**
                 * Obtiene el valor de la propiedad transform.
                 * 
                 * @return
                 *     possible object is
                 *     {@link SignatureType.SignedInfo.Reference.Transforms.Transform }
                 *     
                 */
                public SignatureType.SignedInfo.Reference.Transforms.Transform getTransform() {
                    return transform;
                }

                /**
                 * Define el valor de la propiedad transform.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link SignatureType.SignedInfo.Reference.Transforms.Transform }
                 *     
                 */
                public void setTransform(SignatureType.SignedInfo.Reference.Transforms.Transform value) {
                    this.transform = value;
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
                 *       <attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
                 *     </restriction>
                 *   </complexContent>
                 * </complexType>
                 * }</pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Transform {

                    @XmlAttribute(name = "Algorithm", required = true)
                    @XmlSchemaType(name = "anyURI")
                    protected String algorithm;

                    /**
                     * Obtiene el valor de la propiedad algorithm.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getAlgorithm() {
                        return algorithm;
                    }

                    /**
                     * Define el valor de la propiedad algorithm.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setAlgorithm(String value) {
                        this.algorithm = value;
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
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <attribute name="Algorithm" use="required">
         *         <simpleType>
         *           <restriction base="{http://www.w3.org/2001/XMLSchema}anyURI">
         *             <enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
         *             <enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
         *           </restriction>
         *         </simpleType>
         *       </attribute>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class SignatureMethod {

            @XmlAttribute(name = "Algorithm", required = true)
            protected String algorithm;

            /**
             * Obtiene el valor de la propiedad algorithm.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAlgorithm() {
                return algorithm;
            }

            /**
             * Define el valor de la propiedad algorithm.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAlgorithm(String value) {
                this.algorithm = value;
            }

        }

    }

}
