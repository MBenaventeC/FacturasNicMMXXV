
package SiiTr;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Firma Digital con Restricciones
 * 
 * <p>Java class for SignatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignatureType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignedInfo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CanonicalizationMethod"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="SignatureMethod"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Algorithm" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyURI"&gt;
 *                                 &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/&gt;
 *                                 &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Reference"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Transforms" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="Transform"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DigestMethod"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SignatureValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="KeyInfo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="KeyValue"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;choice&gt;
 *                             &lt;element name="RSAKeyValue"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                       &lt;element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DSAKeyValue"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                       &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                       &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                       &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/choice&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="X509Data"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
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

    @XmlElement(name = "SignedInfo", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType.SignedInfo signedInfo;
    @XmlElement(name = "SignatureValue", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] signatureValue;
    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType.KeyInfo keyInfo;

    /**
     * Gets the value of the signedInfo property.
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
     * Sets the value of the signedInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType.SignedInfo }
     *     
     */
    public void setSignedInfo(SignatureType.SignedInfo value) {
        this.signedInfo = value;
    }

    /**
     * Gets the value of the signatureValue property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignatureValue() {
        return signatureValue;
    }

    /**
     * Sets the value of the signatureValue property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSignatureValue(byte[] value) {
        this.signatureValue = value;
    }

    /**
     * Gets the value of the keyInfo property.
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
     * Sets the value of the keyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType.KeyInfo }
     *     
     */
    public void setKeyInfo(SignatureType.KeyInfo value) {
        this.keyInfo = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="KeyValue"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;choice&gt;
     *                   &lt;element name="RSAKeyValue"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                             &lt;element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DSAKeyValue"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                             &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                             &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                             &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/choice&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="X509Data"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
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
        @XmlElement(name = "X509Data", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.KeyInfo.X509Data x509Data;

        /**
         * Gets the value of the keyValue property.
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
         * Sets the value of the keyValue property.
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
         * Gets the value of the x509Data property.
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
         * Sets the value of the x509Data property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.KeyInfo.X509Data }
         *     
         */
        public void setX509Data(SignatureType.KeyInfo.X509Data value) {
            this.x509Data = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;choice&gt;
         *         &lt;element name="RSAKeyValue"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                   &lt;element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DSAKeyValue"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                   &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                   &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                   &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/choice&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "rsaKeyValue",
            "dsaKeyValue"
        })
        public static class KeyValue {

            @XmlElement(name = "RSAKeyValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.KeyInfo.KeyValue.RSAKeyValue rsaKeyValue;
            @XmlElement(name = "DSAKeyValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.KeyInfo.KeyValue.DSAKeyValue dsaKeyValue;

            /**
             * Gets the value of the rsaKeyValue property.
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
             * Sets the value of the rsaKeyValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.KeyInfo.KeyValue.RSAKeyValue }
             *     
             */
            public void setRSAKeyValue(SignatureType.KeyInfo.KeyValue.RSAKeyValue value) {
                this.rsaKeyValue = value;
            }

            /**
             * Gets the value of the dsaKeyValue property.
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
             * Sets the value of the dsaKeyValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.KeyInfo.KeyValue.DSAKeyValue }
             *     
             */
            public void setDSAKeyValue(SignatureType.KeyInfo.KeyValue.DSAKeyValue value) {
                this.dsaKeyValue = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *         &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *         &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
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

                @XmlElement(name = "P", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] p;
                @XmlElement(name = "Q", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] q;
                @XmlElement(name = "G", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] g;
                @XmlElement(name = "Y", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] y;

                /**
                 * Gets the value of the p property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getP() {
                    return p;
                }

                /**
                 * Sets the value of the p property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setP(byte[] value) {
                    this.p = value;
                }

                /**
                 * Gets the value of the q property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getQ() {
                    return q;
                }

                /**
                 * Sets the value of the q property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setQ(byte[] value) {
                    this.q = value;
                }

                /**
                 * Gets the value of the g property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getG() {
                    return g;
                }

                /**
                 * Sets the value of the g property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setG(byte[] value) {
                    this.g = value;
                }

                /**
                 * Gets the value of the y property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getY() {
                    return y;
                }

                /**
                 * Sets the value of the y property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setY(byte[] value) {
                    this.y = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="Modulus" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *         &lt;element name="Exponent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "modulus",
                "exponent"
            })
            public static class RSAKeyValue {

                @XmlElement(name = "Modulus", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] modulus;
                @XmlElement(name = "Exponent", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
                protected byte[] exponent;

                /**
                 * Gets the value of the modulus property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getModulus() {
                    return modulus;
                }

                /**
                 * Sets the value of the modulus property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setModulus(byte[] value) {
                    this.modulus = value;
                }

                /**
                 * Gets the value of the exponent property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getExponent() {
                    return exponent;
                }

                /**
                 * Sets the value of the exponent property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setExponent(byte[] value) {
                    this.exponent = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "x509Certificate"
        })
        public static class X509Data {

            @XmlElement(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected byte[] x509Certificate;

            /**
             * Gets the value of the x509Certificate property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getX509Certificate() {
                return x509Certificate;
            }

            /**
             * Sets the value of the x509Certificate property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setX509Certificate(byte[] value) {
                this.x509Certificate = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="CanonicalizationMethod"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="SignatureMethod"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Algorithm" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyURI"&gt;
     *                       &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/&gt;
     *                       &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Reference"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Transforms" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="Transform"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DigestMethod"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
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

        @XmlElement(name = "CanonicalizationMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.CanonicalizationMethod canonicalizationMethod;
        @XmlElement(name = "SignatureMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.SignatureMethod signatureMethod;
        @XmlElement(name = "Reference", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
        protected SignatureType.SignedInfo.Reference reference;

        /**
         * Gets the value of the canonicalizationMethod property.
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
         * Sets the value of the canonicalizationMethod property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.CanonicalizationMethod }
         *     
         */
        public void setCanonicalizationMethod(SignatureType.SignedInfo.CanonicalizationMethod value) {
            this.canonicalizationMethod = value;
        }

        /**
         * Gets the value of the signatureMethod property.
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
         * Sets the value of the signatureMethod property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.SignatureMethod }
         *     
         */
        public void setSignatureMethod(SignatureType.SignedInfo.SignatureMethod value) {
            this.signatureMethod = value;
        }

        /**
         * Gets the value of the reference property.
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
         * Sets the value of the reference property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignatureType.SignedInfo.Reference }
         *     
         */
        public void setReference(SignatureType.SignedInfo.Reference value) {
            this.reference = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
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
             * Gets the value of the algorithm property.
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
             * Sets the value of the algorithm property.
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="Transforms" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="Transform"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DigestMethod"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DigestValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
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

            @XmlElement(name = "Transforms", namespace = "http://www.w3.org/2000/09/xmldsig#")
            protected SignatureType.SignedInfo.Reference.Transforms transforms;
            @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected SignatureType.SignedInfo.Reference.DigestMethod digestMethod;
            @XmlElement(name = "DigestValue", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
            protected byte[] digestValue;
            @XmlAttribute(name = "URI", required = true)
            @XmlSchemaType(name = "anyURI")
            protected String uri;

            /**
             * Gets the value of the transforms property.
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
             * Sets the value of the transforms property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.SignedInfo.Reference.Transforms }
             *     
             */
            public void setTransforms(SignatureType.SignedInfo.Reference.Transforms value) {
                this.transforms = value;
            }

            /**
             * Gets the value of the digestMethod property.
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
             * Sets the value of the digestMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignatureType.SignedInfo.Reference.DigestMethod }
             *     
             */
            public void setDigestMethod(SignatureType.SignedInfo.Reference.DigestMethod value) {
                this.digestMethod = value;
            }

            /**
             * Gets the value of the digestValue property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getDigestValue() {
                return digestValue;
            }

            /**
             * Sets the value of the digestValue property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setDigestValue(byte[] value) {
                this.digestValue = value;
            }

            /**
             * Gets the value of the uri property.
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
             * Sets the value of the uri property.
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
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" fixed="http://www.w3.org/2000/09/xmldsig#sha1" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
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
                 * Gets the value of the algorithm property.
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
                 * Sets the value of the algorithm property.
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
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="Transform"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
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
                 * Gets the value of the transform property.
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
                 * Sets the value of the transform property.
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
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
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
                     * Gets the value of the algorithm property.
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
                     * Sets the value of the algorithm property.
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="Algorithm" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyURI"&gt;
         *             &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/&gt;
         *             &lt;enumeration value="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class SignatureMethod {

            @XmlAttribute(name = "Algorithm", required = true)
            protected String algorithm;

            /**
             * Gets the value of the algorithm property.
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
             * Sets the value of the algorithm property.
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
