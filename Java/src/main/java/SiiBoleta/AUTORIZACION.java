package SiiBoleta;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.security.PrivateKey;

@XmlRootElement(name = "AUTORIZACION")
public class AUTORIZACION {
    @XmlElement(name = "CAF")
    private DTEDefType.Documento.TED.DD.CAF caf;
    @XmlElement(name = "RSASK")
    private String RSASK;
    @XmlElement(name = "RSAPUBK")
    private byte[] RSAPUBK;

    public DTEDefType.Documento.TED.DD.CAF getCAF() { return caf; }
    public void setCAF(DTEDefType.Documento.TED.DD.CAF caf) { this.caf = caf; }

    public String getRSASK() { return RSASK; }


}
