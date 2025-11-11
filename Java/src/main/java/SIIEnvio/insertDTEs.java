package SIIEnvio;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class insertDTEs {
    public static void insertXmlAtStringPosition(File baseXml, File xmlToInsert, File output, String afterMarker) throws IOException {
        // Read both files as strings
        String baseContent = Files.readString(baseXml.toPath(), StandardCharsets.ISO_8859_1);
        String insertContent = Files.readString(xmlToInsert.toPath(), StandardCharsets.ISO_8859_1);

        //System.out.println("baseContent: " + baseContent);

        // Remove XML declaration from the second file if present
        insertContent = insertContent.replaceFirst("<\\?xml.*?\\?>\\s*", "");

        // Find the insertion point
        int pos = baseContent.indexOf(afterMarker);
        if (pos == -1) throw new IllegalArgumentException("Marker not found in base XML");

        // Insert content after marker
        //String merged = baseContent.substring(0, pos + afterMarker.length()) + "\n" + insertContent + baseContent.substring(pos + afterMarker.length());
        String merged = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\r\n" + "<EnvioDTE xmlns=\"http://www.sii.cl/SiiDte\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"1.0\" xsi:schemaLocation=\"http://www.sii.cl/SiiDte EnvioDTE_v10.xsd\">" + "\r\n" +baseContent.substring(0, pos) + insertContent + "\r\n" + baseContent.substring(pos)+ "</EnvioDTE>";

        // Write output
        Files.writeString(output.toPath(), merged, StandardCharsets.ISO_8859_1);
    }
    public static String Insert(String base,String dte, String out) throws IOException {
        File xmlToInsert = new File(dte);
        File baseXml = new File(base);

        File output = new File("out/"+out+".xml");
        String afterMarker = "</SetDTE>";

        insertXmlAtStringPosition(baseXml, xmlToInsert, output, afterMarker);

        return "out/"+out+".xml";
    }
    public static void Insert2(Document base, Document dte, String out) throws IOException {

        Element dteElement = (Element) dte.getElementsByTagName("DTE").item(0);
        Node importedDTE = base.importNode(dteElement, true);
        //Element newElement = base.createElementNS("http://www.sii.cl/SiiDte","DTE");

        NodeList dteList2 = base.getElementsByTagName("SetDTE");
        Element dteE2 = (Element) dteList2.item(0);
        dteE2.appendChild(importedDTE);
    }
}
