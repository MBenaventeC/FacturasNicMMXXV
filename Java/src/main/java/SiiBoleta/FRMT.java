package SiiBoleta;

public class FRMT {

    public static DTEDefType.Documento.TED.FRMT makeFRMT(byte[] value) {

        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();
        frmt.setValue(value);
        frmt.setAlgoritmo("SHA1withRSA");

        return frmt;
    }
}
