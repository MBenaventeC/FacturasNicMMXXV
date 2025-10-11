package SiiPDF.Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import com.itextpdf.text.Image;
import java.util.Base64;

public class TedImageURIResolver implements URIResolver {
    private Image tedBarcode;
    
    public TedImageURIResolver(Image tedBarcode) {
        this.tedBarcode = tedBarcode;
    }
    
    @Override
    public Source resolve(String href, String base) throws TransformerException {
        if ("ted:barcode".equals(href)) {
            try {
                // Convertir iText Image a Base64
                String base64Image = convertImageToBase64(tedBarcode);
                
                // Crear Data URI
                String dataUri = "data:image/png;base64," + base64Image;
                
                // Retornar como StreamSource
                InputStream dataStream = new ByteArrayInputStream(dataUri.getBytes());
                return new StreamSource(dataStream);
                
            } catch (Exception e) {
                throw new TransformerException("Error resolving TED barcode", e);
            }
        }
        return null; // Dejar que el resolver por defecto maneje otras URIs
    }
    
    private String convertImageToBase64(Image itextImage) throws Exception {
        // Convertir iText Image a BufferedImage
        BufferedImage bufferedImage = convertItextToBufferedImage(itextImage);
        
        // Convertir BufferedImage a Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
    
    private BufferedImage convertItextToBufferedImage(Image itextImage) {
        byte[] rawData = itextImage.getRawData();
        int width = (int) itextImage.getScaledWidth();
        int height = (int) itextImage.getScaledHeight();
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        if (rawData != null && rawData.length > 0) {
            int index = 0;
            for (int y = 0; y < height && index < rawData.length; y++) {
                for (int x = 0; x < width && index < rawData.length; x++) {
                    int pixel = (rawData[index] & 0xFF) == 0 ? 0x000000 : 0xFFFFFF;
                    bufferedImage.setRGB(x, y, pixel);
                    index++;
                }
            }
        }
        
        return bufferedImage;
    }
}

