package SIIEnvio;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class insertDTEs {
    /** Given a base XML file, a XML is inserted in 'afterMarker' and save into output path
     * , an output path and a reference to where the XML must be inserted
     * @param baseXml Xml file where 'xmlToInsert' is going to be inserted.
     * @param xmlToInsert Xml file to be inserted.
     * @param output path to save the XML into.
     * @param afterMarker reference of where to insert 'xmlToInsert' in 'baseXml'.
     * @throws IOException
     */
    public static void insertXmlAtStringPosition(File baseXml, File xmlToInsert, File output, String afterMarker) throws IOException {
        // Read both files as strings
        String baseContent = Files.readString(baseXml.toPath(), StandardCharsets.ISO_8859_1);
        String insertContent = Files.readString(xmlToInsert.toPath(), StandardCharsets.ISO_8859_1);
        System.out.println(insertContent);
        // Find the insertion point
        int closeDTEpos = insertContent.indexOf("</DTE>");
        if (closeDTEpos == -1) throw new IllegalArgumentException("Marker not found in base XML");

        insertContent = insertContent.substring(0, closeDTEpos) + "\r\n" +
                insertContent.substring(closeDTEpos);


        // Remove XML declaration from the second file if present
        insertContent = insertContent.replaceFirst("<\\?xml.*?\\?>\\s*", "");

        // Find the insertion point
        int pos = baseContent.indexOf(afterMarker);
        if (pos == -1) throw new IllegalArgumentException("Marker not found in base XML");

        // Insert content after marker
        //String merged = baseContent.substring(0, pos + afterMarker.length()) + "\n" + insertContent + baseContent.substring(pos + afterMarker.length());
        String merged = baseContent.substring(0, pos) + insertContent + "\r\n" +
                baseContent.substring(pos);

        // Write output
        Files.writeString(output.toPath(), merged, StandardCharsets.ISO_8859_1);
    }

    /** Given a SetDTE file, a list of DTEs and an output path,
     * the method inserts DTE files into the SetDTE file, and save it in output path.
     *
     * @param base path to Xml file where 'dtes' are going to be inserted.
     * @param dtes list of paths to DTE files to be inserted into 'base' XML file.
     * @param out path to save the XML into.
     * @return
     * @throws IOException
     */
    public static String Insert(String base,List<String> dtes, String out) throws IOException {
        // base es una dirección al archivo SetDTE
        // base is the path to SetDTE file
        File baseXml = new File(base);
        File output = new File("out/"+out+".xml");
        String afterMarker = "</SetDTE>";
        for(int i=0;i<dtes.size();i++) {
            String dte = dtes.get(i);
            File xmlToInsert = new File(dte);
            insertXmlAtStringPosition(baseXml, xmlToInsert, baseXml, afterMarker);
        }
        // Ahora si insertaremos el header de xml
        String baseContent = Files.readString(baseXml.toPath(), StandardCharsets.ISO_8859_1);
        String finalContent = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\r\n" +
                "<EnvioDTE xmlns=\"http://www.sii.cl/SiiDte\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"1.0\" xsi:schemaLocation=\"http://www.sii.cl/SiiDte EnvioDTE_v10.xsd\">" + "\r\n" +
                baseContent + "</EnvioDTE>";
        // Write output
        Files.writeString(output.toPath(), finalContent, StandardCharsets.ISO_8859_1);
        return "out/"+out+".xml";
    }
}
