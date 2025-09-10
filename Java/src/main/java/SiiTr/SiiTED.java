package SiiTr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.Base64;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.itextpdf.text.pdf.BarcodePDF417;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase contiene métodos necesarios para crear el bloque DD y FRMT necesarios para poder obtener el TED
 * y su representación en PDF417 como se indica en el documento "Instructivo Tecnico Factura Eléctronica", anexo 2.
 */
public class SiiTED {

    private static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;

    private static String getTsted() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return ahora.format(formatter);
    }

    /**
     * Este metodo construye el bloque DD
     * @param datos: Los datos que necesita el bloque DD para construirse
     * @param cafXml: String obtenido desde el XML de autorizacion. Es especificamente es toda la etiqueta CAF.
     * @return Retorna el bloque DD como string, para luego poder hashearlo en el metodo 'signDD'.
     */
    public static String buildDD(Map<String,String> datos, String cafXml) {
        // Campos requeridos según el instructivo
        String[] requeridos = {"RE","TD","F","FE","RR","RSR","MNT","IT1"};
        for (String k : requeridos) {
            if (!datos.containsKey(k)) {
                throw new IllegalArgumentException("Falta el campo requerido: " + k);
            }
        }
        if (cafXml == null || cafXml.isBlank()) {
            throw new IllegalArgumentException("CAF vacío o nulo");
        }

        String tsted = getTsted();

        return "<DD>" +
                "<RE>" + datos.get("RE") + "</RE>" +
                "<TD>" + datos.get("TD") + "</TD>" +
                "<F>" + datos.get("F") + "</F>" +
                "<FE>" + datos.get("FE") + "</FE>" +
                "<RR>" + datos.get("RR") + "</RR>" +
                "<RSR>" + datos.get("RSR") + "</RSR>" +
                "<MNT>" + datos.get("MNT") + "</MNT>" +
                "<IT1>" + datos.get("IT1") + "</IT1>" +
                cafXml +
                "<TSTED>" + tsted + "</TSTED>" +
                "</DD>";
    }

    /**
     * Firma (aplica hash) al string dd, que representa el bloque DD obtenido en el metodo buildDD
     * @param dd: String que representa el bloque DD construido
     * @param rsaPrivateKey: LLave privada ya cargada con anterioridad (recibe lo que entrega el metodo loadPkcs1Key)
     * @return La firma obtenida al aplicar SHA1 al string dd.
     */
    public static String signDD(String dd, PrivateKey rsaPrivateKey) throws Exception {
        String ddMin = minificarEntreTags(dd); // quitar \s entre '><' (ver anexo 2.4)
        byte[] bytes = ddMin.getBytes(ISO_8859_1);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(rsaPrivateKey);
        sig.update(bytes);
        byte[] firma = sig.sign();
        return Base64.getEncoder().encodeToString(firma); // sin saltos de línea
    }

    /**
     * Construye el TED final según como indica el SII.
     */
    public static String buildTED(String dd, String frmtBase64) {
        return "<TED version=\"1.0\">" + dd + "<FRMT algoritmo=\"SHA1withRSA\">" + frmtBase64 + "</FRMT></TED>";
    }


    /** Quita espacios/saltos entre '><' (según reglas del anexo 2.4 para el cálculo del digest/firma). */
    private static String minificarEntreTags(String dd) {
        // elimina espacios/tab/nuevas líneas entre cierre y apertura de tag
        return dd.replaceAll(">\\s+<", "><");
    }

    /** Metodo para cargar la llave privada, que luego será entregada a la funcion signDD. Es necesario
     * agregar la dependencia bouncycastle
     */
    public static PrivateKey loadKey(String rsaskPem) throws Exception {
        try (PEMParser parser = new PEMParser(new StringReader(rsaskPem))) {
            Object obj = parser.readObject();
            return new JcaPEMKeyConverter().getKeyPair((PEMKeyPair) obj).getPrivate();
        }
    }

    /** Metodo que recibe el TED construido en el metodo buildTED y entrega la representacion
     * en PDF417. (Es necesario dibujar la representacion en un pdf mas adelante).
     */
    public static Image generateBarcode(String ted)
            throws BadElementException {

        // Generar el código PDF417
        BarcodePDF417 pdf417 = new BarcodePDF417();
        pdf417.setText(ted.getBytes(StandardCharsets.ISO_8859_1)); // Ojo con charset
        return pdf417.getImage();
    }

    public static String extraerCAF(Path xmlPath) throws IOException {
        // Lee el archivo XML completo en un String
        String xml = Files.readString(xmlPath);

        // Buscar el bloque CAF (etiqueta incluida)
        Pattern p = Pattern.compile("<CAF[\\s\\S]*?</CAF>");
        Matcher m = p.matcher(xml);
        if (m.find()) {
            return m.group(); // Devuelve <CAF ...> ... </CAF>
        }
        throw new IllegalArgumentException("No se encontró <CAF>");
    }

    public static void guardarBarcodeEnPDF(Image barcode, String rutaPdf) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(rutaPdf));
        doc.open();
        doc.add(barcode);
        doc.close();
    }

    public static void makeTed(Map<String,String> datos, Path xmlPath) throws Exception {
        String caf = extraerCAF(xmlPath);

        PrivateKey privateKey = Pem.loadPrivateKeyFromCaf(xmlPath);

        String dd = buildDD(datos, caf);
        String signDD = signDD(dd, privateKey);
        String ted =  buildTED(dd, signDD);

        Image barcode = generateBarcode(ted);
        guardarBarcodeEnPDF(barcode, "barcode.pdf");
    }

    public static String getCafAttr(Path xmlPath, String tagName) throws IOException {
        String caf = extraerCAF(xmlPath);
        return Pem.extractTagInnerPem(caf, tagName);
    }

    public static void main(String[] args) throws Exception {

        // inventamos datos de prueba
        Map<String,String> datos = new HashMap<>();
        datos.put("RE", "20.828.882-1");
        datos.put("TD", "33");
        datos.put("F", "67");
        datos.put("FE", "2025-09-03");
        datos.put("RR", "39729847-1");
        datos.put("RSR", "Comprador de prueba");
        datos.put("MNT", "10990");
        datos.put("IT1", "Prueba");

        // indicamos donde esta la ruta del xml que contiene el caf y llaves
        String ruta = "C:/universidad/proyecto_de_software/FacturasNicMMXXV/Java/Autorizacion.xml";
        Path xmlPath = Paths.get(ruta);

        // Hacemos el timbre electronico
        makeTed(datos, xmlPath);
    }

}
