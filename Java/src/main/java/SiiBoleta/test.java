package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.envioGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;

public class test {
    public static void exmain(String[] args) throws Exception {
        //Genera DTE
        DTEGenerator.main(args);
        //firma DTE
        DTESign.main(args);
        //Genera Envio
        envioGenerator.main(args);
        //Firma envio
        signEnvio.main(args);
    }
    public static void main(String[] args) throws Exception {
        //Genera DTE
        DTEGenerator.main(args);
        //firma DTE
        DTESign.main(args);
        //Genera SetDTE vacío
        SetDTEGenerator.main(args);
        //inserta DTEs y añade a envíoDTE
        insertDTEs.main(args);
        //Firma envio
        signEnvio.main(args);
    }
}
