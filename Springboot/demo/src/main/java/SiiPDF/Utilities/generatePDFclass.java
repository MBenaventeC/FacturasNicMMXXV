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
import com.itextpdf.text.pdf.BarcodePDF417;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
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
                                         OutputStream pdfFile,DTEDefType.Documento.TED ted) throws Exception {
        
        // Parseamos el XML para extraer el TED
//		JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
//        File cafFile = new File("src/main/java/SiiPDF/Autorizacion.xml"); //
//        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
//        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
//        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",41,1370000,"60910000-1","Dise�o",9990,"item1aaa",cafFromXml);
//        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
//        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);
        
        // Creamos imagen del timbre
        BarcodePDF417 barcode = TED.makeBarcode(ted);

        //Crear imagen awt
        java.awt.Image awtImage = barcode.createAwtImage(java.awt.Color.BLACK, java.awt.Color.WHITE);
        
        // Convertir a bufferedImage
        BufferedImage bufferedImage = new BufferedImage(
            awtImage.getWidth(null),
            awtImage.getHeight(null),
            BufferedImage.TYPE_INT_RGB
        );
        bufferedImage.getGraphics().drawImage(awtImage, 0, 0, null);
        
        // 4. Borrar la imagen temporal anterior
        String path = "Springboot/demo/test_files/Out/ted_temp/ted_barcode.png";
        deleteTemporaryImage(path);

        // Guardar imagen temporal
        ImageIO.write(bufferedImage, "png", new File(path));
        System.out.println("Imagen guardada como codigo_pdf417.png");

        // Generar PDF
        generatePDF(xmlFile, xslFile, pdfFile);
        
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
//    public static void main(String[] args) throws Exception {
//        try (
//            InputStream xmlFile = new FileInputStream("test_files/xml/test_xmlDemo.xml");
//            InputStream xslFile = new FileInputStream("src/main/java/SiiPDF/plantillas/plantilla_PDF_FExE.xsl");
//            OutputStream pdfFile = new FileOutputStream("test_files/Out/test_pdf_FExE_Demo_3.pdf")
//        ) {
//            generatePDFWithTED(xmlFile, xslFile, pdfFile);
//            System.out.println("PDF generado correctamente.");
//        }
//    }

}