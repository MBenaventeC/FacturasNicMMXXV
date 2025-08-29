
package SiiTr;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Documento Tributario Electronico
 * 
 * <p>Java class for ReciboDefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReciboDefType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocumentoRecibo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TipoDoc" type="{http://www.sii.cl/SiiDte}DocType"/&gt;
 *                   &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
 *                   &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                   &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                   &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
 *                   &lt;element name="Recinto"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="80"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="RutFirma" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                   &lt;element name="Declaracion"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="256"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="TmstFirmaRecibo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" fixed="1.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReciboDefType", namespace = "http://www.sii.cl/SiiDte", propOrder = {
    "documentoRecibo",
    "signature"
})
public class   ReciboDefType {

    @XmlElement(name = "DocumentoRecibo", namespace = "http://www.sii.cl/SiiDte", required = true)
    protected ReciboDefType.DocumentoRecibo documentoRecibo;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "version", required = true)
    protected BigDecimal version;

    /**
     * Gets the value of the documentoRecibo property.
     * 
     * @return
     *     possible object is
     *     {@link ReciboDefType.DocumentoRecibo }
     *     
     */
    public ReciboDefType.DocumentoRecibo getDocumentoRecibo() {
        return documentoRecibo;
    }

    /**
     * Sets the value of the documentoRecibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReciboDefType.DocumentoRecibo }
     *     
     */
    public void setDocumentoRecibo(ReciboDefType.DocumentoRecibo value) {
        this.documentoRecibo = value;
    }

    /**
     * Firma Digital sobre Documento
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        if (version == null) {
            return new BigDecimal("1.0");
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
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
     *         &lt;element name="TipoDoc" type="{http://www.sii.cl/SiiDte}DocType"/&gt;
     *         &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
     *         &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *         &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *         &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
     *         &lt;element name="Recinto"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="80"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="RutFirma" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *         &lt;element name="Declaracion"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="256"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="TmstFirmaRecibo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tipoDoc",
        "folio",
        "fchEmis",
        "rutEmisor",
        "rutRecep",
        "mntTotal",
        "recinto",
        "rutFirma",
        "declaracion",
        "tmstFirmaRecibo"
    })
    public static class DocumentoRecibo {

        @XmlElement(name = "TipoDoc", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger tipoDoc;
        @XmlElement(name = "Folio", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger folio;
        @XmlElement(name = "FchEmis", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fchEmis;
        @XmlElement(name = "RUTEmisor", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected String rutEmisor;
        @XmlElement(name = "RUTRecep", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected String rutRecep;
        @XmlElement(name = "MntTotal", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger mntTotal;
        @XmlElement(name = "Recinto", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected String recinto;
        @XmlElement(name = "RutFirma", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected String rutFirma;
        @XmlElement(name = "Declaracion", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected String declaracion;
        @XmlElement(name = "TmstFirmaRecibo", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tmstFirmaRecibo;
        @XmlAttribute(name = "ID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Gets the value of the tipoDoc property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTipoDoc() {
            return tipoDoc;
        }

        /**
         * Sets the value of the tipoDoc property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTipoDoc(BigInteger value) {
            this.tipoDoc = value;
        }

        /**
         * Gets the value of the folio property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getFolio() {
            return folio;
        }

        /**
         * Sets the value of the folio property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setFolio(BigInteger value) {
            this.folio = value;
        }

        /**
         * Gets the value of the fchEmis property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFchEmis() {
            return fchEmis;
        }

        /**
         * Sets the value of the fchEmis property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFchEmis(XMLGregorianCalendar value) {
            this.fchEmis = value;
        }

        /**
         * Gets the value of the rutEmisor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRUTEmisor() {
            return rutEmisor;
        }

        /**
         * Sets the value of the rutEmisor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRUTEmisor(String value) {
            this.rutEmisor = value;
        }

        /**
         * Gets the value of the rutRecep property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRUTRecep() {
            return rutRecep;
        }

        /**
         * Sets the value of the rutRecep property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRUTRecep(String value) {
            this.rutRecep = value;
        }

        /**
         * Gets the value of the mntTotal property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMntTotal() {
            return mntTotal;
        }

        /**
         * Sets the value of the mntTotal property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMntTotal(BigInteger value) {
            this.mntTotal = value;
        }

        /**
         * Gets the value of the recinto property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRecinto() {
            return recinto;
        }

        /**
         * Sets the value of the recinto property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRecinto(String value) {
            this.recinto = value;
        }

        /**
         * Gets the value of the rutFirma property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRutFirma() {
            return rutFirma;
        }

        /**
         * Sets the value of the rutFirma property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRutFirma(String value) {
            this.rutFirma = value;
        }

        /**
         * Gets the value of the declaracion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDeclaracion() {
            return declaracion;
        }

        /**
         * Sets the value of the declaracion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDeclaracion(String value) {
            this.declaracion = value;
        }

        /**
         * Gets the value of the tmstFirmaRecibo property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTmstFirmaRecibo() {
            return tmstFirmaRecibo;
        }

        /**
         * Sets the value of the tmstFirmaRecibo property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTmstFirmaRecibo(XMLGregorianCalendar value) {
            this.tmstFirmaRecibo = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setID(String value) {
            this.id = value;
        }

    }

}
