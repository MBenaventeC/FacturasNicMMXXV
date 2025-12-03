package SiiBoleta;

public class RSAPK {

    /**
     * Creates RSAPK and returns it
     * @param m
     * @param e
     * @return
     */
    public static DTEDefType.Documento.TED.DD.CAF.DA.RSAPK makeRSAPK(byte[] m,
                                                       byte[] e) {

        DTEDefType.Documento.TED.DD.CAF.DA.RSAPK rsapk = new DTEDefType.Documento.TED.DD.CAF.DA.RSAPK();
        rsapk.m = m;
        rsapk.e = e;
        return rsapk;
    }
}
