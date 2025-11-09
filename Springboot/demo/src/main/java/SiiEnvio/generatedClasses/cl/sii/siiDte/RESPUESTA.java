//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package SiiEnvio.generatedClasses.cl.sii.siiDte;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
 *         <element name="RESP_BODY">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <choice>
 *                   <element name="TOKEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SEMILLA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 </choice>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="RESP_HDR">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   <choice>
 *                     <element name="GLOSA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     <sequence>
 *                       <element name="GLOSA_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                       <element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                       <element name="NUM_ATENCION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     </sequence>
 *                   </choice>
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
    "respbody",
    "resphdr"
})
@XmlRootElement(name = "RESPUESTA", namespace = "http://www.sii.cl/XMLSchema")
public class RESPUESTA {

    @XmlElement(name = "RESP_BODY", namespace = "http://www.sii.cl/XMLSchema", required = true)
    protected RESPUESTA.RESPBODY respbody;
    @XmlElement(name = "RESP_HDR", namespace = "http://www.sii.cl/XMLSchema", required = true)
    protected RESPUESTA.RESPHDR resphdr;

    /**
     * Obtiene el valor de la propiedad respbody.
     * 
     * @return
     *     possible object is
     *     {@link RESPUESTA.RESPBODY }
     *     
     */
    public RESPUESTA.RESPBODY getRESPBODY() {
        return respbody;
    }

    /**
     * Define el valor de la propiedad respbody.
     * 
     * @param value
     *     allowed object is
     *     {@link RESPUESTA.RESPBODY }
     *     
     */
    public void setRESPBODY(RESPUESTA.RESPBODY value) {
        this.respbody = value;
    }

    /**
     * Obtiene el valor de la propiedad resphdr.
     * 
     * @return
     *     possible object is
     *     {@link RESPUESTA.RESPHDR }
     *     
     */
    public RESPUESTA.RESPHDR getRESPHDR() {
        return resphdr;
    }

    /**
     * Define el valor de la propiedad resphdr.
     * 
     * @param value
     *     allowed object is
     *     {@link RESPUESTA.RESPHDR }
     *     
     */
    public void setRESPHDR(RESPUESTA.RESPHDR value) {
        this.resphdr = value;
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
     *         <element name="TOKEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SEMILLA" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "token",
        "semilla"
    })
    public static class RESPBODY {

        @XmlElement(name = "TOKEN", namespace = "http://www.sii.cl/XMLSchema")
        protected String token;
        @XmlElement(name = "SEMILLA", namespace = "http://www.sii.cl/XMLSchema")
        protected String semilla;

        /**
         * Obtiene el valor de la propiedad token.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOKEN() {
            return token;
        }

        /**
         * Define el valor de la propiedad token.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOKEN(String value) {
            this.token = value;
        }

        /**
         * Obtiene el valor de la propiedad semilla.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSEMILLA() {
            return semilla;
        }

        /**
         * Define el valor de la propiedad semilla.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSEMILLA(String value) {
            this.semilla = value;
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
     *         <element name="ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         <choice>
     *           <element name="GLOSA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *           <sequence>
     *             <element name="GLOSA_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *             <element name="ERR_CODE" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *             <element name="NUM_ATENCION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *           </sequence>
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
        "estado",
        "glosa",
        "glosaestado",
        "errcode",
        "numatencion"
    })
    public static class RESPHDR {

        @XmlElement(name = "ESTADO", namespace = "http://www.sii.cl/XMLSchema")
        protected int estado;
        @XmlElement(name = "GLOSA", namespace = "http://www.sii.cl/XMLSchema")
        protected String glosa;
        @XmlElement(name = "GLOSA_ESTADO", namespace = "http://www.sii.cl/XMLSchema")
        protected String glosaestado;
        @XmlElement(name = "ERR_CODE", namespace = "http://www.sii.cl/XMLSchema")
        protected Integer errcode;
        @XmlElement(name = "NUM_ATENCION", namespace = "http://www.sii.cl/XMLSchema")
        protected String numatencion;

        /**
         * Obtiene el valor de la propiedad estado.
         * 
         */
        public int getESTADO() {
            return estado;
        }

        /**
         * Define el valor de la propiedad estado.
         * 
         */
        public void setESTADO(int value) {
            this.estado = value;
        }

        /**
         * Obtiene el valor de la propiedad glosa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGLOSA() {
            return glosa;
        }

        /**
         * Define el valor de la propiedad glosa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGLOSA(String value) {
            this.glosa = value;
        }

        /**
         * Obtiene el valor de la propiedad glosaestado.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGLOSAESTADO() {
            return glosaestado;
        }

        /**
         * Define el valor de la propiedad glosaestado.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGLOSAESTADO(String value) {
            this.glosaestado = value;
        }

        /**
         * Obtiene el valor de la propiedad errcode.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getERRCODE() {
            return errcode;
        }

        /**
         * Define el valor de la propiedad errcode.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setERRCODE(Integer value) {
            this.errcode = value;
        }

        /**
         * Obtiene el valor de la propiedad numatencion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNUMATENCION() {
            return numatencion;
        }

        /**
         * Define el valor de la propiedad numatencion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNUMATENCION(String value) {
            this.numatencion = value;
        }

    }

}
