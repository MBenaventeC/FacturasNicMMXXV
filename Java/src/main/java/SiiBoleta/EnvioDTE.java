
package SiiBoleta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="SetDTE"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Caratula"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="RutEmisor"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}RUTType"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RutEnvia" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                             &lt;element name="RutReceptor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                             &lt;element name="FchResol" type="{http://www.sii.cl/SiiDte}FechaType"/&gt;
 *                             &lt;element name="NroResol" type="{http://www.sii.cl/SiiDte}NroResolType"/&gt;
 *                             &lt;element name="TmstFirmaEnv" type="{http://www.sii.cl/SiiDte}FechaHoraType"/&gt;
 *                             &lt;element name="SubTotDTE" maxOccurs="20"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="TpoDTE" type="{http://www.sii.cl/SiiDte}DOCType"/&gt;
 *                                       &lt;element name="NroDTE" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" fixed="1.0" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element ref="{http://www.sii.cl/SiiDte}DTE" maxOccurs="2000"/&gt;
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
@XmlType(name = "", propOrder = {
    "setDTE"/*,
    "signature"*/
})
@XmlRootElement(name = "EnvioDTE", namespace = "http://www.sii.cl/SiiDte")
public class EnvioDTE {

    @XmlElement(name = "SetDTE", namespace = "http://www.sii.cl/SiiDte", required = true)
    protected EnvioDTE.SetDTE setDTE;
    //@XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    @XmlTransient
    protected SiiBoleta.SignatureType signature;
    @XmlAttribute(name = "version", required = true)
    protected BigDecimal version;

    /**
     * Gets the value of the setDTE property.
     *
     * @return
     *     possible object is
     *     {@link EnvioDTE.SetDTE }
     *
     */
    public EnvioDTE.SetDTE getSetDTE() {
        return setDTE;
    }

    /**
     * Sets the value of the setDTE property.
     *
     * @param value
     *     allowed object is
     *     {@link EnvioDTE.SetDTE }
     *
     */
    public void setSetDTE(EnvioDTE.SetDTE value) {
        this.setDTE = value;
    }

    /**
     * Firma Digital sobre SetDTE
     *
     * @return
     *     possible object is
     *     {@link SiiBoleta.SignatureType }
     *
     */
    public SiiBoleta.SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     *
     * @param value
     *     allowed object is
     *     {@link SiiBoleta.SignatureType }
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
     *         &lt;element name="Caratula"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="RutEmisor"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}RUTType"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RutEnvia" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                   &lt;element name="RutReceptor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                   &lt;element name="FchResol" type="{http://www.sii.cl/SiiDte}FechaType"/&gt;
     *                   &lt;element name="NroResol" type="{http://www.sii.cl/SiiDte}NroResolType"/&gt;
     *                   &lt;element name="TmstFirmaEnv" type="{http://www.sii.cl/SiiDte}FechaHoraType"/&gt;
     *                   &lt;element name="SubTotDTE" maxOccurs="20"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="TpoDTE" type="{http://www.sii.cl/SiiDte}DOCType"/&gt;
     *                             &lt;element name="NroDTE" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" fixed="1.0" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element ref="{http://www.sii.cl/SiiDte}DTE" maxOccurs="2000"/&gt;
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
    @XmlType(name = "SetDTE", propOrder = {
        "caratula",
        "dte"
    })
    public static class SetDTE {

        @XmlElement(name = "Caratula", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected EnvioDTE.SetDTE.Caratula caratula;
        @XmlElement(name = "DTE"/*, namespace = "http://www.sii.cl/SiiDte"*/, required = true)
        protected List<SiiBoleta.DTEDefType> dte;
        @XmlAttribute(name = "ID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Gets the value of the caratula property.
         *
         * @return
         *     possible object is
         *     {@link EnvioDTE.SetDTE.Caratula }
         *
         */
        public EnvioDTE.SetDTE.Caratula getCaratula() {
            return caratula;
        }

        /**
         * Sets the value of the caratula property.
         *
         * @param value
         *     allowed object is
         *     {@link EnvioDTE.SetDTE.Caratula }
         *
         */
        public void setCaratula(EnvioDTE.SetDTE.Caratula value) {
            this.caratula = value;
        }

        /**
         * Documento Tributario Electronico Gets the value of the dte property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dte property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDTE().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SiiBoleta.DTEDefType }
         *
         *
         */
        public List<SiiBoleta.DTEDefType> getDTE() {
            if (dte == null) {
                dte = new ArrayList<DTEDefType>();
            }
            return this.dte;
        }

        public void setDteList(List<SiiBoleta.DTEDefType> dteList) {
            dte = dteList;
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
         *         &lt;element name="RutEmisor"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}RUTType"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RutEnvia" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *         &lt;element name="RutReceptor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *         &lt;element name="FchResol" type="{http://www.sii.cl/SiiDte}FechaType"/&gt;
         *         &lt;element name="NroResol" type="{http://www.sii.cl/SiiDte}NroResolType"/&gt;
         *         &lt;element name="TmstFirmaEnv" type="{http://www.sii.cl/SiiDte}FechaHoraType"/&gt;
         *         &lt;element name="SubTotDTE" maxOccurs="20"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="TpoDTE" type="{http://www.sii.cl/SiiDte}DOCType"/&gt;
         *                   &lt;element name="NroDTE" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
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
        @XmlType(name = "", propOrder = {
            "rutEmisor",
            "rutEnvia",
            "rutReceptor",
            "fchResol",
            "nroResol",
            "tmstFirmaEnv",
            "subTotDTE"
        })
        public static class Caratula {

            @XmlElement(name = "RutEmisor", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String rutEmisor;
            @XmlElement(name = "RutEnvia", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String rutEnvia;
            @XmlElement(name = "RutReceptor", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String rutReceptor;
            @XmlElement(name = "FchResol", namespace = "http://www.sii.cl/SiiDte", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fchResol;
            @XmlElement(name = "NroResol", namespace = "http://www.sii.cl/SiiDte", required = true)
            @XmlSchemaType(name = "nonNegativeInteger")
            protected BigInteger nroResol;
            @XmlElement(name = "TmstFirmaEnv", namespace = "http://www.sii.cl/SiiDte", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar tmstFirmaEnv;
            @XmlElement(name = "SubTotDTE", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTE;
            @XmlAttribute(name = "version", required = true)
            protected BigDecimal version;

            /**
             * Gets the value of the rutEmisor property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRutEmisor() {
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
            public void setRutEmisor(String value) {
                this.rutEmisor = value;
            }

            /**
             * Gets the value of the rutEnvia property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRutEnvia() {
                return rutEnvia;
            }

            /**
             * Sets the value of the rutEnvia property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRutEnvia(String value) {
                this.rutEnvia = value;
            }

            /**
             * Gets the value of the rutReceptor property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRutReceptor() {
                return rutReceptor;
            }

            /**
             * Sets the value of the rutReceptor property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRutReceptor(String value) {
                this.rutReceptor = value;
            }

            /**
             * Gets the value of the fchResol property.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getFchResol() {
                return fchResol;
            }

            /**
             * Sets the value of the fchResol property.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setFchResol(XMLGregorianCalendar value) {
                this.fchResol = value;
            }

            /**
             * Gets the value of the nroResol property.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getNroResol() {
                return nroResol;
            }

            /**
             * Sets the value of the nroResol property.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setNroResol(BigInteger value) {
                this.nroResol = value;
            }

            /**
             * Gets the value of the tmstFirmaEnv property.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getTmstFirmaEnv() {
                return tmstFirmaEnv;
            }

            /**
             * Sets the value of the tmstFirmaEnv property.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setTmstFirmaEnv(XMLGregorianCalendar value) {
                this.tmstFirmaEnv = value;
            }

            /**
             * Gets the value of the subTotDTE property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the subTotDTE property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSubTotDTE().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link EnvioDTE.SetDTE.Caratula.SubTotDTE }
             *
             *
             */
            public List<EnvioDTE.SetDTE.Caratula.SubTotDTE> getSubTotDTE() {
                if (subTotDTE == null) {
                    subTotDTE = new ArrayList<EnvioDTE.SetDTE.Caratula.SubTotDTE>();
                }
                return this.subTotDTE;
            }

            public void addSubTotDTE(EnvioDTE.SetDTE.Caratula.SubTotDTE subTot) {
                subTotDTE.add(subTot);
            }

            public void setSubTotDTE(List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTot) {
                subTotDTE = subTot;
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
             *         &lt;element name="TpoDTE" type="{http://www.sii.cl/SiiDte}DOCType"/&gt;
             *         &lt;element name="NroDTE" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
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
                "tpoDTE",
                "nroDTE"
            })
            public static class SubTotDTE {

                @XmlElement(name = "TpoDTE", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger tpoDTE;
                @XmlElement(name = "NroDTE", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger nroDTE;

                /**
                 * Gets the value of the tpoDTE property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getTpoDTE() {
                    return tpoDTE;
                }

                /**
                 * Sets the value of the tpoDTE property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setTpoDTE(BigInteger value) {
                    this.tpoDTE = value;
                }

                /**
                 * Gets the value of the nroDTE property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getNroDTE() {
                    return nroDTE;
                }

                /**
                 * Sets the value of the nroDTE property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setNroDTE(BigInteger value) {
                    this.nroDTE = value;
                }

            }

        }

    }

}
