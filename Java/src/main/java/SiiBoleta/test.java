package SiiBoleta;

import SIIEnvio.envioGenerator;
import SIIEnvio.signEnvio;

public class test {
    public static void main(String[] args) throws Exception {
        //Genera DTE
        DTEGenerator.main(args);
        //firma DTE
        DTESign.main(args);
        //Genera Envio
        envioGenerator.main(args);
        //Firma envio
        signEnvio.main(args);
    }
}
