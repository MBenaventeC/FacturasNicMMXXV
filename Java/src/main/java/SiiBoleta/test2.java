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
import java.nio.file.Path;
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

    public static void main(String[] args) throws Exception {
        //Encuentra la ruta al template (o archivo Json con el contenido del documento)
        String contenido = Files.readString(Paths.get("Java/jsonTemplate.json"));
        JSONObject root = new JSONObject(contenido);

        //Lee el archivo como un Array Json
        JSONArray dtes = root.getJSONArray("DTEs");

        // Valida que se cumplan las exigencias de input en cada DTE. Si alguno
        // no cumple, se lanza una excepcion.
        Validate.validateDTEs(dtes);

        //Genera SetDTE vacío
        Document SetDTE = SetDTEGenerator.Generate2("SetDTE");
        //Iteramos por cada DTE
        for (int i = 0; i < dtes.length(); i++) {
            JSONObject jsonDoc = dtes.getJSONObject(i);
            //Genera DTE
            Document DTE = DTEGenerator2.Generate("DTE",jsonDoc, "Java/FoliosSII609100003422295202510171815.xml");
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
