package SiiBoleta;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AUTORIZACION")
public class AUTORIZACION {
    @XmlElement(name = "CAF")
    private DTEDefType.Documento.TED.DD.CAF caf;
    private byte[] RSASK;
    private byte[] RSAPUBK;

    public DTEDefType.Documento.TED.DD.CAF getCAF() { return caf; }
    public void setCAF(DTEDefType.Documento.TED.DD.CAF caf) { this.caf = caf; }


}
