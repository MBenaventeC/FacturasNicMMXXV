package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.envioGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;
import org.w3c.dom.Document;
import SiiBoleta.SignXMLApache;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class test2 {
    public static void exmain(String[] args) throws Exception {
        //Genera DTE
        //DTEGenerator.main(args);
        //firma DTE
        //DTESign.main(args);
        //Genera Envio
        envioGenerator.main(args);
        //Firma envio
        //signEnvio.main(args);
    }

    public static JSONArray leerJsonArray(String ruta) throws Exception {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return new JSONArray(contenido);
    }

    public static void main(String[] args) throws Exception {
        //Encuentra la ruta al template (o archivo Json con el contenido del documento)
        String jsonRuta = Files.readString(Paths.get("jsonTemplate.json"));
        //Lee el archivo como un Array Json
        JSONArray arrayJson = leerJsonArray(jsonRuta);
        //Genera SetDTE vacío
        Document SetDTE = SetDTEGenerator.Generate2("SetDTE");
        //Iteramos por cada DTE
        for (int i = 0; i < arrayJson.length(); i++) {
            JSONObject jsonDoc = arrayJson.getJSONObject(i);
            //Genera DTE
            Document DTE = DTEGenerator2.Generate("DTE",jsonDoc);
            //firma DTE
            DTESign.Sign3("signedDTE",DTE);
            //inserta DTEs y añade a envíoDTE
            insertDTEs.Insert2(SetDTE,DTE,"envio");
        }
        //Firma envio
        String signedEnv = signEnvio.sign3(SetDTE,"signedEnv");
        //System.out.println("Envio DTE firmado: "+ signedEnv);
    }
}
