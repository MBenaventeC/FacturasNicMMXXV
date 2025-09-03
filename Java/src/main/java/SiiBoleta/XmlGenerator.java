package SiiBoleta;

import SiiBoleta.DTEDefType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public class XmlGenerator {

    public DTEDefType.Documento.Encabezado.IdDoc makeIdDoc
            (BigInteger folio,
             XMLGregorianCalendar fchEmis,
             BigInteger indServicio,
             BigInteger fmaPago,
             XMLGregorianCalendar fchCancel,
             MedioPagoType medioPago,
             XMLGregorianCalendar fchVenc
            ){
        DTEDefType.Documento.Encabezado.IdDoc  idDoc = new DTEDefType.Documento.Encabezado.IdDoc();
        idDoc.setTipoDTE(new BigInteger("34"));
        idDoc.setFolio(folio);
        idDoc.setFchEmis(fchEmis);
        idDoc.setIndServicio(indServicio);
        idDoc.setFmaPago(fmaPago);
        idDoc.setFchCancel(fchCancel);
        idDoc.setMedioPago(medioPago);
        idDoc.setFchVenc(fchVenc);
        return idDoc;
    }

    public DTEDefType.Documento.Encabezado.Emisor makeEmisor
            (){
        DTEDefType.Documento.Encabezado.Emisor emisor = new DTEDefType.Documento.Encabezado.Emisor();
        emisor.setRUTEmisor("60910000-1");
        emisor.setRznSoc("Universidad de Chile");
        emisor.setGiroEmis("Corporación Educacional y Servicios Profesionales");
        //acteco: no tiene setter
        emisor.setSucursal("NIC Chile");
        emisor.setCdgSIISucur(new BigInteger("67051191"));
        emisor.setDirOrigen("Miraflores 222, Piso 14");
        emisor.setCmnaOrigen("Santiago");
        emisor.setCiudadOrigen("Santiago");
        return emisor;
    }

    public DTEDefType.Documento.Encabezado.Receptor makeReceptor(
            String RUTRecep,
            String rznSoc,
            String giroRecep,
            String Contacto,
            String dirRecap,
            String cmnaRecap,
            String ciudadRecep
    ){
        DTEDefType.Documento.Encabezado.Receptor receptor = new DTEDefType.Documento.Encabezado.Receptor();
        receptor.setRUTRecep(RUTRecep);
        receptor.setRznSocRecep(rznSoc);
        receptor.setGiroRecep(giroRecep);
        receptor.setContacto(Contacto);
        receptor.setDirRecep(dirRecap);
        receptor.setCmnaRecep(cmnaRecap);
        receptor.setCiudadRecep(ciudadRecep);
        return receptor;
    }

    public DTEDefType.Documento.Encabezado.Totales makeTotales
            (BigInteger mntExe,
             BigInteger mntTotal){
        DTEDefType.Documento.Encabezado.Totales totales = new DTEDefType.Documento.Encabezado.Totales();
        totales.setMntExe(mntExe);
        totales.setMntTotal(mntTotal);
        return totales;
    }

    public DTEDefType.Documento.Encabezado makeEncabezado(){
        DTEDefType.Documento.Encabezado encabezado = new DTEDefType.Documento.Encabezado();
        encabezado.setIdDoc(makeIdDoc());
        encabezado.setEmisor(makeEmisor());
        encabezado.setReceptor(makeReceptor());
        encabezado.setTotales(makeTotales());
        return encabezado;
    }

    //se deberia calcular?
    public DTEDefType.Documento.Detalle makeDetalle(
            int nroLinDet,
            String nmbItem,
            BigDecimal qtyItem,
            BigDecimal prcItem,
            BigInteger montoItem){
        DTEDefType.Documento.Detalle detalle = new DTEDefType.Documento.Detalle();
        detalle.setNroLinDet(nroLinDet);//int
        detalle.setNmbItem(nmbItem);//str
        detalle.setQtyItem(qtyItem);//bigd
        detalle.setPrcItem(prcItem);//bigd
        detalle.setMontoItem(montoItem);//bigi
        return detalle;
    }

    public DTEDefType.Documento makeDocumento() {
        DTEDefType.Documento documento = new DTEDefType.Documento();
        documento.setEncabezado(makeEncabezado());
        //no existe set detalle
        return documento;
    }

    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
        // 1. Create and populate your object

        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(ReciboDefType.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // 4. Marshal to a file or System.out
        new File("src/out").mkdirs();
        File output = new File("src/out/FacturaOutXD.xml");
        marshaller.marshal(obj, output);
        // or: marshaller.marshal(obj, System.out);
    }
}