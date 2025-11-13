/**
 * Copyright [2009] [NIC Labs]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the 	License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or 
 * agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 **/

package SiiEnvio.net;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.nio.file.Files;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.apache.http.client.ClientProtocolException;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

import SiiEnvio.generatedClasses.cl.sii.siiDte.GetToken;
import SiiEnvio.generatedClasses.cl.sii.siiDte.RESPUESTA;
import SiiEnvio.generatedClasses.cl.sii.siiDte.RECEPCIONDTE;
import SiiEnvio.util.GetTokenDocument;
import SiiEnvio.util.RECEPCIONDTEDocument;
import SiiEnvio.util.RESPUESTADocument;
import SiiEnvio.util.Utilities;
import SiiBoleta.DTEDefType.Documento;

public class ConexionSii2_legacy {

    public boolean autentifica(PrivateKey pKey) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public String getToken(PrivateKey pKey, X509Certificate cert)
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, 
            KeyException, MarshalException, XMLSignatureException, SAXException, 
            IOException, ParserConfigurationException, JAXBException,
            UnsupportedOperationException, SOAPException, ConexionSiiException {

        String urlSolicitud = Utilities.netLabels.getString("URL_SOLICITUD_TOKEN");
        String semilla = getSemilla();

        // ✅ Crear documento usando la versión JAXB
        GetTokenDocument req = GetTokenDocument.newInstance();
        req.addNewGetToken().addNewItem().setSemilla(semilla);

        // ✅ Firmar el documento
        req.sign(pKey, cert);

        // ✅ Crear mensaje SOAP
        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        header.detachNode();

        Name bodyName = envelope.createName("getToken", "m", urlSolicitud);
        SOAPBodyElement gltp = body.addBodyElement(bodyName);
        Name toKname = envelope.createName("pszXml");
        SOAPElement toKsymbol = gltp.addChildElement(toKname);

        // ✅ Obtener XML firmado como texto
        toKsymbol.addTextNode(req.xmlText());

        message.getMimeHeaders().addHeader("SOAPAction", "");
        URL endpoint = new URL(urlSolicitud);

        System.out.println("✅ Enviando mensaje SOAP...");
        message.writeTo(System.out);
        System.out.println("\n");

        SOAPMessage responseSII = con.call(message, endpoint);

        SOAPPart sp = responseSII.getSOAPPart();
        SOAPBody b = sp.getEnvelope().getBody();

        // ✅ Procesar respuesta con JAXB
        RESPUESTA resp = null;
        for (Iterator<SOAPBodyElement> res = b.getChildElements(
                sp.getEnvelope().createName("getTokenResponse", "ns1", urlSolicitud)); 
                res.hasNext();) {
            for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
                    sp.getEnvelope().createName("getTokenReturn", "ns1", urlSolicitud)); 
                    ret.hasNext();) {

                String xmlResponse = ret.next().getValue();
                
                // ✅ Parsear con JAXB en lugar de XMLBeans
                JAXBContext responseContext = JAXBContext.newInstance(RESPUESTA.class);
                Unmarshaller unmarshaller = responseContext.createUnmarshaller();
                
                // Limpiar el XML de posibles caracteres problemáticos
                String cleanXml = xmlResponse
                    .replaceAll("&#13;", "")
                    .replaceAll("&#10;", "")
                    .trim();
                
                StringReader reader = new StringReader(cleanXml);
                resp = (RESPUESTA) unmarshaller.unmarshal(reader);
            }
        }

        if (resp != null && resp.getRESPHDR().getESTADO() == 0) {
            return resp.getRESPBODY().getTOKEN();
        } else {
            throw new ConexionSiiException("No obtuvo Token: Codigo: "
                    + (resp != null ? resp.getRESPHDR().getESTADO() : "null")
                    + "; Glosa: " + (resp != null ? resp.getRESPHDR().getGLOSA() : "null"));
        }
    }

    @SuppressWarnings("unchecked")
    private String getSemilla() throws UnsupportedOperationException,
            SOAPException, IOException, JAXBException, ConexionSiiException {
        
        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        header.detachNode();

        String urlSolicitud = Utilities.netLabels.getString("URL_SOLICITUD_SEMILLA");

        Name bodyName = envelope.createName("getSeed", "m", urlSolicitud);
        message.getMimeHeaders().addHeader("SOAPAction", "");
        body.addBodyElement(bodyName);

        URL endpoint = new URL(urlSolicitud);

        SOAPMessage responseSII = con.call(message, endpoint);

        SOAPPart sp = responseSII.getSOAPPart();
        SOAPBody b = sp.getEnvelope().getBody();

        // ✅ Procesar respuesta con JAXB
        RESPUESTA resp = null;
        for (Iterator<SOAPBodyElement> res = b.getChildElements(
                sp.getEnvelope().createName("getSeedResponse", "ns1", urlSolicitud)); 
                res.hasNext();) {
            for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
                    sp.getEnvelope().createName("getSeedReturn", "ns1", urlSolicitud)); 
                    ret.hasNext();) {

                String xmlResponse = ret.next().getValue();
                
                // ✅ Parsear con JAXB en lugar de XMLBeans
                JAXBContext responseContext = JAXBContext.newInstance(RESPUESTA.class);
                Unmarshaller unmarshaller = responseContext.createUnmarshaller();
                
                // Limpiar el XML de posibles caracteres problemáticos
                String cleanXml = xmlResponse
                    .replaceAll("&#13;", "")
                    .replaceAll("&#10;", "")
                    .trim();
                
                StringReader reader = new StringReader(cleanXml);
                resp = (RESPUESTA) unmarshaller.unmarshal(reader);
            }
        }

        if (resp != null && resp.getRESPHDR().getESTADO() == 0) {
            return resp.getRESPBODY().getSEMILLA();
        } else {
            throw new ConexionSiiException("No obtuvo Semilla: Codigo: "
                    + (resp != null ? resp.getRESPHDR().getESTADO() : "null")
                    + "; Glosa: " + (resp != null ? resp.getRESPHDR().getGLOSA() : "null"));
        }
    }

    /**
     * Consulta el estado de un DTE en el ambiente de produccion del SII
     */
    public RESPUESTADocument getEstadoDTEProduccion(String rutConsultante,
            Documento dte, String token) throws UnsupportedOperationException,
            SOAPException, MalformedURLException, JAXBException {
        String urlSolicitud = Utilities.netLabels
                .getString("URL_CONSULTA_ESTADO_DTE_PRODUCCION");
        return getEstadoDTE(rutConsultante, dte, token, urlSolicitud);
    }

    /**
     * Consulta el estado de un DTE en el ambiente de certificacion del SII
     */
    public RESPUESTADocument getEstadoDTECertificacion(String rutConsultante,
            Documento dte, String token) throws UnsupportedOperationException,
            SOAPException, MalformedURLException, JAXBException {
        String urlSolicitud = Utilities.netLabels
                .getString("URL_CONSULTA_ESTADO_DTE_CERTIFICACION");
        return getEstadoDTE(rutConsultante, dte, token, urlSolicitud);
    }

    @SuppressWarnings("unchecked")
    private RESPUESTADocument getEstadoDTE(String rutConsultante,
            Documento dte, String token, String urlSolicitud)
            throws UnsupportedOperationException, SOAPException,
            MalformedURLException, JAXBException {

        String rutEmisor = dte.getEncabezado().getEmisor().getRUTEmisor();
        String rutReceptor = dte.getEncabezado().getReceptor().getRUTRecep();
        Integer tipoDTE = dte.getEncabezado().getIdDoc().getTipoDTE().intValue();
        BigInteger folioDTE = dte.getEncabezado().getIdDoc().getFolio();
        String fechaEmision = Utilities.fechaEstadoDte.format(
            dte.getEncabezado().getIdDoc().getFchEmis().getTime());
        BigInteger montoTotal = dte.getEncabezado().getTotales().getMntTotal();

        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        header.detachNode();

        Name bodyName = envelope.createName("getEstDte", "m", urlSolicitud);
        SOAPBodyElement gltp = body.addBodyElement(bodyName);

        addSOAPParameter(envelope, gltp, "RutConsultante", 
            rutConsultante.substring(0, rutConsultante.length() - 2));
        addSOAPParameter(envelope, gltp, "DvConsultante", 
            rutConsultante.substring(rutConsultante.length() - 1));
        addSOAPParameter(envelope, gltp, "RutCompania", 
            rutEmisor.substring(0, rutEmisor.length() - 2));
        addSOAPParameter(envelope, gltp, "DvCompania", 
            rutEmisor.substring(rutEmisor.length() - 1));
        addSOAPParameter(envelope, gltp, "RutReceptor", 
            rutReceptor.substring(0, rutReceptor.length() - 2));
        addSOAPParameter(envelope, gltp, "DvReceptor", 
            rutReceptor.substring(rutReceptor.length() - 1));
        addSOAPParameter(envelope, gltp, "TipoDte", Integer.toString(tipoDTE));
        addSOAPParameter(envelope, gltp, "FolioDte", folioDTE.toString());
        addSOAPParameter(envelope, gltp, "FechaEmisionDte", fechaEmision);
        addSOAPParameter(envelope, gltp, "MontoDte", montoTotal.toString());
        addSOAPParameter(envelope, gltp, "Token", token);

        message.getMimeHeaders().addHeader("SOAPAction", "");
        URL endpoint = new URL(urlSolicitud);

        SOAPMessage responseSII = con.call(message, endpoint);

        SOAPPart sp = responseSII.getSOAPPart();
        SOAPBody b = sp.getEnvelope().getBody();

        for (Iterator<SOAPBodyElement> res = b.getChildElements(
                sp.getEnvelope().createName("getEstDteResponse", "ns1", urlSolicitud)); 
                res.hasNext();) {
            for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
                    sp.getEnvelope().createName("getEstDteReturn", "ns1", urlSolicitud)); 
                    ret.hasNext();) {

                String xmlResponse = ret.next().getValue();
                
                try {
                    RESPUESTADocument doc = RESPUESTADocument.Factory.parse(xmlResponse);
                    return doc;
                } catch (XmlException e) {
                    throw new SOAPException("Error parseando respuesta SOAP", e);
                }
            }
        }

        return null;
    }

    /**
     * Helper para agregar parámetros SOAP
     */
    private void addSOAPParameter(SOAPEnvelope envelope, SOAPBodyElement parent, 
            String name, String value) throws SOAPException {
        Name paramName = envelope.createName(name);
        SOAPElement paramElement = parent.addChildElement(paramName);
        paramElement.addTextNode(value);
    }

    /**
     * Envia el archivo XML indicado al ambiente de produccion del SII.
     */
    public RECEPCIONDTEDocument uploadEnvioProduccion(String rutEnvia,
            String rutCompania, File archivoEnviarSII, String token)
            throws IOException, JAXBException, InterruptedException {
        String urlEnvio = Utilities.netLabels.getString("URL_UPLOAD_PRODUCCION");
        String hostEnvio = Utilities.netLabels.getString("HOST_UPLOAD_PRODUCCION");
        return uploadEnvio(rutEnvia, rutCompania, archivoEnviarSII, token,
                urlEnvio, hostEnvio);
    }

    /**
     * Envia el archivo XML indicado al ambiente de certificacion del SII.
     */
    public RECEPCIONDTEDocument uploadEnvioCertificacion(String rutEnvia,
            String rutCompania, File archivoEnviarSII, String token)
            throws ClientProtocolException, IOException, JAXBException, InterruptedException {
        String urlEnvio = Utilities.netLabels.getString("URL_UPLOAD_CERTIFICACION");
        String hostEnvio = Utilities.netLabels.getString("HOST_UPLOAD_CERTIFICACION");
        return uploadEnvio(rutEnvia, rutCompania, archivoEnviarSII, token,
                urlEnvio, hostEnvio);
    }

    private RECEPCIONDTEDocument uploadEnvio(String rutEnvia,
            String rutCompania, File archivoEnviarSII, String token,
            String urlEnvio, String hostEnvio) 
            throws IOException, JAXBException, InterruptedException {

        // ✅ Crear boundary para multipart
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        
        // ✅ Construir cuerpo multipart manualmente
        StringBuilder multipartBody = new StringBuilder();
        
        // Agregar campos de texto
        multipartBody.append("--").append(boundary).append("\r\n");
        multipartBody.append("Content-Disposition: form-data; name=\"rutSender\"\r\n\r\n");
        multipartBody.append(rutEnvia.substring(0, rutEnvia.length() - 2)).append("\r\n");
        
        multipartBody.append("--").append(boundary).append("\r\n");
        multipartBody.append("Content-Disposition: form-data; name=\"dvSender\"\r\n\r\n");
        multipartBody.append(rutEnvia.substring(rutEnvia.length() - 1)).append("\r\n");
        
        multipartBody.append("--").append(boundary).append("\r\n");
        multipartBody.append("Content-Disposition: form-data; name=\"rutCompany\"\r\n\r\n");
        multipartBody.append(rutCompania.substring(0, rutCompania.length() - 2)).append("\r\n");
        
        multipartBody.append("--").append(boundary).append("\r\n");
        multipartBody.append("Content-Disposition: form-data; name=\"dvCompany\"\r\n\r\n");
        multipartBody.append(rutCompania.substring(rutCompania.length() - 1)).append("\r\n");
        
        // Agregar archivo
        multipartBody.append("--").append(boundary).append("\r\n");
        multipartBody.append("Content-Disposition: form-data; name=\"archivo\"; filename=\"")
                    .append(archivoEnviarSII.getName()).append("\"\r\n");
        multipartBody.append("Content-Type: application/xml\r\n\r\n");
        
        // Leer contenido del archivo
        String fileContent = Files.readString(archivoEnviarSII.toPath());
        multipartBody.append(fileContent).append("\r\n");
        
        // Cerrar multipart
        multipartBody.append("--").append(boundary).append("--\r\n");

        // ✅ Crear HTTP Client nativo de Java
        HttpClient client = HttpClient.newBuilder()
            .cookieHandler(CookieHandler.getDefault())
            .build();

        // ✅ Crear request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlEnvio))
            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
            .header("Cookie", "TOKEN=" + token)
            .header("User-Agent", Utilities.netLabels.getString("UPLOAD_SII_HEADER_VALUE"))
            .POST(HttpRequest.BodyPublishers.ofString(multipartBody.toString()))
            .build();

        // ✅ Enviar request
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());

        // ✅ Parsear respuesta usando el wrapper JAXB
        try {
            return RECEPCIONDTEDocument.Factory.parse(response.body());
        } catch (XmlException e) {
            throw new IOException("Error parseando respuesta del SII", e);
        }
    }
}
