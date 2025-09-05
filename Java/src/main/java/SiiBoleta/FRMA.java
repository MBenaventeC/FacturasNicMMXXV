package SiiBoleta;

public class FRMA {

    public static DTEDefType.Documento.TED.DD.CAF.FRMA makeFRMA(byte[] value) {
        DTEDefType.Documento.TED.DD.CAF.FRMA frma = new DTEDefType.Documento.TED.DD.CAF.FRMA();

        frma.setAlgoritmo("SHA1withRSA");
        frma.setValue(value);

        return frma;
    }
}
