package SiiBoleta;

public class TED {

    public static DTEDefType.Documento.TED makeTED(DTEDefType.Documento.TED.DD dd,
                                                   DTEDefType.Documento.TED.FRMT frmt) {

        DTEDefType.Documento.TED ted= new DTEDefType.Documento.TED();

        ted.setDD(dd);
        ted.setFRMT(frmt);
        ted.setVersion("1.0");

        return ted;
    }
}
