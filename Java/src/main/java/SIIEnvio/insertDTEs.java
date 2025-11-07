package SIIEnvio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class insertDTEs {
    public static void insertXmlAtStringPosition(File baseXml, File xmlToInsert, File output, String afterMarker) throws IOException {
        // Read both files as strings
        String baseContent = Files.readString(baseXml.toPath(), StandardCharsets.ISO_8859_1);
        String insertContent = Files.readString(xmlToInsert.toPath(), StandardCharsets.ISO_8859_1);

        System.out.println("baseContent: " + baseContent);

        // Remove XML declaration from the second file if present
        insertContent = insertContent.replaceFirst("<\\?xml.*?\\?>\\s*", "");

        // Find the insertion point
        int pos = baseContent.indexOf(afterMarker);
        if (pos == -1) throw new IllegalArgumentException("Marker not found in base XML");

        // Insert content after marker
        //String merged = baseContent.substring(0, pos + afterMarker.length()) + "\n" + insertContent + baseContent.substring(pos + afterMarker.length());
        String merged = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\n" + "<EnvioDTE xmlns=\"http://www.sii.cl/SiiDte\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"1.0\" xsi:schemaLocation=\"http://www.sii.cl/SiiDte EnvioDTE_v10.xsd\">" + "\n" +baseContent.substring(0, pos) + insertContent + "\n" + baseContent.substring(pos)+ "</EnvioDTE>";

        // Write output
        Files.writeString(output.toPath(), merged, StandardCharsets.ISO_8859_1);
    }
    public static void main(String[] args) throws IOException {
        File xmlToInsert = new File("out/signed.xml");
        File baseXml = new File("out/SetDTE.xml");
        File output = new File("out/envio.xml");
        String afterMarker = "</SetDTE>";

        insertXmlAtStringPosition(baseXml, xmlToInsert, output, afterMarker);
    }
}
