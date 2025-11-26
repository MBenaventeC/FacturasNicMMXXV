package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Java vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java home: " + System.getProperty("java.home"));
        System.out.println("----------------------------------------");
        //Encuentra la ruta al template (o archivo Json con el contenido del documento)
        System.out.println("cwd = " + System.getProperty("user.dir"));
        InputStream is = test.class.getClassLoader().getResourceAsStream("jsonTemplate.json");
        if (is == null) {
            throw new FileNotFoundException("No se encontró jsonTemplate.json en resources");
        }

        String contenido = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject jsonObj = new JSONObject(contenido);
        //Lee el archivo como un Array Json
        JSONArray jsonDTEs = jsonObj.getJSONArray("DTEs");

        // Validación de datos en cada DTE
        Validate.validateDTEs(jsonDTEs);

        //System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");

        //Para guardar las archivos DTE firmados
        List<String> SignedDTEs = new ArrayList<>();
        //Genera SetDTE vacío
        String SetDTE = SetDTEGenerator.Generate("SetDTE", jsonDTEs);
        //Iteramos por cada DTE
        for (int i=0;i<jsonDTEs.length();i++){
            JSONObject jsonDTE = jsonDTEs.getJSONObject(i);
            JSONObject idoc = jsonDTE.getJSONObject("documento");
            int tipoDoc = idoc.getInt("tipoDocumento");
            int folio = idoc.getInt("folio");
            String ID = "DTE-"+tipoDoc+"-"+folio;
            //Genera DTE
            System.out.println("cwd = " + System.getProperty("user.dir"));
            String DTE = DTEGenerator.Generate("DTE",jsonDTE);
            //firma DTE
            String signedDTE = DTESign.Sign4("signedDTE"+i,DTE,ID);

            //Guarda DTE firmado
            SignedDTEs.add(signedDTE);
        }

        //inserta DTEs y añade a envíoDTE
        String envio = insertDTEs.Insert(SetDTE,SignedDTEs,"envio");
        //Firma envio
        String signedEnv = signEnvio.sign(envio,"out/signedEnv.xml","SetDoc");
        String Fixed =  signEnvio.Fix(signedEnv,"out/signedEnvFixed");

        System.out.println("Envio DTE firmado: "+ Fixed);
    }
}
