//package SiiEnvio.util;
//
//import SiiEnvioApi.generatedClasses.cl.sii.siiDte.RESPUESTA;
//import jakarta.xml.bind.JAXBContext;
//import jakarta.xml.bind.JAXBException;
//import jakarta.xml.bind.Unmarshaller;
//import org.apache.xmlbeans.XmlException;
//import org.apache.xmlbeans.XmlOptions;
//import java.io.*;
//
///**
// * Wrapper para RESPUESTA compatible con XMLBeans API pero usando JAXB internamente
// * Basado en el patrón de GetTokenDocument
// */
//public class RESPUESTADocument {
//
//    private RESPUESTA respuesta;
//
//    /**
//     * Obtiene el elemento RESPUESTA
//     */
//    public RESPUESTA getRESPUESTA() {
//        return respuesta;
//    }
//
//    /**
//     * Define el elemento RESPUESTA
//     */
//    public void setRESPUESTA(RESPUESTA respuesta) {
//        this.respuesta = respuesta;
//    }
//
//    /**
//     * Clase Factory para mantener compatibilidad con XMLBeans
//     */
//    public static class Factory {
//
//        private static JAXBContext jaxbContext;
//
//        static {
//            try {
//                jaxbContext = JAXBContext.newInstance(RESPUESTA.class);
//            } catch (JAXBException e) {
//                throw new RuntimeException("Error inicializando JAXB para RESPUESTA", e);
//            }
//        }
//
//        /**
//         * Crea nueva instancia vacía
//         */
//        public static RESPUESTADocument newInstance() {
//            RESPUESTADocument doc = new RESPUESTADocument();
//            doc.setRESPUESTA(new RESPUESTA());
//            return doc;
//        }
//
//        /**
//         * Parsea desde String (ignora XmlOptions para compatibilidad)
//         */
//        public static RESPUESTADocument parse(String xmlAsString) throws XmlException {
//            return parse(xmlAsString, null);
//        }
//
//        /**
//         * Parsea desde String con opciones XMLBeans
//         */
//        public static RESPUESTADocument parse(String xmlAsString, XmlOptions options)
//                throws XmlException {
//            try {
//                // Limpiar el XML de posibles caracteres problemáticos
//                String cleanXml = xmlAsString
//                    .replaceAll("&#13;", "")
//                    .replaceAll("&#10;", "")
//                    .trim();
//
//                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                StringReader reader = new StringReader(cleanXml);
//
//                RESPUESTA respuesta = (RESPUESTA) unmarshaller.unmarshal(reader);
//
//                RESPUESTADocument doc = new RESPUESTADocument();
//                doc.setRESPUESTA(respuesta);
//
//                return doc;
//            } catch (JAXBException e) {
//                throw new XmlException("Error parseando XML RESPUESTA: " + e.getMessage(), e);
//            }
//        }
//
//        /**
//         * Parsea desde InputStream con opciones
//         */
//        public static RESPUESTADocument parse(InputStream is, XmlOptions options)
//                throws XmlException, IOException {
//            try {
//                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                RESPUESTA respuesta = (RESPUESTA) unmarshaller.unmarshal(is);
//
//                RESPUESTADocument doc = new RESPUESTADocument();
//                doc.setRESPUESTA(respuesta);
//
//                return doc;
//            } catch (JAXBException e) {
//                throw new XmlException("Error parseando XML RESPUESTA: " + e.getMessage(), e);
//            }
//        }
//
//        /**
//         * Parsea desde Reader con opciones
//         */
//        public static RESPUESTADocument parse(Reader reader, XmlOptions options)
//                throws XmlException, IOException {
//            try {
//                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                RESPUESTA respuesta = (RESPUESTA) unmarshaller.unmarshal(reader);
//
//                RESPUESTADocument doc = new RESPUESTADocument();
//                doc.setRESPUESTA(respuesta);
//
//                return doc;
//            } catch (JAXBException e) {
//                throw new XmlException("Error parseando XML RESPUESTA: " + e.getMessage(), e);
//            }
//        }
//
//        private Factory() { }
//    }
//
//    /**
//     * Convierte el documento a String XML
//     */
//    public String xmlText() {
//        return xmlText(null);
//    }
//
//    /**
//     * Convierte el documento a String XML con opciones
//     */
//    public String xmlText(XmlOptions options) {
//        try {
//            StringWriter writer = new StringWriter();
//            jakarta.xml.bind.Marshaller marshaller = Factory.jaxbContext.createMarshaller();
//            marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.marshal(respuesta, writer);
//            return writer.toString();
//        } catch (JAXBException e) {
//            return "<error>Error generando XML: " + e.getMessage() + "</error>";
//        }
//    }
//}
//