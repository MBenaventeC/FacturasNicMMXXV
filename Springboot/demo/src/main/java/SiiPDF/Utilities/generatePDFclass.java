package SiiPDF.Utilities;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

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
    // Ejemplo de uso
    public static void main(String[] args) throws Exception {
        try (
            InputStream xmlFile = new FileInputStream("test_files/testdocPDF.xml");
            InputStream xslFile = new FileInputStream("src/main/java/SiiPDF/plantilla_pdf.xsl");
            OutputStream pdfFile = new FileOutputStream("test_files/test_pdf.pdf")
        ) {
            if (xslFile == null) {
                throw new FileNotFoundException("No se encontró el archivo XSL en la ruta especificada.");
            }
            generatePDF(xmlFile, xslFile, pdfFile);
            System.out.println("PDF generado correctamente.");
        }
    }

}