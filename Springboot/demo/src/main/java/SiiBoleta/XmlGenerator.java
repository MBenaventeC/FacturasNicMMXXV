package SiiBoleta;

import SiiBoleta.DTEDefType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import SiiBoleta.DTEMakers;

public class XmlGenerator {

    public static DTEDefType.Documento.TED generateFacturaXML(Map<String, Object> args) throws JAXBException, DatatypeConfigurationException, Exception {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println(args);
        System.out.println("/////////////////////////////////////////////////////");
        // 1. Create and populate your object
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(1,2,1,MedioPagoType.EF);
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor(args.get("rut").toString(), "Empresa ejemplo S.A." ,args.get("giro").toString(),args.get("nombre").toString(), args.get("direccion").toString(), args.get("comuna").toString(), args.get("ciudad").toString());
        double precioUnitario = (Double)args.get("precioU");
        int cantidad = (Integer)args.get("cant");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales((int)(precioUnitario));
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);
        DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,args.get("detalle").toString(), precioUnitario);
        GregorianCalendar calendar = new GregorianCalendar(2025, Calendar.APRIL, 25);

        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/FoliosSII609100003410743962025729929.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",34,994321,args.get("rut").toString(),"Empresa Ejemplo S.A.",(int)precioUnitario, args.get("detalle").toString(),cafFromXml);

        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        

        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);
        DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalle,ted,"DTE-34-994321");


        SignatureType signature = DTEMakers.makeSignature(documento);


        DTEDefType dte = DTEMakers.makeDTE(documento,signature);
        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(DTEDefType.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

        // 4. Marshal to a file or System.out
        new File("Springboot/demo/test_files/xml").mkdirs();
        File output = new File("Springboot/demo/test_files/xml/dte.xml");

        //Para crear fragmentos:
        JAXBElement<DTEDefType> jaxbElement = new JAXBElement<>(
                new QName("DTE"), DTEDefType.class, dte);

        marshaller.marshal(jaxbElement, output);
        // or: marshaller.marshal(obj, System.out);

        return ted;
    }
}