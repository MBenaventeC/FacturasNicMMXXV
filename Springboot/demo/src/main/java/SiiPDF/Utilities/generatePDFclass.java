package SiiPDF.Utilities;
import SiiBoleta.AUTORIZACION;
import SiiBoleta.DTEDefType;
import SiiBoleta.TED;
import SiiBoleta.FRMT;
import SiiBoleta.DD;

import jakarta.xml.bind.JAXBContext;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

//Imports para TED
import com.itextpdf.text.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.FOPException;

public class generatePDFclass{
    public static void generatePDF(InputStream xmlFile, InputStream xslFile,
                OutputStream pdfFile) throws FOPException, FileNotFoundException,
                TransformerException {
            FopFactory fopFactory = FopFactory.newInstance(new java.io.File(".").toURI());   

    		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
    		// configure foUserAgent as desired 

    		// Construct fop with desired output format
    		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent,
    				pdfFile);   

    		// Setup XSLT
    		TransformerFactory factory = TransformerFactory.newInstance();
    		Transformer transformer = factory.newTransformer(new StreamSource(
    				xslFile));  

    		// Set the value of a <param> in the stylesheet
    		transformer.setParameter("versionParam", "2.0");    

    		// Setup input for XSLT transformation
    		Source src = new StreamSource(xmlFile);
    		// Resulting SAX events (the generated FO) must be piped through to FOP
    		Result res = new SAXResult(fop.getDefaultHandler());    

    		// res = new StreamResult(new File("data/generated/sample-fo.xml"));    

    		// Start XSLT transformation and FOP processing
    		transformer.transform(src, res);
    }
	public static void generatePDFWithTED(InputStream xmlFile, InputStream xslFile,
                                         OutputStream pdfFile) throws Exception {
        
        // 1. Parsear el XML para extraer el TED
		JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("src/main/java/SiiPDF/Autorizacion.xml"); // 
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",41,1370000,"60910000-1","Dise�o",9990,"item1aaaaaaaaaaaaaaaaaaaa",cafFromXml);

        //DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

        // Se genera el codigo de barras
        String tedsinlen = serializeTedToString(ted);
        System.out.println("=== DEBUG TED BARCODE "+ tedsinlen.length() + "===");
        Image barcode = TED.makeBarcode(ted);

        // ✅ AGREGAR DEBUG
        //System.out.println("===  TED BARCODE LENGHT: ===");
        //System.out.println("Barcode width: " + barcode.getScaledWidth());
        //System.out.println("Barcode height: " + barcode.getScaledHeight());
        //System.out.println("Raw data length: " + (barcode.getRawData() != null ? barcode.getRawData().length : 0));
        //System.out.println("========================");
        
        // 3. Guardar imagen temporalmente
        //String tempImagePath = "test_files/Out/ted_temp/ted_barcode.png";
        //saveImageAsPNG(barcode, tempImagePath);
        
        // 4. Generar PDF con referencia a la imagen
        //generatePDF(xmlFile, xslFile, pdfFile);

        // 5. Borrar la imagen temporal
        //deleteTemporaryImage(tempImagePath);

        //--------NUEVO------- 4. Configurar FOP
        FopFactory fopFactory = FopFactory.newInstance(new java.io.File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfFile);
    
        // 5. Setup XSLT con URIResolver personalizado
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
    
        // 6. Configurar URIResolver que maneja la imagen
        transformer.setURIResolver(new TedImageURIResolver(barcode));
        
        // 7. Parámetros adicionales
        transformer.setParameter("versionParam", "2.0");
        transformer.setParameter("tedBarcodeAvailable", "true");
        
        // 8. Ejecutar transformación
        Source src = new StreamSource(xmlFile);
        Result res = new SAXResult(fop.getDefaultHandler());
        transformer.transform(src, res);
        
    }
    private static String serializeTedToString(DTEDefType.Documento.TED ted) throws Exception {
        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.class);
        jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        // Envolver en JAXBElement
        javax.xml.namespace.QName qname = new javax.xml.namespace.QName("", "TED");
        jakarta.xml.bind.JAXBElement<DTEDefType.Documento.TED> jaxbElement = 
            new jakarta.xml.bind.JAXBElement<>(qname, DTEDefType.Documento.TED.class, ted);

        java.io.StringWriter sw = new java.io.StringWriter();
        marshaller.marshal(jaxbElement, sw);

        String tedString = sw.toString()
                .replace("&#8220;", "&quot;")
                .replace("&#8216;", "&apos;");
        
        return tedString;
    }
    
    private static void saveImageAsPNG(Image itextImage, String path) throws IOException {
        // Método optimizado para códigos de barras PDF417
        byte[] rawData = itextImage.getRawData();
        int width = (int) itextImage.getScaledWidth();
        int height = (int) itextImage.getScaledHeight();
        
        // Crear BufferedImage optimizado para códigos de barras (monocromático)
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        
        if (rawData != null && rawData.length > 0) {
            // Procesamiento optimizado para datos binarios
            int index = 0;
            for (int y = 0; y < height && index < rawData.length; y++) {
                for (int x = 0; x < width && index < rawData.length; x++) {
                    // Para PDF417: 0 = negro (barra), 255 = blanco (espacio)
                    int pixel = (rawData[index] & 0xFF) == 0 ? 0x000000 : 0xFFFFFF;
                    bufferedImage.setRGB(x, y, pixel);
                    index++;
                }
            }
        }
        
        // Crear directorio y guardar
        File outputFile = new File(path);
        outputFile.getParentFile().mkdirs();
        
        // Guardar como PNG con compresión optimizada
        ImageIO.write(bufferedImage, "PNG", outputFile);
    }

    private static void deleteTemporaryImage(String pathString) {
        try {
            Path path = Paths.get(pathString);
            boolean deleted = Files.deleteIfExists(path);
            if (deleted) {
                System.out.println("Imagen temporal eliminada: " + pathString);
            } else {
                System.out.println("El archivo no existía: " + pathString);
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar la imagen: " + e.getMessage());
        }
    }

    // Ejemplo de uso
    public static void main(String[] args) throws Exception {
        try (
            InputStream xmlFile = new FileInputStream("test_files/In/BoletaExentaElectronica_1811253.xml");
            InputStream xslFile = new FileInputStream("src/main/java/SiiPDF/plantillas/plantilla_PDF_BExE.xsl");
            OutputStream pdfFile = new FileOutputStream("test_files/Out/test_pdf_BExE_1811_3.pdf")
        ) {
            generatePDFWithTED(xmlFile, xslFile, pdfFile);
            System.out.println("PDF generado correctamente.");
        }
    }

}