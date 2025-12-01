package SiiEnvioApi.util;
import SiiEnvioApi.generatedClasses.cl.sii.siiDte.RECEPCIONDTE;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import java.io.*;

/**
* Wrapper para RECEPCIONDTE compatible con XMLBeans y JAXB
* Basado en el patrón de RESPUESTADocument
*/
public class RECEPCIONDTEDocument {

   private RECEPCIONDTE recepciondte;

   /**
    * Obtiene el elemento RECEPCIONDTE
    */
   public RECEPCIONDTE getRECEPCIONDTE() {
       return recepciondte;
   }

   /**
    * Define el elemento RECEPCIONDTE
    */
   public void setRECEPCIONDTE(RECEPCIONDTE recepciondte) {
       this.recepciondte = recepciondte;
   }

   /**
    * Clase Factory para mantener compatibilidad con XMLBeans
    */
   public static class Factory {

       private static JAXBContext jaxbContext;

       static {
           try {
               jaxbContext = JAXBContext.newInstance(RECEPCIONDTE.class);
           } catch (JAXBException e) {
               throw new RuntimeException("Error inicializando JAXB para RECEPCIONDTE", e);
           }
       }

       /**
        * Crea nueva instancia vacía
        */
       public static RECEPCIONDTEDocument newInstance() {
           RECEPCIONDTEDocument doc = new RECEPCIONDTEDocument();
           doc.setRECEPCIONDTE(new RECEPCIONDTE());
           return doc;
       }

       /**
        * Parsea desde String (ignora XmlOptions para compatibilidad)
        */
       public static RECEPCIONDTEDocument parse(String xmlAsString) throws XmlException {
           return parse(xmlAsString, null);
       }

       /**
        * Parsea desde String con opciones XMLBeans
        */
       public static RECEPCIONDTEDocument parse(String xmlAsString, XmlOptions options)
               throws XmlException {
           try {
               // Limpiar el XML de posibles caracteres problemáticos
               String cleanXml = xmlAsString
                   .replaceAll("&#13;", "")
                   .replaceAll("&#10;", "")
                   .trim();

               Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
               StringReader reader = new StringReader(cleanXml);

               RECEPCIONDTE recepcion = (RECEPCIONDTE) unmarshaller.unmarshal(reader);

               RECEPCIONDTEDocument doc = new RECEPCIONDTEDocument();
               doc.setRECEPCIONDTE(recepcion);

               return doc;
           } catch (JAXBException e) {
               throw new XmlException("Error parseando XML RECEPCIONDTE: " + e.getMessage(), e);
           }
       }

       /**
        * Parsea desde InputStream
        */
       public static RECEPCIONDTEDocument parse(InputStream is) throws XmlException, IOException {
           return parse(is, null);
       }

       /**
        * Parsea desde InputStream con opciones
        */
       public static RECEPCIONDTEDocument parse(InputStream is, XmlOptions options)
               throws XmlException, IOException {
           try {
               Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
               RECEPCIONDTE recepcion = (RECEPCIONDTE) unmarshaller.unmarshal(is);

               RECEPCIONDTEDocument doc = new RECEPCIONDTEDocument();
               doc.setRECEPCIONDTE(recepcion);

               return doc;
           } catch (JAXBException e) {
               throw new XmlException("Error parseando XML RECEPCIONDTE: " + e.getMessage(), e);
           }
       }

       /**
        * Parsea desde File
        */
       public static RECEPCIONDTEDocument parse(File file) throws XmlException, IOException {
           return parse(file, null);
       }

       /**
        * Parsea desde File con opciones
        */
       public static RECEPCIONDTEDocument parse(File file, XmlOptions options)
               throws XmlException, IOException {
           try (FileInputStream fis = new FileInputStream(file)) {
               return parse(fis, options);
           }
       }

       /**
        * Parsea desde Reader
        */
       public static RECEPCIONDTEDocument parse(Reader reader) throws XmlException, IOException {
           return parse(reader, null);
       }

       /**
        * Parsea desde Reader con opciones
        */
       public static RECEPCIONDTEDocument parse(Reader reader, XmlOptions options)
               throws XmlException, IOException {
           try {
               Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
               RECEPCIONDTE recepcion = (RECEPCIONDTE) unmarshaller.unmarshal(reader);

               RECEPCIONDTEDocument doc = new RECEPCIONDTEDocument();
               doc.setRECEPCIONDTE(recepcion);

               return doc;
           } catch (JAXBException e) {
               throw new XmlException("Error parseando XML RECEPCIONDTE: " + e.getMessage(), e);
           }
       }

       private Factory() { }
   }

   /**
    * Convierte el documento a String XML
    */
   public String xmlText() {
       return xmlText(null);
   }

   /**
    * Convierte el documento a String XML con opciones
    */
   public String xmlText(XmlOptions options) {
       try {
           StringWriter writer = new StringWriter();
           jakarta.xml.bind.Marshaller marshaller = Factory.jaxbContext.createMarshaller();
           marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
           marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
           marshaller.marshal(recepciondte, writer);
           return writer.toString();
       } catch (JAXBException e) {
           return "<error>Error generando XML: " + e.getMessage() + "</error>";
       }
   }

   /**
    * Guarda el documento en un OutputStream
    */
   public void save(OutputStream out) throws IOException {
       save(out, null);
   }

   /**
    * Guarda el documento en un OutputStream con opciones
    */
   public void save(OutputStream out, XmlOptions options) throws IOException {
       try {
           jakarta.xml.bind.Marshaller marshaller = Factory.jaxbContext.createMarshaller();
           marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
           marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
           marshaller.marshal(recepciondte, out);
       } catch (JAXBException e) {
           throw new IOException("Error guardando XML: " + e.getMessage(), e);
       }
   }

   /**
    * Guarda el documento en un File
    */
   public void save(File file) throws IOException {
       save(file, null);
   }

   /**
    * Guarda el documento en un File con opciones
    */
   public void save(File file, XmlOptions options) throws IOException {
       try (FileOutputStream fos = new FileOutputStream(file)) {
           save(fos, options);
       }
   }
}
