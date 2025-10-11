package SiiBoletaTest;

public class RSAPK {

    public static DTEDefType.Documento.TED.DD.CAF.DA.RSAPK makeRSAPK(byte[] m,
                                                       byte[] e) {

        DTEDefType.Documento.TED.DD.CAF.DA.RSAPK rsapk = new DTEDefType.Documento.TED.DD.CAF.DA.RSAPK();
        rsapk.m = m;
        rsapk.e = e;
        return rsapk;
    }
}
