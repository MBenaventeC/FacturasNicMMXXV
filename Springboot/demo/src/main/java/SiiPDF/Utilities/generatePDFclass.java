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
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


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
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",41,1370000,"60910000-1","Dise�o",9990,"item1aaa",cafFromXml);
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);
        
        // 2. Creamos
        Image barcode = TED.makeBarcode(ted);
        previewItextImage(barcode, "Barcode pre guardado");
        
        // 3. Guardamos
        String tempImagePath = "test_files/Out/ted_temp/ted_barcode.png";
        saveImageAsPNG(barcode, tempImagePath);
        
        // 4. Generar PDF
        generatePDF(xmlFile, xslFile, pdfFile);

        // 5. Borrar la imagen temporal
        //deleteTemporaryImage(tempImagePath);
    }
    
    private static void previewItextImage(Image itextImage, String title) throws Exception {
        // Crear archivo PDF temporal
        File tempPdf = File.createTempFile("itext_preview_", ".pdf");
        tempPdf.deleteOnExit();
        
        // Crear documento PDF con la imagen
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(tempPdf));
        document.open();
        
        // Agregar título
        document.add(new com.itextpdf.text.Paragraph(title));
        document.add(new com.itextpdf.text.Paragraph(" ")); // Espacio
        
        // Agregar la imagen
        document.add(itextImage);
        document.close();
        
        // Abrir PDF automáticamente
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(tempPdf);
            System.out.println("Vista previa de iText Image: " + tempPdf.getAbsolutePath());
        }
    }
    
    private static void saveImageAsPNG(Image itextImage, String path) throws IOException {
        try {
            // Obtener dimensiones
            int width = Math.round(itextImage.getScaledWidth());
            int height = Math.round(itextImage.getScaledHeight());
            
            //Usar TYPE_INT_RGB para compatibilidad completa
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            //Fallback mejorado con datos raw
            byte[] rawData = itextImage.getRawData();
                
            if (rawData != null && rawData.length > 0) {
                // Llenar fondo blanco primero
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        bufferedImage.setRGB(x, y, 0xFFFFFF); // Blanco por defecto
                    }
                }
                    
                // Calcular bytes por píxel de manera más robusta
                int expectedPixels = width * height;
                int bytesPerPixel = Math.max(1, rawData.length / expectedPixels);
                    
                // Procesar datos con validación
                int pixelCount = 0;
                for (int y = 0; y < height && pixelCount < expectedPixels; y++) {
                    for (int x = 0; x < width && pixelCount < expectedPixels; x++) {
                        int dataIndex = pixelCount * bytesPerPixel;
                            
                        if (dataIndex < rawData.length) {
                            int grayValue = rawData[dataIndex] & 0xFF;
                                
                            // Para PDF417: invertir si es necesario
                            // Si la mayoría de píxeles son 0, probablemente están invertidos
                            int rgb = (grayValue < 128) ? 0x000000 : 0xFFFFFF;
                            bufferedImage.setRGB(x, y, rgb);
                        }
                        pixelCount++;
                    }
                }
            } else {
                // ✅ MÉTODO 3: Generar imagen de placeholder si fallan los anteriores
                java.awt.Graphics2D g2d = bufferedImage.createGraphics();
                g2d.setColor(java.awt.Color.WHITE);
                g2d.fillRect(0, 0, width, height);
                g2d.setColor(java.awt.Color.BLACK);
                g2d.drawString("PDF417 Error", 10, height/2);
                g2d.dispose();
                    
                System.err.println("Warning: No se pudieron obtener datos de imagen, generando placeholder");
            }
            // ✅ GUARDAR CON VALIDACIÓN
            File outputFile = new File(path);
            outputFile.getParentFile().mkdirs();
            
            // Verificar que la imagen no esté completamente vacía
            if (isImageValid(bufferedImage)) {
                boolean saved = ImageIO.write(bufferedImage, "PNG", outputFile);
                if (saved) {
                    System.out.println("✅ Imagen TED guardada: " + path + " (" + width + "x" + height + ")");
                } else {
                    throw new IOException("ImageIO.write() retornó false");
                }
            } else {
                throw new IOException("La imagen generada está corrupta o vacía");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error guardando imagen TED: " + e.getMessage());
            throw new IOException("Error procesando imagen iText: " + e.getMessage(), e);
        }
    }

    // Método auxiliar para validar la imagen
    private static boolean isImageValid(BufferedImage image) {
        if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0) {
            return false;
        }
        
        // Verificar que no sea completamente transparente/vacía
        int blackPixels = 0;
        int whitePixels = 0;
        int totalPixels = Math.min(100, image.getWidth() * image.getHeight()); // Muestra
        
        for (int i = 0; i < totalPixels; i++) {
            int x = i % image.getWidth();
            int y = i / image.getWidth();
            int rgb = image.getRGB(x, y);
            
            if (rgb == 0x000000 || (rgb & 0xFF) < 128) blackPixels++;
            else whitePixels++;
        }
        
        // Debe tener al menos algunos píxeles negros (el código de barras)
        return blackPixels > 0 && whitePixels > 0;
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