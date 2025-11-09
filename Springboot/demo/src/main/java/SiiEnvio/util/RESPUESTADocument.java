package SiiEnvio.util;
import SiiEnvio.generatedClasses.cl.sii.siiDte.RESPUESTA;

import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import javax.xml.namespace.QName;
import java.io.*;

/**
 * Documento wrapper para RESPUESTA usando XMLBeans
 */
public interface RESPUESTADocument extends XmlObject {
    
    public static final SchemaType type = (SchemaType) XmlBeans.typeSystemForClassLoader(
        RESPUESTADocument.class.getClassLoader(), 
        "schemaorg_apache_xmlbeans.system.sXMLSCHEMA"
    ).resolveHandle("respuesta");

    /**
     * Obtiene el elemento RESPUESTA
     */
    RESPUESTA getRESPUESTA();
    
    /**
     * Define el elemento RESPUESTA
     */
    void setRESPUESTA(RESPUESTA respuesta);
    
    /**
     * Añade un nuevo elemento RESPUESTA
     */
    RESPUESTA addNewRESPUESTA();

    /**
     * Clase Factory para crear instancias
     */
    public static final class Factory {
        
        private static final QName RESPUESTA_QNAME = new QName(
            "http://www.sii.cl/XMLSchema", 
            "RESPUESTA"
        );

        /**
         * Crea una nueva instancia vacía
         */
        public static RESPUESTADocument newInstance() {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().newInstance(
                type, 
                null
            );
        }

        /**
         * Crea una nueva instancia con opciones
         */
        public static RESPUESTADocument newInstance(XmlOptions options) {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().newInstance(
                type, 
                options
            );
        }

        /**
         * Parsea desde String
         */
        public static RESPUESTADocument parse(String xmlAsString) throws XmlException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                xmlAsString, 
                type, 
                null
            );
        }

        /**
         * Parsea desde String con opciones
         */
        public static RESPUESTADocument parse(String xmlAsString, XmlOptions options) 
                throws XmlException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                xmlAsString, 
                type, 
                options
            );
        }

        /**
         * Parsea desde File
         */
        public static RESPUESTADocument parse(File file) throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                file, 
                type, 
                null
            );
        }

        /**
         * Parsea desde File con opciones
         */
        public static RESPUESTADocument parse(File file, XmlOptions options) 
                throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                file, 
                type, 
                options
            );
        }

        /**
         * Parsea desde InputStream
         */
        public static RESPUESTADocument parse(InputStream is) 
                throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                is, 
                type, 
                null
            );
        }

        /**
         * Parsea desde InputStream con opciones
         */
        public static RESPUESTADocument parse(InputStream is, XmlOptions options) 
                throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                is, 
                type, 
                options
            );
        }

        /**
         * Parsea desde Reader
         */
        public static RESPUESTADocument parse(Reader r) throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                r, 
                type, 
                null
            );
        }

        /**
         * Parsea desde Reader con opciones
         */
        public static RESPUESTADocument parse(Reader r, XmlOptions options) 
                throws XmlException, IOException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                r, 
                type, 
                options
            );
        }

        /**
         * Parsea desde XMLStreamReader
         */
        public static RESPUESTADocument parse(javax.xml.stream.XMLStreamReader sr) 
                throws XmlException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                sr, 
                type, 
                null
            );
        }

        /**
         * Parsea desde XMLStreamReader con opciones
         */
        public static RESPUESTADocument parse(javax.xml.stream.XMLStreamReader sr, 
                XmlOptions options) throws XmlException {
            return (RESPUESTADocument) XmlBeans.getContextTypeLoader().parse(
                sr, 
                type, 
                options
            );
        }

        private Factory() { }
    }

    /**
     * Implementación concreta del documento
     */
    public static class RESPUESTADocumentImpl extends XmlComplexContentImpl 
            implements RESPUESTADocument {
        
        private static final long serialVersionUID = 1L;
        
        private RESPUESTA respuesta;

        public RESPUESTADocumentImpl(SchemaType sType) {
            super(sType);
        }

        @Override
        public RESPUESTA getRESPUESTA() {
            synchronized (monitor()) {
                check_orphaned();
                return respuesta;
            }
        }

        @Override
        public void setRESPUESTA(RESPUESTA newRespuesta) {
            synchronized (monitor()) {
                check_orphaned();
                this.respuesta = newRespuesta;
            }
        }

        @Override
        public RESPUESTA addNewRESPUESTA() {
            synchronized (monitor()) {
                check_orphaned();
                this.respuesta = new RESPUESTA();
                return this.respuesta;
            }
        }
    }
}
