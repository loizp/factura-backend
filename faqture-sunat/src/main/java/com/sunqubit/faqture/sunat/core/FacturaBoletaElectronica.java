package com.sunqubit.faqture.sunat.core;

import org.apache.xml.security.utils.ElementProxy;

import static com.sunqubit.faqture.beans.utils.ConstantProperty.XML_EXT;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.ZIP_EXT;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.GCSTORE_BUCKET;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.QRNAME_EXT;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.PDF417NAME_EXT;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;

import com.google.common.net.MediaType;
import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.DetalleDocumento;
import com.sunqubit.faqture.beans.core.Leyenda;
import com.sunqubit.faqture.beans.rest.ServiceResponse;
import com.sunqubit.faqture.beans.utils.AESCipher;
import com.sunqubit.faqture.beans.utils.StoreManager;
import com.sunqubit.faqture.sunat.utils.DigitalFileCreator;
import com.sunqubit.faqture.sunat.utils.HeaderHandlerResolver;
import com.sunqubit.faqture.sunat.utils.LecturaXML;

import pe.gob.sunat.service.NoExisteWSSunatException;

public class FacturaBoletaElectronica {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacturaBoletaElectronica.class);

    static {
        org.apache.xml.security.Init.init();
    }
    
    public static ServiceResponse generarQRPdf417(ComprobantePago factura) {
    	Boolean ok = true;
        String msg = "Creación de las imágenes codigos QR y PDF417 correctamente";
        ComprobantePago res = factura;
        String qrpath = "", pathXMLFile = "";
        
        String foldername = factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo() + "-" +  factura.getEmpresa().getNumeroDocumento();
        String qrname = factura.getEmpresa().getNumeroDocumento() + "-" + factura.getTipoDocumento().getCodigo() + "-" + factura.getNumero();
        if(StoreManager.store == 1) {
        	qrpath = StoreManager.getDirectorioLocal(4, foldername, factura.getFechaEmision());
        	pathXMLFile = StoreManager.getDirectorioLocal(2, foldername, factura.getFechaEmision()) + qrname + XML_EXT;
        }
        if(StoreManager.store == 2) {
        	qrpath = StoreManager.getDirectorioGCloud(4, foldername, factura.getFechaEmision());
        	pathXMLFile = StoreManager.getDirectorioGCloud(2, foldername, factura.getFechaEmision())	+ qrname + XML_EXT;
        }
        qrpath += qrname;
        
        String hashCode = LecturaXML.getDigestValue(pathXMLFile);
        if(!hashCode.equals("")) res.setHashSunat(hashCode);
        String firma = LecturaXML.getSignature(pathXMLFile);
        
        String infobarcode = factura.getEmpresa().getNumeroDocumento() + "|";
		infobarcode += factura.getTipoDocumento().getCodigo() + "|";
		infobarcode += factura.getNumero().replaceAll("-", "|") + "|";
		if(!factura.getIgv().equals(BigDecimal.valueOf(0.00))) infobarcode += factura.getIgv().toString() + "|";
		else infobarcode += "|";
		infobarcode += factura.getTotal().toString() + "|";
		infobarcode += factura.getFechaEmision().toString() + "|";
		infobarcode += factura.getCliente().getTipoDocumentoIdentidad().getCodigo() + "|";
		if(factura.getCliente().getTipoDocumentoIdentidad().getCodigo().equals("0")) infobarcode += "|";
		else infobarcode += factura.getCliente().getNumeroDocumento() + "|";
		infobarcode += hashCode + "|" + firma + "|";
        ok = DigitalFileCreator.creaImgQRPdf417(infobarcode, qrpath);
        if(ok) {
        	String urlCode = "https://" + GCSTORE_BUCKET + ".storage.googleapis.com/" + qrpath;
        	res.setLinkQR(urlCode+QRNAME_EXT);
        	res.setLinkPDF417(urlCode+PDF417NAME_EXT);
        }
        return new ServiceResponse(ok, msg, res);
    }
    
    public static ServiceResponse generarXMLZipiado(ComprobantePago factura) {
        org.apache.xml.security.Init.init();

        Boolean ok = true;
        String msg = "creación del archivo XML y comprimido correctamente";
        ComprobantePago res = factura;
        String unidadEnvio = "";
        String foldername = factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo() + "-" +  factura.getEmpresa().getNumeroDocumento();
        if(StoreManager.store == 1) unidadEnvio = StoreManager.getDirectorioLocal(2, foldername, factura.getFechaEmision());
        if(StoreManager.store == 2) unidadEnvio = StoreManager.getDirectorioGCloud(2, foldername, factura.getFechaEmision());
        try {
        	LOGGER.info("FactE - generarXMLZipiado | RUC: " + factura.getEmpresa().getNumeroDocumento());
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
            String key = factura.getEmpresa().getNumeroDocumento() + factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo();
            String keystoreType = factura.getEmpresa().getKeystoreType();
            String keystoreFile = "";
            if(StoreManager.store == 1) keystoreFile = StoreManager.getDirectorioLocal(1, foldername, null) + factura.getEmpresa().getKeystoreFile();
            if(StoreManager.store == 2) keystoreFile = StoreManager.getDirectorioGCloud(1, foldername, null) + factura.getEmpresa().getKeystoreFile();
            String keystorePass = AESCipher.desencripta(factura.getEmpresa().getKeystorePass(), key);
            String privateKeyAlias = AESCipher.desencripta(factura.getEmpresa().getPrivateKeyAlias(), key);
            String privateKeyPass = AESCipher.desencripta(factura.getEmpresa().getPrivateKeyPass(), key);
            String certificateAlias = AESCipher.desencripta(factura.getEmpresa().getCertificateAlias(), key);
            String anticipoCero1001 = "0";
            String anticipoCero1002 = "0";
            String anticipoCero1003 = "0";
            LOGGER.info("FactE - generarXMLZipiado | Lectura de cerificado ");
            CDATASection cdata;

            LOGGER.info("FactE - generarXMLZipiado | Iniciamos la generacion del XML");
            String xmlname = factura.getEmpresa().getNumeroDocumento() + "-" + factura.getTipoDocumento().getCodigo() + "-" + factura.getNumero() + XML_EXT;
            String pathXMLFile = unidadEnvio + xmlname;
            File signatureFile = null;
            Path signaturePath = null;
            
            KeyStore ks = KeyStore.getInstance(keystoreType);
            if(StoreManager.store == 1) {
            	signatureFile = new File(pathXMLFile);
            	FileInputStream fis = new FileInputStream(keystoreFile);
            	ks.load(fis, keystorePass.toCharArray());
            }
            if(StoreManager.store == 2) {
            	signaturePath = Paths.get(pathXMLFile);
            	ks.load(StoreManager.getFileStore(keystoreFile), keystorePass.toCharArray());
            }

            PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
            if (privateKey == null) {
                throw new RuntimeException("Private key is null");
            }
            X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);
            javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();

            LOGGER.info("FactE - generarXMLZipiado | cabecera XML");
            Element envelope = doc.createElementNS("", "Invoice");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            envelope.appendChild(doc.createTextNode("\n"));
            doc.appendChild(envelope);

            Element UBLExtensions = doc.createElementNS("", "ext:UBLExtensions");
            envelope.appendChild(UBLExtensions);
            Element UBLExtension2 = doc.createElementNS("", "ext:UBLExtension");
            UBLExtension2.appendChild(doc.createTextNode("\n"));
            Element ExtensionContent2 = doc.createElementNS("", "ext:ExtensionContent");
            ExtensionContent2.appendChild(doc.createTextNode("\n"));

            Element UBLExtension = doc.createElementNS("", "ext:UBLExtension");
            envelope.appendChild(UBLExtension);
            Element ExtensionContent = doc.createElementNS("", "ext:ExtensionContent");
            envelope.appendChild(ExtensionContent);

            Element AdditionalInformation = doc.createElementNS("", "sac:AdditionalInformation");
            envelope.appendChild(AdditionalInformation);
            AdditionalInformation.appendChild(doc.createTextNode("\n"));
            
            if (!factura.getGrabada().equals(BigDecimal.valueOf(0.00))) {
                Element AdditionalMonetaryTotal1 = doc.createElementNS("", "sac:AdditionalMonetaryTotal");
                envelope.appendChild(AdditionalMonetaryTotal1);
                AdditionalMonetaryTotal1.appendChild(doc.createTextNode("\n"));

                Element ID1 = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID1);
                ID1.appendChild(doc.createTextNode("1001"));

                Element PayableAmount1 = doc.createElementNS("", "cbc:PayableAmount");
                PayableAmount1.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PayableAmount1.setIdAttributeNS(null, "currencyID", true);
                envelope.appendChild(PayableAmount1);
                PayableAmount1.appendChild(doc.createTextNode(factura.getGrabada().toString()));
                AdditionalInformation.appendChild(AdditionalMonetaryTotal1);
                AdditionalMonetaryTotal1.appendChild(ID1);
                AdditionalMonetaryTotal1.appendChild(PayableAmount1);
                anticipoCero1001 = "1";
            }
            if (!factura.getInafecta().equals(BigDecimal.valueOf(0.00))) {
                Element AdditionalMonetaryTotal2 = doc.createElementNS("", "sac:AdditionalMonetaryTotal");
                envelope.appendChild(AdditionalMonetaryTotal2);
                AdditionalMonetaryTotal2.appendChild(doc.createTextNode("\n"));

                Element ID2 = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID2);
                ID2.appendChild(doc.createTextNode("1002"));

                Element PayableAmount2 = doc.createElementNS("", "cbc:PayableAmount");
                PayableAmount2.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PayableAmount2.setIdAttributeNS(null, "currencyID", true);
                envelope.appendChild(PayableAmount2);
                PayableAmount2.appendChild(doc.createTextNode(factura.getInafecta().toString()));
                AdditionalInformation.appendChild(AdditionalMonetaryTotal2);
                AdditionalMonetaryTotal2.appendChild(ID2);
                AdditionalMonetaryTotal2.appendChild(PayableAmount2);
                anticipoCero1002 = "1";
            }
            if (!factura.getExonerada().equals(BigDecimal.valueOf(0.00))) {
                Element AdditionalMonetaryTotal3 = doc.createElementNS("", "sac:AdditionalMonetaryTotal");
                envelope.appendChild(AdditionalMonetaryTotal3);
                AdditionalMonetaryTotal3.appendChild(doc.createTextNode("\n"));

                Element ID3 = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID3);
                ID3.appendChild(doc.createTextNode("1003"));

                Element PayableAmount3 = doc.createElementNS("", "cbc:PayableAmount");
                PayableAmount3.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PayableAmount3.setIdAttributeNS(null, "currencyID", true);
                envelope.appendChild(PayableAmount3);
                PayableAmount3.appendChild(doc.createTextNode(factura.getExonerada().toString()));
                AdditionalInformation.appendChild(AdditionalMonetaryTotal3);
                AdditionalMonetaryTotal3.appendChild(ID3);
                AdditionalMonetaryTotal3.appendChild(PayableAmount3);
                anticipoCero1003 = "1";
            }
            if (!factura.getGratuita().equals(BigDecimal.valueOf(0.00))) {
                Element AdditionalMonetaryTotal4 = doc.createElementNS("", "sac:AdditionalMonetaryTotal");
                envelope.appendChild(AdditionalMonetaryTotal4);
                AdditionalMonetaryTotal4.appendChild(doc.createTextNode("\n"));

                Element ID4 = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID4);
                ID4.appendChild(doc.createTextNode("1004"));

                Element PayableAmount4 = doc.createElementNS("", "cbc:PayableAmount");
                PayableAmount4.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PayableAmount4.setIdAttributeNS(null, "currencyID", true);
                envelope.appendChild(PayableAmount4);
                PayableAmount4.appendChild(doc.createTextNode(factura.getGratuita().toString()));
                AdditionalInformation.appendChild(AdditionalMonetaryTotal4);
                AdditionalMonetaryTotal4.appendChild(ID4);
                AdditionalMonetaryTotal4.appendChild(PayableAmount4);
            }
            if (!factura.getDescuento().equals(BigDecimal.valueOf(0.00))) {
                Element AdditionalMonetaryTotal5 = doc.createElementNS("", "sac:AdditionalMonetaryTotal");
                envelope.appendChild(AdditionalMonetaryTotal5);
                AdditionalMonetaryTotal5.appendChild(doc.createTextNode("\n"));

                Element ID10 = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID10);
                ID10.appendChild(doc.createTextNode("2005"));

                Element PayableAmount5 = doc.createElementNS("", "cbc:PayableAmount");
                PayableAmount5.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PayableAmount5.setIdAttributeNS(null, "currencyID", true);
                envelope.appendChild(PayableAmount5);
                PayableAmount5.appendChild(doc.createTextNode(factura.getDescuento().toString()));
                AdditionalInformation.appendChild(AdditionalMonetaryTotal5);
                AdditionalMonetaryTotal5.appendChild(ID10);
                AdditionalMonetaryTotal5.appendChild(PayableAmount5);
            }
            for (Leyenda leyenda : factura.getLeyendas()) {
                Element AdditionalProperty = doc.createElementNS("", "sac:AdditionalProperty");
                envelope.appendChild(AdditionalProperty);
                AdditionalProperty.appendChild(doc.createTextNode("\n"));

                Element ID = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID);
                ID.appendChild(doc.createTextNode(leyenda.getTipoLeyenda().getCodigo()));

                Element Value = doc.createElementNS("", "cbc:Value");
                envelope.appendChild(Value);
                cdata = doc.createCDATASection(leyenda.getDescripcion());
                Value.appendChild(cdata);
                AdditionalInformation.appendChild(AdditionalProperty);
                AdditionalProperty.appendChild(ID);
                AdditionalProperty.appendChild(Value);
            }
            String BaseURI = ""; 
            if(StoreManager.store == 1) BaseURI = signatureFile.toURI().toURL().toString();
            if(StoreManager.store == 2) BaseURI = signaturePath.toUri().toURL().toString();
            XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

            ExtensionContent.appendChild(sig.getElement());
            UBLExtension.appendChild(ExtensionContent);
            UBLExtensions.appendChild(UBLExtension);
            UBLExtensions.appendChild(UBLExtension2);
            UBLExtension2.appendChild(ExtensionContent2);
            ExtensionContent2.appendChild(AdditionalInformation);

            Element UBLVersionID = doc.createElementNS("", "cbc:UBLVersionID");
            envelope.appendChild(UBLVersionID);
            UBLVersionID.appendChild(doc.createTextNode("2.0"));

            Element CustomizationID = doc.createElementNS("", "cbc:CustomizationID");
            envelope.appendChild(CustomizationID);
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            
            Element ID5 = doc.createElementNS("", "cbc:ID");
            envelope.appendChild(ID5);
            ID5.appendChild(doc.createTextNode(factura.getNumero()));
            
            Element IssueDate = doc.createElementNS("", "cbc:IssueDate");
            envelope.appendChild(IssueDate);
            IssueDate.appendChild(doc.createTextNode(factura.getFechaEmision().toString()));
            
            Element InvoiceTypeCode = doc.createElementNS("", "cbc:InvoiceTypeCode");
            envelope.appendChild(InvoiceTypeCode);
            InvoiceTypeCode.appendChild(doc.createTextNode(factura.getTipoDocumento().getCodigo()));

            Element DocumentCurrencyCode = doc.createElementNS("", "cbc:DocumentCurrencyCode");
            envelope.appendChild(DocumentCurrencyCode);
            DocumentCurrencyCode.appendChild(doc.createTextNode(factura.getMoneda().getCodigo()));

            Element Signature = doc.createElementNS("", "cac:Signature");
            envelope.appendChild(Signature);
            Signature.appendChild(doc.createTextNode("\n"));

            Element ID6 = doc.createElementNS("", "cbc:ID");
            Signature.appendChild(ID6);
            ID6.appendChild(doc.createTextNode(factura.getEmpresa().getNumeroDocumento()));

            Element SignatoryParty = doc.createElementNS("", "cac:SignatoryParty");
            Signature.appendChild(SignatoryParty);
            SignatoryParty.appendChild(doc.createTextNode("\n"));

            Element PartyIdentification = doc.createElementNS("", "cac:PartyIdentification");
            SignatoryParty.appendChild(PartyIdentification);
            PartyIdentification.appendChild(doc.createTextNode("\n"));

            Element ID7 = doc.createElementNS("", "cbc:ID");
            PartyIdentification.appendChild(ID7);
            ID7.appendChild(doc.createTextNode(factura.getEmpresa().getNumeroDocumento()));

            Element PartyName = doc.createElementNS("", "cac:PartyName");
            SignatoryParty.appendChild(PartyName);
            PartyName.appendChild(doc.createTextNode("\n"));

            Element Name = doc.createElementNS("", "cbc:Name");
            PartyName.appendChild(Name);
            cdata = doc.createCDATASection(factura.getEmpresa().getNombreLegal());
            Name.appendChild(cdata);

            Element DigitalSignatureAttachment = doc.createElementNS("", "cac:DigitalSignatureAttachment");
            Signature.appendChild(DigitalSignatureAttachment);
            DigitalSignatureAttachment.appendChild(doc.createTextNode("\n"));

            Element ExternalReference = doc.createElementNS("", "cac:ExternalReference");
            DigitalSignatureAttachment.appendChild(ExternalReference);
            ExternalReference.appendChild(doc.createTextNode("\n"));

            Element URI = doc.createElementNS("", "cbc:URI");
            ExternalReference.appendChild(URI);
            URI.appendChild(doc.createTextNode(factura.getEmpresa().getNumeroDocumento()));

            Element AccountingSupplierParty = doc.createElementNS("", "cac:AccountingSupplierParty");
            envelope.appendChild(AccountingSupplierParty);
            AccountingSupplierParty.appendChild(doc.createTextNode("\n"));

            Element CustomerAssignedAccountID = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);
            CustomerAssignedAccountID.appendChild(doc.createTextNode(factura.getEmpresa().getNumeroDocumento()));

            Element AdditionalAccountID = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingSupplierParty.appendChild(AdditionalAccountID);
            AdditionalAccountID.appendChild(doc.createTextNode(factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo()));

            Element Party = doc.createElementNS("", "cac:Party");
            AccountingSupplierParty.appendChild(Party);
            Party.appendChild(doc.createTextNode("\n"));

            Element PartyName1 = doc.createElementNS("", "cac:PartyName");
            Party.appendChild(PartyName1);
            PartyName1.appendChild(doc.createTextNode("\n"));

            Element Name2 = doc.createElementNS("", "cbc:Name");
            PartyName1.appendChild(Name2);
            cdata = doc.createCDATASection(factura.getEmpresa().getNombreLegal());
            Name2.appendChild(cdata);

            Element PostalAddress = doc.createElementNS("", "cac:PostalAddress");
            Party.appendChild(PostalAddress);
            PostalAddress.appendChild(doc.createTextNode("\n"));

            Element ID8 = doc.createElementNS("", "cbc:ID");
            PostalAddress.appendChild(ID8);
            ID8.appendChild(doc.createTextNode(factura.getEmpresa().getUbigeo().getCodigo()));

            Element StreetName = doc.createElementNS("", "cbc:StreetName");
            PostalAddress.appendChild(StreetName);
            cdata = doc.createCDATASection(factura.getEmpresa().getDireccion());
            StreetName.appendChild(cdata);

            Element CityName = doc.createElementNS("", "cbc:CityName");
            PostalAddress.appendChild(CityName);
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getProvincia());
            CityName.appendChild(cdata);

            Element CountrySubentity = doc.createElementNS("", "cbc:CountrySubentity");
            PostalAddress.appendChild(CountrySubentity);
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getDepartamento());
            CountrySubentity.appendChild(cdata);

            Element District = doc.createElementNS("", "cbc:District");
            PostalAddress.appendChild(District);
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getDistrito());
            District.appendChild(cdata);

            Element Country = doc.createElementNS("", "cac:Country");
            PostalAddress.appendChild(Country);
            Country.appendChild(doc.createTextNode("\n"));

            Element IdentificationCode = doc.createElementNS("", "cbc:IdentificationCode");
            Country.appendChild(IdentificationCode);
            cdata = doc.createCDATASection(factura.getEmpresa().getPais().getCodigo());
            IdentificationCode.appendChild(cdata);

            Element PartyLegalEntity = doc.createElementNS("", "cac:PartyLegalEntity");
            Party.appendChild(PartyLegalEntity);
            PartyLegalEntity.appendChild(doc.createTextNode("\n"));

            Element RegistrationName = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity.appendChild(RegistrationName);
            cdata = doc.createCDATASection(factura.getEmpresa().getNombreLegal());
            RegistrationName.appendChild(cdata);

            Element AccountingCustomerParty = doc.createElementNS("", "cac:AccountingCustomerParty");
            envelope.appendChild(AccountingCustomerParty);
            AccountingCustomerParty.appendChild(doc.createTextNode("\n"));
            
            Element CustomerAssignedAccountID1 = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingCustomerParty.appendChild(CustomerAssignedAccountID1);
            CustomerAssignedAccountID1.appendChild(doc.createTextNode(factura.getCliente().getNumeroDocumento()));

            Element AdditionalAccountID1 = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingCustomerParty.appendChild(AdditionalAccountID1);
            AdditionalAccountID1.appendChild(doc.createTextNode(factura.getCliente().getTipoDocumentoIdentidad().getCodigo()));

            Element Party1 = doc.createElementNS("", "cac:Party");
            AccountingCustomerParty.appendChild(Party1);
            Party1.appendChild(doc.createTextNode("\n"));

            Element PartyLegalEntity1 = doc.createElementNS("", "cac:PartyLegalEntity");
            Party1.appendChild(PartyLegalEntity1);
            PartyLegalEntity1.appendChild(doc.createTextNode("\n"));

            Element RegistrationName1 = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity1.appendChild(RegistrationName1);
            cdata = doc.createCDATASection(factura.getCliente().getNombreLegal());
            RegistrationName1.appendChild(cdata);
            
            if (!factura.getIgv().equals(BigDecimal.valueOf(0.00))) {
                Element TaxTotal = doc.createElementNS("", "cac:TaxTotal");
                envelope.appendChild(TaxTotal);
                TaxTotal.appendChild(doc.createTextNode("\n"));

                Element TaxAmount = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount.setIdAttributeNS(null, "currencyID", true);
                TaxTotal.appendChild(TaxAmount);
                TaxAmount.appendChild(doc.createTextNode(factura.getIgv().toString()));

                Element TaxSubtotal = doc.createElementNS("", "cac:TaxSubtotal");
                TaxTotal.appendChild(TaxSubtotal);
                TaxSubtotal.appendChild(doc.createTextNode("\n"));

                Element TaxAmount1 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount1.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount1.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal.appendChild(TaxAmount1);
                TaxAmount1.appendChild(doc.createTextNode(factura.getIgv().toString()));

                Element TaxCategory = doc.createElementNS("", "cac:TaxCategory");
                TaxSubtotal.appendChild(TaxCategory);
                TaxCategory.appendChild(doc.createTextNode("\n"));

                Element TaxScheme = doc.createElementNS("", "cac:TaxScheme");
                TaxCategory.appendChild(TaxScheme);
                TaxScheme.appendChild(doc.createTextNode("\n"));

                Element ID9 = doc.createElementNS("", "cbc:ID");
                TaxScheme.appendChild(ID9);
                ID9.appendChild(doc.createTextNode("1000"));

                Element Name3 = doc.createElementNS("", "cbc:Name");
                TaxScheme.appendChild(Name3);
                Name3.appendChild(doc.createTextNode("IGV"));

                Element TaxTypeCode = doc.createElementNS("", "cbc:TaxTypeCode");
                TaxScheme.appendChild(TaxTypeCode);
                TaxTypeCode.appendChild(doc.createTextNode("VAT"));
            }
            Element LegalMonetaryTotal = doc.createElementNS("", "cac:LegalMonetaryTotal");
            envelope.appendChild(LegalMonetaryTotal);
            LegalMonetaryTotal.appendChild(doc.createTextNode("\n"));

            if (!factura.getDescuento().equals(BigDecimal.valueOf(0.00))) {
                Element AllowanceTotalAmount = doc.createElementNS("", "cbc:AllowanceTotalAmount");
                AllowanceTotalAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                AllowanceTotalAmount.setIdAttributeNS(null, "currencyID", true);
                LegalMonetaryTotal.appendChild(AllowanceTotalAmount);
                AllowanceTotalAmount.appendChild(doc.createTextNode(factura.getDescuento().toString()));
            }

            Element PayableAmount = doc.createElementNS("", "cbc:PayableAmount");
            PayableAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
            PayableAmount.setIdAttributeNS(null, "currencyID", true);
            LegalMonetaryTotal.appendChild(PayableAmount);
            PayableAmount.appendChild(doc.createTextNode(factura.getTotal().toString()));

            LOGGER.info("FactE - generarXMLZipiado | Iniciamos detalle XML ");
            for (DetalleDocumento listaDet : factura.getDetallesDocumento()) {
                Element InvoiceLine = doc.createElementNS("", "cac:InvoiceLine");
                envelope.appendChild(InvoiceLine);
                InvoiceLine.appendChild(doc.createTextNode("\n"));

                Element ID11 = doc.createElementNS("", "cbc:ID");
                InvoiceLine.appendChild(ID11);
                ID11.appendChild(doc.createTextNode(String.valueOf(listaDet.getOrden())));

                Element InvoicedQuantity = doc.createElementNS("", "cbc:InvoicedQuantity");
                InvoicedQuantity.setAttributeNS(null, "unitCode", listaDet.getUnidadMedida().getCodigo());
                InvoicedQuantity.setIdAttributeNS(null, "unitCode", true);
                InvoiceLine.appendChild(InvoicedQuantity);
                InvoicedQuantity.appendChild(doc.createTextNode(String.valueOf(listaDet.getCantidad())));

                Element LineExtensionAmount1 = doc.createElementNS("", "cbc:LineExtensionAmount");
                LineExtensionAmount1.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                LineExtensionAmount1.setIdAttributeNS(null, "currencyID", true);
                InvoiceLine.appendChild(LineExtensionAmount1);
                LineExtensionAmount1.appendChild(doc.createTextNode(listaDet.getSubtotal().toString()));

                Element PricingReference = doc.createElementNS("", "cac:PricingReference");
                InvoiceLine.appendChild(PricingReference);
                PricingReference.appendChild(doc.createTextNode("\n"));

                Element AlternativeConditionPrice = doc.createElementNS("", "cac:AlternativeConditionPrice");
                PricingReference.appendChild(AlternativeConditionPrice);
                AlternativeConditionPrice.appendChild(doc.createTextNode("\n"));

                Element PriceAmount = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                PriceAmount.setIdAttributeNS(null, "currencyID", true);
                AlternativeConditionPrice.appendChild(PriceAmount);
                PriceAmount.appendChild(doc.createTextNode(listaDet.getPrecioVenta().toString()));

                Element PriceTypeCode = doc.createElementNS("", "cbc:PriceTypeCode");
                AlternativeConditionPrice.appendChild(PriceTypeCode);
                PriceTypeCode.appendChild(doc.createTextNode("01"));

                if (!listaDet.getVentaNoOnerosa().equals(BigDecimal.valueOf(0.00))) {
                    Element AlternativeConditionPrice02 = doc.createElementNS("", "cac:AlternativeConditionPrice");
                    PricingReference.appendChild(AlternativeConditionPrice02);
                    AlternativeConditionPrice02.appendChild(doc.createTextNode("\n"));

                    Element PriceAmount02 = doc.createElementNS("", "cbc:PriceAmount");
                    PriceAmount02.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                    PriceAmount02.setIdAttributeNS(null, "currencyID", true);
                    AlternativeConditionPrice02.appendChild(PriceAmount02);
                    PriceAmount02.appendChild(doc.createTextNode(listaDet.getVentaNoOnerosa().toString()));

                    Element PriceTypeCode02 = doc.createElementNS("", "cbc:PriceTypeCode");
                    AlternativeConditionPrice02.appendChild(PriceTypeCode02);
                    PriceTypeCode02.appendChild(doc.createTextNode("02"));
                }
                Element TaxTotal1 = doc.createElementNS("", "cac:TaxTotal");
                InvoiceLine.appendChild(TaxTotal1);
                TaxTotal1.appendChild(doc.createTextNode("\n"));

                Element TaxAmount2 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount2.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount2.setIdAttributeNS(null, "currencyID", true);
                TaxTotal1.appendChild(TaxAmount2);
                TaxAmount2.appendChild(doc.createTextNode(listaDet.getIgv().toString()));

                Element TaxSubtotal1 = doc.createElementNS("", "cac:TaxSubtotal");
                TaxTotal1.appendChild(TaxSubtotal1);
                TaxSubtotal1.appendChild(doc.createTextNode("\n"));

                Element TaxableAmount = doc.createElementNS("", "cbc:TaxableAmount");
                TaxableAmount.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                TaxableAmount.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal1.appendChild(TaxableAmount);
                TaxableAmount.appendChild(doc.createTextNode(listaDet.getSubtotal().toString()));

                Element TaxAmount3 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount3.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                TaxAmount3.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal1.appendChild(TaxAmount3);
                TaxAmount3.appendChild(doc.createTextNode(listaDet.getIgv().toString()));

                Element Percent = doc.createElementNS("", "cbc:Percent");
                TaxSubtotal1.appendChild(Percent);
                Percent.appendChild(doc.createTextNode(factura.getTasaIgv().toString()));

                Element TaxCategory1 = doc.createElementNS("", "cac:TaxCategory");
                TaxSubtotal1.appendChild(TaxCategory1);
                TaxCategory1.appendChild(doc.createTextNode("\n"));

                Element ID12 = doc.createElementNS("", "cbc:ID");
                TaxCategory1.appendChild(ID12);
                ID12.appendChild(doc.createTextNode("VAT"));

                Element TaxExemptionReasonCode = doc.createElementNS("", "cbc:TaxExemptionReasonCode");
                TaxCategory1.appendChild(TaxExemptionReasonCode);
                TaxExemptionReasonCode.appendChild(doc.createTextNode(listaDet.getTipoAfectacionIgv().getCodigo()));

                Element TierRange = doc.createElementNS("", "cbc:TierRange");
                TaxCategory1.appendChild(TierRange);
                TierRange.appendChild(doc.createTextNode("00"));

                Element TaxScheme1 = doc.createElementNS("", "cac:TaxScheme");
                TaxCategory1.appendChild(TaxScheme1);
                TaxScheme1.appendChild(doc.createTextNode("\n"));

                Element ID15 = doc.createElementNS("", "cbc:ID");
                TaxScheme1.appendChild(ID15);
                ID15.appendChild(doc.createTextNode("1000"));

                Element Name9 = doc.createElementNS("", "cbc:Name");
                TaxScheme1.appendChild(Name9);
                Name9.appendChild(doc.createTextNode("IGV"));

                Element TaxTypeCode1 = doc.createElementNS("", "cbc:TaxTypeCode");
                TaxScheme1.appendChild(TaxTypeCode1);
                TaxTypeCode1.appendChild(doc.createTextNode("VAT"));

                Element Item = doc.createElementNS("", "cac:Item");
                InvoiceLine.appendChild(Item);
                Item.appendChild(doc.createTextNode("\n"));

                Element Description = doc.createElementNS("", "cbc:Description");
                Item.appendChild(Description);
                cdata = doc.createCDATASection(listaDet.getDescripcion());
                Description.appendChild(cdata);

                Element SellersItemIdentification = doc.createElementNS("", "cac:SellersItemIdentification");
                Item.appendChild(SellersItemIdentification);
                SellersItemIdentification.appendChild(doc.createTextNode("\n"));

                Element ID18 = doc.createElementNS("", "cbc:ID");
                SellersItemIdentification.appendChild(ID18);
                ID18.appendChild(doc.createTextNode(listaDet.getCodigoProducto()));

                Element Price = doc.createElementNS("", "cac:Price");
                InvoiceLine.appendChild(Price);
                Price.appendChild(doc.createTextNode("\n"));

                Element PriceAmount2 = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount2.setAttributeNS(null, "currencyID", listaDet.getMoneda().getCodigo());
                PriceAmount2.setIdAttributeNS(null, "currencyID", true);
                Price.appendChild(PriceAmount2);
                PriceAmount2.appendChild(doc.createTextNode(listaDet.getPrecioVenta().toString()));
            }
            LOGGER.info("FactE - generarXMLZipiado | Prepara firma digital ");
            sig.setId(factura.getEmpresa().getNumeroDocumento());
            sig.addKeyInfo(cert);
            {
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
            }
            {
                sig.sign(privateKey);
            }
            FileOutputStream fout = null;
            ByteArrayOutputStream bout = null;
            if(StoreManager.store == 1) fout = new FileOutputStream(signatureFile);
            if(StoreManager.store == 2) bout = new ByteArrayOutputStream();
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            tf.setOutputProperty(OutputKeys.STANDALONE, "no");
            StreamResult sr = null;
            if(StoreManager.store == 1) sr = new StreamResult(fout);
            if(StoreManager.store == 2) sr = new StreamResult(bout);
            tf.transform(new DOMSource(doc), sr);
            if(StoreManager.store == 2) StoreManager.saveXMLStore(bout.toByteArray(), pathXMLFile);
            sr.getOutputStream().close();
            res.setLinkXml("https://" + GCSTORE_BUCKET + ".storage.googleapis.com/" + unidadEnvio + xmlname);
            
            LOGGER.info("FactE - generarXMLZipiado | XML creado " + pathXMLFile);
            ok = DigitalFileCreator.crearZip(xmlname, unidadEnvio);
            if(!ok)
            	msg = "Ocurrió un error en la creación del archivo ZIP";
        } catch (Exception ex) {
        	ex.printStackTrace();
            ok = false;
            msg = "Ocurrió un error en la creación del archivo XML";
            LOGGER.error("FactE - generarXMLZipiado | error  " + ex.toString());
        }
        return new ServiceResponse(ok, msg, res);
    }

    public static String enviarASunat(ComprobantePago factura) throws NoExisteWSSunatException {
        String resultado = "";
        String zipFileName = factura.getEmpresa().getNumeroDocumento() + "-" + factura.getTipoDocumento().getCodigo() + "-" + factura.getNumero() + ZIP_EXT;
        String sws = factura.getEmpresa().getTipoEnvio();
        LOGGER.info("FactE - enviarASunat | Prepara ambiente: " + sws);
        String key = factura.getEmpresa().getNumeroDocumento() + factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo();
        String foldername = factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo() + "-" +  factura.getEmpresa().getNumeroDocumento();
        String unidadEnvio = "";
        if(StoreManager.store == 1) unidadEnvio = StoreManager.getDirectorioLocal(2, foldername, factura.getFechaEmision());
        if(StoreManager.store == 2) unidadEnvio = StoreManager.getDirectorioGCloud(2, foldername, factura.getFechaEmision());
        String userEmisor = AESCipher.desencripta(factura.getEmpresa().getUserSunat(), key);
        String passEmisor = AESCipher.desencripta(factura.getEmpresa().getPassSunat(), key);
        try {
        	DataHandler dataHandler = null;
        	if(StoreManager.store == 1) {
        		FileDataSource fileDataSource = new FileDataSource(unidadEnvio + zipFileName);
        		dataHandler = new DataHandler(fileDataSource);
        	}
        	if(StoreManager.store == 2) {
        		DataSource dataSource = new ByteArrayDataSource(StoreManager.getBlobGCloud(unidadEnvio + zipFileName), MediaType.ZIP.toString());
        		dataHandler = new DataHandler(dataSource);
        	}
        	
            byte[] respuestaSunat = null;
            switch (sws) {
                case "1":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe ws1 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver1 = new HeaderHandlerResolver();
                    handlerResolver1.setRucEmisor(factura.getEmpresa().getNumeroDocumento());
                    handlerResolver1.setUserEmisor(userEmisor);
                    handlerResolver1.setPassEmisor(passEmisor);
                    ws1.setHandlerResolver(handlerResolver1);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService port1 = ws1.getBillServicePort();
                    respuestaSunat = port1.sendBill(zipFileName, dataHandler);
                    LOGGER.info("FactE - enviarASunat | Ambiente Beta: " + sws);
                    break;
                case "2":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa ws2 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa();
                    HeaderHandlerResolver handlerResolver2 = new HeaderHandlerResolver();
                    handlerResolver2.setRucEmisor(factura.getEmpresa().getNumeroDocumento());
                    handlerResolver2.setUserEmisor(userEmisor);
                    handlerResolver2.setPassEmisor(passEmisor);
                    ws2.setHandlerResolver(handlerResolver2);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService port2 = ws2.getBillServicePort();
                    respuestaSunat = port2.sendBill(zipFileName, dataHandler);
                    LOGGER.info("FactE - enviarASunat | Ambiente QA " + sws);
                    break;
                case "3":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe ws3 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver3 = new HeaderHandlerResolver();
                    handlerResolver3.setRucEmisor(factura.getEmpresa().getNumeroDocumento());
                    handlerResolver3.setUserEmisor(userEmisor);
                    handlerResolver3.setPassEmisor(passEmisor);
                    ws3.setHandlerResolver(handlerResolver3);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService port3 = ws3.getBillServicePort();
                    respuestaSunat = port3.sendBill(zipFileName, dataHandler);
                    LOGGER.info("FactE - enviarASunat | Ambiente Produccion " + sws);
                    break;
                default:
                    respuestaSunat = null;
                    throw new NoExisteWSSunatException(sws);
            }
            String pathRecepcion = "";
            if(StoreManager.store == 1) {
            	pathRecepcion = StoreManager.getDirectorioLocal(3, foldername, factura.getFechaEmision());
            	FileOutputStream fos = new FileOutputStream(pathRecepcion + "R-" + zipFileName);
            	fos.write(respuestaSunat);
            	fos.close();
            }
            if(StoreManager.store == 2) {
            	pathRecepcion = StoreManager.getDirectorioGCloud(3, foldername, factura.getFechaEmision());
            	StoreManager.saveXMLZipStore(respuestaSunat, pathRecepcion + "R-" + zipFileName);
            }
            /*
            LOGGER.info("FactE - enviarASunat | Descomprimiendo CDR " + pathRecepcion + "R-" + zipFileName);
            ZipFile archive = new ZipFile(pathRecepcion + "R-" + zipFileName);
            Enumeration e = archive.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                File file = new File(pathRecepcion, entry.getName());
//                System.out.println("<<< Quien es: "+file.getName()+">>>>>" );
//                System.out.println("<<< Direcctorio: "+file.isDirectory()+">>>>>" );
//                System.out.println("<<< Archivo: "+file.isFile()+">>>>>" );
                if (!file.isDirectory()) {
                    if (entry.isDirectory() && !file.exists()) {
                        file.mkdirs();
                    } else {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        InputStream in = archive.getInputStream(entry);
                        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                        byte[] buffer = new byte[8192];
                        int read;
                        while (-1 != (read = in.read(buffer))) {
                            out.write(buffer, 0, read);
                        }
                        in.close();
                        out.close();
                    }
                }
            }
            archive.close();
            //System.out.println("Leyendo cdr xml");
            //================leeyendo la resuesta de Sunat
            zipFileName = zipFileName.substring(0, zipFileName.indexOf(".zip"));
            LOGGER.info("enviarASunat - Lectura del contenido del CDR ");
            //resultado = LecturaXML.getRespuestaSunat(trans, pathRecepcion + "R-" + zipFileName + ".xml", conn);
            System.out.println("==>El envio del Zip a sunat fue exitoso");
            LOGGER.info("enviarASunat - Envio a Sunat Exitoso ");*/
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            String errorCode = ex.getFault().getFaultCodeAsQName().getLocalPart();
            errorCode = errorCode.substring(errorCode.indexOf(".") + 1, errorCode.length());
            /*
            resultado = Util.getErrorMesageByCode(errorCode, vruc);
            log.error("enviarASunat - Error SOAP" + ex.toString());
            String sql = "insert into seguimiento(estado_seguimiento, docu_codigo, cdr_code, cdr_nota, cdr_observacion ) values (?,?,?,?,?)";
            try {

                PreparedStatement ps = conn.prepareStatement(sql);
                //===Inserta seguimiento de documento
                String[] cdr = resultado.split("\\|", 0);
                ps.setString(1, "ECDR");
                ps.setString(2, trans);
                ps.setString(3, cdr[0]);
                ps.setString(4, cdr[1]);
                ps.setString(5, ex.toString());
                ps.executeUpdate();
            } catch (SQLException ex1) {
                log.error("enviarASunat - Error ex1 " + ex1.toString());
            }
            */
        } catch (Exception e) {
            LOGGER.error("FactE - enviarASunat | Error: " + e.toString());
            resultado = "0100|Error en el envio de archivo zip a sunat";
            //resultado = "0100|"+e.toString();
            /*
            String sql = "insert into seguimiento(estado_seguimiento, docu_codigo, cdr_code, cdr_nota ) values (?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                //===Inserta seguimiento de documento
                ps.setString(1, "ECDR");
                ps.setString(2, trans);
                ps.setString(3, "0100");
                ps.setString(4, e.toString());
                ps.executeUpdate();
            } catch (SQLException ex1) {
                //Logger.getLogger(BolElectronica.class.getName()).log(Level.SEVERE, null, ex1);
                LOGGER.error("enviarASunat - Error ex1 " + ex1.toString());
            }
			*/
        }
        return resultado;
    }
}
