package SiiBoleta;

public class CAF {

    public static DTEDefType.Documento.TED.DD.CAF makeCAF(DTEDefType.Documento.TED.DD.CAF.DA da,
                                            DTEDefType.Documento.TED.DD.CAF.FRMA frma) {

        DTEDefType.Documento.TED.DD.CAF caf = new DTEDefType.Documento.TED.DD.CAF();

        caf.setDA(da);
        caf.setFRMA(frma);
        caf.setVersion("1.0");

        return caf;
    }
}
