package com.sunqubit.faqture.sunat.ws;

import org.apache.xml.security.utils.ElementProxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

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

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.DetalleDocumento;
import com.sunqubit.faqture.core.beans.TipoLeyenda;
import com.sunqubit.faqture.sunat.utils.FuncionesUtiles;

public class FacElectronica {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FacElectronica.class);
	
	static {
        org.apache.xml.security.Init.init();
    }
	
	public static String generarXMLZipiadoFactura(ComprobantePago factura) {
		LOGGER.info("generarXMLZipiadoFactura - Inicializamos el ambiente");
		org.apache.xml.security.Init.init();
		
		String resultado = "";
		//String nrodoc = factura.getNumero();
		String unidadEnvio = factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo() + "-" + factura.getEmpresa().getNumeroDocumento();
		//String pathXMLFile;
		
		try {
			LOGGER.info("generarXMLZipiadoFactura - Extraemos datos para preparar XML ");
			LOGGER.info("generarXMLZipiadoFactura - RUC " + factura.getEmpresa().getNumeroDocumento());
			LOGGER.info("generarXMLZipiadoFactura - Iniciamos cabecera ");
			//crear el Xml firmado			
			unidadEnvio = FuncionesUtiles.getDirectorio(1,unidadEnvio, factura.getFechaEmision());
			
			//======================crear XML =======================
            resultado = creaXml(factura, unidadEnvio);

            //======================guardar Hash Y Barcode PDF417 =======================
            //log.info("generarXMLZipiadoFactura - Crear Hashcode y CodeBarPDF417");

            //LecturaXML.guardarHashYBarCodeQR(items.getDocu_tipodocumento(), nrodoc, "DV", pathXMLFile, conn);
            /*======================= ENVIO A SUNAT =============*/
            //log.info("generarXMLZipiadoFactura - Preparando para enviar a SUNAT");
            //resultado = enviarASunat(nrodoc, unidadEnvio, items.getEmpr_nroruc() + "-" + items.getDocu_tipodocumento() + "-" + items.getDocu_numero() + ".zip", conn, items.getEmpr_nroruc(), items.getDocu_fecha());
            //LecturaXML.guardarProcesoEstado(nrodoc, "O", resultado.split("\\|", 0), "", conn);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return resultado;
	}
	
	private static String creaXml(ComprobantePago factura, String unidadEnvio) {
        String resultado = "";
        String tipo_ruc = factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo() + "-" + factura.getEmpresa().getNumeroDocumento();
        try {      	
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
            //Parametros del keystore
            String keystoreType = FuncionesUtiles.getPropertyValue("keystoreType", tipo_ruc);//"JKS";
            String keystoreFile = FuncionesUtiles.getPropertyValue("keystoreFile", tipo_ruc);
            String keystorePass = FuncionesUtiles.getPropertyValue("keystorePass", tipo_ruc);
            String privateKeyAlias = FuncionesUtiles.getPropertyValue("privateKeyAlias", tipo_ruc);
            String privateKeyPass = FuncionesUtiles.getPropertyValue("privateKeyPass", tipo_ruc);
            String certificateAlias = FuncionesUtiles.getPropertyValue("certificateAlias", tipo_ruc);
            String anticipoCero1001 = "0";
            String anticipoCero1002 = "0";
            String anticipoCero1003 = "0";
            LOGGER.info("creaXml - Lectura de cerificado ");
            CDATASection cdata;

            LOGGER.info("creaXml - Iniciamos la generacion del XML");
            String pathXMLFile = unidadEnvio + factura.getEmpresa().getNumeroDocumento() + "-" + factura.getTipoDocumento().getCodigo() + "-" + factura.getNumero() + ".xml";
            File signatureFile = new File(pathXMLFile);

            ///////////////////Creación del certificado//////////////////////////////
            KeyStore ks = KeyStore.getInstance(keystoreType);
            FileInputStream fis = new FileInputStream(keystoreFile);
            ks.load(fis, keystorePass.toCharArray());
            //obtener la clave privada para firmar
            PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
            if (privateKey == null) {
                throw new RuntimeException("Private key is null");
            }
            X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);
            //////////////////////////////////////////////////
            javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            //Firma XML genera espacio para los nombres o tag
            dbf.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();
            ////////////////////////////////////////////////// 
            LOGGER.info("creaXml - cabecera XML ");

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
            //doc.appendChild(doc.createComment(" Preamble "));
            doc.appendChild(envelope);
            //doc.appendChild(doc.createComment(" Postamble "));

            Element UBLExtensions = doc.createElementNS("", "ext:UBLExtensions");
            envelope.appendChild(UBLExtensions);
            Element UBLExtension2 = doc.createElementNS("", "ext:UBLExtension");
            UBLExtension2.appendChild(doc.createTextNode("\n"));
            Element ExtensionContent2 = doc.createElementNS("", "ext:ExtensionContent");
            ExtensionContent2.appendChild(doc.createTextNode("\n"));
            //2do grupo
            Element UBLExtension = doc.createElementNS("", "ext:UBLExtension");
            envelope.appendChild(UBLExtension);
            Element ExtensionContent = doc.createElementNS("", "ext:ExtensionContent");
            envelope.appendChild(ExtensionContent);

            Element AdditionalInformation = doc.createElementNS("", "sac:AdditionalInformation");
            envelope.appendChild(AdditionalInformation);
            AdditionalInformation.appendChild(doc.createTextNode("\n"));
            //agrupa1
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
            //agrupa2
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
            //agrupa3
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
            //agrupa4
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
            //agrupa5
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
            //leyendas
            List<TipoLeyenda> leyendas = new ArrayList<>();
            leyendas.add(factura.getTipoLeyenda());
            
            for (TipoLeyenda leyenda : leyendas) {

                Element AdditionalProperty = doc.createElementNS("", "sac:AdditionalProperty");
                envelope.appendChild(AdditionalProperty);
                AdditionalProperty.appendChild(doc.createTextNode("\n"));

                Element ID = doc.createElementNS("", "cbc:ID");
                envelope.appendChild(ID);
                ID.appendChild(doc.createTextNode(leyenda.getCodigo()));

                Element Value = doc.createElementNS("", "cbc:Value");
                envelope.appendChild(Value);
                cdata = doc.createCDATASection(leyenda.getDescripcion());
                Value.appendChild(cdata);

                AdditionalInformation.appendChild(AdditionalProperty);
                AdditionalProperty.appendChild(ID);
                AdditionalProperty.appendChild(Value);

            }
            //El baseURI es la URI que se utiliza para anteponer a URIs relativos
            String BaseURI = signatureFile.toURI().toURL().toString();
            //Crea un XML Signature objeto desde el documento, BaseURI and signature algorithm (in this case RSA)
            //XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA); Cadena URI que se ajusta a la sintaxis URI y representa el archivo XML de entrada
            XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

            ExtensionContent.appendChild(sig.getElement());
            UBLExtension.appendChild(ExtensionContent);
            UBLExtensions.appendChild(UBLExtension);
            UBLExtensions.appendChild(UBLExtension2);
            UBLExtension2.appendChild(ExtensionContent2);
            ExtensionContent2.appendChild(AdditionalInformation);
//bloque1
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
            InvoiceTypeCode.appendChild(doc.createTextNode(factura.getTipoDocumento().getCodigo()));//DIFERENCIA ENTRE FAC Y ND Y NC

            Element DocumentCurrencyCode = doc.createElementNS("", "cbc:DocumentCurrencyCode");
            envelope.appendChild(DocumentCurrencyCode);
            DocumentCurrencyCode.appendChild(doc.createTextNode(factura.getMoneda().getCodigo()));

//bloque2 cac:Signature--------------------------------------------------------
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
//bloque3 cac:AccountingSupplierParty-----------------------------------------

            Element AccountingSupplierParty = doc.createElementNS("", "cac:AccountingSupplierParty");
            envelope.appendChild(AccountingSupplierParty);
            AccountingSupplierParty.appendChild(doc.createTextNode("\n"));

            Element CustomerAssignedAccountID = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);
            CustomerAssignedAccountID.appendChild(doc.createTextNode(factura.getEmpresa().getNumeroDocumento()));

            Element AdditionalAccountID = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingSupplierParty.appendChild(AdditionalAccountID);
            AdditionalAccountID.appendChild(doc.createTextNode(factura.getEmpresa().getTipoDocumentoIdentidad().getCodigo()));
//***********************************************************
            Element Party = doc.createElementNS("", "cac:Party");
            AccountingSupplierParty.appendChild(Party);
            Party.appendChild(doc.createTextNode("\n"));

            Element PartyName1 = doc.createElementNS("", "cac:PartyName");
            Party.appendChild(PartyName1);//se anade al grupo party
            PartyName1.appendChild(doc.createTextNode("\n"));

            Element Name2 = doc.createElementNS("", "cbc:Name");
            PartyName1.appendChild(Name2);//se anade al grupo partyname1
            cdata = doc.createCDATASection(factura.getEmpresa().getNombreLegal());
            Name2.appendChild(cdata);

            Element PostalAddress = doc.createElementNS("", "cac:PostalAddress");
            Party.appendChild(PostalAddress);//se anade al grupo party
            PostalAddress.appendChild(doc.createTextNode("\n"));

            Element ID8 = doc.createElementNS("", "cbc:ID");
            PostalAddress.appendChild(ID8);//se anade al grupo PostalAddress
            ID8.appendChild(doc.createTextNode(factura.getEmpresa().getUbigeo().getCodigo()));

            Element StreetName = doc.createElementNS("", "cbc:StreetName");
            PostalAddress.appendChild(StreetName);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(factura.getEmpresa().getDireccion());
            StreetName.appendChild(cdata);

            Element CityName = doc.createElementNS("", "cbc:CityName");
            PostalAddress.appendChild(CityName);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getProvincia());
            CityName.appendChild(cdata);

            Element CountrySubentity = doc.createElementNS("", "cbc:CountrySubentity");
            PostalAddress.appendChild(CountrySubentity);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getDepartamento());
            CountrySubentity.appendChild(cdata);

            Element District = doc.createElementNS("", "cbc:District");
            PostalAddress.appendChild(District);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(factura.getEmpresa().getUbigeo().getDistrito());
            District.appendChild(cdata);

            Element Country = doc.createElementNS("", "cac:Country");
            PostalAddress.appendChild(Country);//se anade al grupo PostalAddress
            Country.appendChild(doc.createTextNode("\n"));

            Element IdentificationCode = doc.createElementNS("", "cbc:IdentificationCode");
            Country.appendChild(IdentificationCode);//se anade al grupo Country
            cdata = doc.createCDATASection(factura.getEmpresa().getPais().getCodigo());
            IdentificationCode.appendChild(cdata);

            Element PartyLegalEntity = doc.createElementNS("", "cac:PartyLegalEntity");
            Party.appendChild(PartyLegalEntity);//se anade al grupo party
            PartyLegalEntity.appendChild(doc.createTextNode("\n"));

            Element RegistrationName = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity.appendChild(RegistrationName);//se anade al grupo Country
            cdata = doc.createCDATASection(factura.getEmpresa().getNombreLegal());
            RegistrationName.appendChild(cdata);
// bloque4
            Element AccountingCustomerParty = doc.createElementNS("", "cac:AccountingCustomerParty");
            envelope.appendChild(AccountingCustomerParty);
            AccountingCustomerParty.appendChild(doc.createTextNode("\n"));

            Element CustomerAssignedAccountID1 = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingCustomerParty.appendChild(CustomerAssignedAccountID1);//se anade al grupo AccountingCustomerParty
            CustomerAssignedAccountID1.appendChild(doc.createTextNode(factura.getCliente().getNumeroDocumento()));

            Element AdditionalAccountID1 = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingCustomerParty.appendChild(AdditionalAccountID1);//se anade al grupo AccountingCustomerParty
            AdditionalAccountID1.appendChild(doc.createTextNode(factura.getCliente().getTipoDocumentoIdentidad().getCodigo()));

            Element Party1 = doc.createElementNS("", "cac:Party");
            AccountingCustomerParty.appendChild(Party1);//se anade al grupo AccountingCustomerParty
            Party1.appendChild(doc.createTextNode("\n"));

            Element PartyLegalEntity1 = doc.createElementNS("", "cac:PartyLegalEntity");
            Party1.appendChild(PartyLegalEntity1);//se anade al grupo Party1
            PartyLegalEntity1.appendChild(doc.createTextNode("\n"));

            Element RegistrationName1 = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity1.appendChild(RegistrationName1);//se anade al grupo PartyLegalEntity1
            cdata = doc.createCDATASection(factura.getCliente().getNombreLegal());
            RegistrationName1.appendChild(cdata);

//bloque 5
            if (!factura.getIgv().equals(BigDecimal.valueOf(0.00))) {

                Element TaxTotal = doc.createElementNS("", "cac:TaxTotal");
                envelope.appendChild(TaxTotal);
                TaxTotal.appendChild(doc.createTextNode("\n"));

                Element TaxAmount = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount.setIdAttributeNS(null, "currencyID", true);
                TaxTotal.appendChild(TaxAmount);//se anade al grupo TaxTotal
                TaxAmount.appendChild(doc.createTextNode(factura.getIgv().toString()));

                Element TaxSubtotal = doc.createElementNS("", "cac:TaxSubtotal");
                TaxTotal.appendChild(TaxSubtotal);//se anade al grupo TaxTotal
                TaxSubtotal.appendChild(doc.createTextNode("\n"));

                Element TaxAmount1 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount1.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount1.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal.appendChild(TaxAmount1);//se anade al grupo TaxSubtotal
                TaxAmount1.appendChild(doc.createTextNode(factura.getIgv().toString()));

                Element TaxCategory = doc.createElementNS("", "cac:TaxCategory");
                TaxSubtotal.appendChild(TaxCategory);//se anade al grupo TaxSubtotal
                TaxCategory.appendChild(doc.createTextNode("\n"));

                Element TaxScheme = doc.createElementNS("", "cac:TaxScheme");
                TaxCategory.appendChild(TaxScheme);//se anade al grupo TaxCategory
                TaxScheme.appendChild(doc.createTextNode("\n"));

                Element ID9 = doc.createElementNS("", "cbc:ID");
                TaxScheme.appendChild(ID9);//se anade al grupo TaxScheme
                ID9.appendChild(doc.createTextNode("1000")); ///================================faltaba poner 1000

                Element Name3 = doc.createElementNS("", "cbc:Name");
                TaxScheme.appendChild(Name3);//se anade al grupo TaxScheme
                Name3.appendChild(doc.createTextNode("IGV"));

                Element TaxTypeCode = doc.createElementNS("", "cbc:TaxTypeCode");
                TaxScheme.appendChild(TaxTypeCode);//se anade al grupo TaxScheme
                TaxTypeCode.appendChild(doc.createTextNode("VAT"));
            }

//bloque 6     
            Element LegalMonetaryTotal = doc.createElementNS("", "cac:LegalMonetaryTotal");
            envelope.appendChild(LegalMonetaryTotal);
            LegalMonetaryTotal.appendChild(doc.createTextNode("\n"));

            if (!factura.getDescuento().equals(BigDecimal.valueOf(0.00))) {
                Element AllowanceTotalAmount = doc.createElementNS("", "cbc:AllowanceTotalAmount");
                AllowanceTotalAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                AllowanceTotalAmount.setIdAttributeNS(null, "currencyID", true);
                LegalMonetaryTotal.appendChild(AllowanceTotalAmount);//se anade al grupo LegalMonetaryTotal
                AllowanceTotalAmount.appendChild(doc.createTextNode(factura.getDescuento().toString()));
            }

            Element PayableAmount = doc.createElementNS("", "cbc:PayableAmount");
            PayableAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
            PayableAmount.setIdAttributeNS(null, "currencyID", true);
            LegalMonetaryTotal.appendChild(PayableAmount);//se anade al grupo LegalMonetaryTotal
            PayableAmount.appendChild(doc.createTextNode(factura.getTotal().toString()));

//detalle factura
            LOGGER.info("creaXML - Iniciamos detalle XML ");
            for (DetalleDocumento listaDet : factura.getDetallesDocumento()) {
                Element InvoiceLine = doc.createElementNS("", "cac:InvoiceLine");
                envelope.appendChild(InvoiceLine);
                InvoiceLine.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - Orden ");
                Element ID11 = doc.createElementNS("", "cbc:ID");
                InvoiceLine.appendChild(ID11);//se anade al grupo InvoiceLine
                ID11.appendChild(doc.createTextNode(String.valueOf(listaDet.getOrden())));

                LOGGER.info("creaXML - Unidad de Medidad ");
                Element InvoicedQuantity = doc.createElementNS("", "cbc:InvoicedQuantity");
                InvoicedQuantity.setAttributeNS(null, "unitCode", listaDet.getUnidadMedida().getCodigo());
                InvoicedQuantity.setIdAttributeNS(null, "unitCode", true);

                LOGGER.info("creaXML - Cantidad ");
                InvoiceLine.appendChild(InvoicedQuantity);//se anade al grupo InvoiceLine
                InvoicedQuantity.appendChild(doc.createTextNode(String.valueOf(listaDet.getCantidad())));

                LOGGER.info("creaXML - Moneda ");
                Element LineExtensionAmount1 = doc.createElementNS("", "cbc:LineExtensionAmount");
                LineExtensionAmount1.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                LineExtensionAmount1.setIdAttributeNS(null, "currencyID", true);

                LOGGER.info("creaXML - Item Sub total ");
                InvoiceLine.appendChild(LineExtensionAmount1);//se anade al grupo InvoiceLine
                LineExtensionAmount1.appendChild(doc.createTextNode(listaDet.getSubtotal().toString()));

                Element PricingReference = doc.createElementNS("", "cac:PricingReference");
                InvoiceLine.appendChild(PricingReference);//se anade al grupo InvoiceLine
                PricingReference.appendChild(doc.createTextNode("\n"));

                Element AlternativeConditionPrice = doc.createElementNS("", "cac:AlternativeConditionPrice");
                PricingReference.appendChild(AlternativeConditionPrice);//se anade al grupo PricingReference
                AlternativeConditionPrice.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - Item_venta ");
                Element PriceAmount = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PriceAmount.setIdAttributeNS(null, "currencyID", true);
                AlternativeConditionPrice.appendChild(PriceAmount);//se anade al grupo AlternativeConditionPrice
                PriceAmount.appendChild(doc.createTextNode(listaDet.getPrecioVenta().toString()));

                Element PriceTypeCode = doc.createElementNS("", "cbc:PriceTypeCode");
                AlternativeConditionPrice.appendChild(PriceTypeCode);//se anade al grupo AlternativeConditionPrice
                PriceTypeCode.appendChild(doc.createTextNode("01")); //=================================>Faltaba especificar ite
//                  
                LOGGER.info("creaXML - Onerosa ");
                if (!listaDet.getVentaNoOnerosa().equals(BigDecimal.valueOf(0.00))) {
                    Element AlternativeConditionPrice02 = doc.createElementNS("", "cac:AlternativeConditionPrice");
                    PricingReference.appendChild(AlternativeConditionPrice02);//se anade al grupo PricingReference
                    AlternativeConditionPrice02.appendChild(doc.createTextNode("\n"));

                    Element PriceAmount02 = doc.createElementNS("", "cbc:PriceAmount");
                    PriceAmount02.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                    PriceAmount02.setIdAttributeNS(null, "currencyID", true);
                    AlternativeConditionPrice02.appendChild(PriceAmount02);//se anade al grupo AlternativeConditionPrice
                    PriceAmount02.appendChild(doc.createTextNode(listaDet.getVentaNoOnerosa().toString()));

                    Element PriceTypeCode02 = doc.createElementNS("", "cbc:PriceTypeCode");
                    AlternativeConditionPrice02.appendChild(PriceTypeCode02);//se anade al grupo AlternativeConditionPrice
                    PriceTypeCode02.appendChild(doc.createTextNode("02")); //==>Para los casos de gatuito venta no Honerosa
                }

//
                Element TaxTotal1 = doc.createElementNS("", "cac:TaxTotal");
                InvoiceLine.appendChild(TaxTotal1);//se anade al grupo InvoiceLine
                TaxTotal1.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - IGV ");
                Element TaxAmount2 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount2.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxAmount2.setIdAttributeNS(null, "currencyID", true);
                TaxTotal1.appendChild(TaxAmount2);//se anade al grupo TaxTotal1
                TaxAmount2.appendChild(doc.createTextNode(listaDet.getIgv().toString()));

                Element TaxSubtotal1 = doc.createElementNS("", "cac:TaxSubtotal");
                TaxTotal1.appendChild(TaxSubtotal1);//se anade al grupo TaxTotal1
                TaxSubtotal1.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - sub total ");
                Element TaxableAmount = doc.createElementNS("", "cbc:TaxableAmount");
                TaxableAmount.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                TaxableAmount.setIdAttributeNS(null, "currencyID", true);

                TaxSubtotal1.appendChild(TaxableAmount);//se anade al grupo TaxSubtotal1
                //TaxableAmount.appendChild(doc.createTextNode(listaDet.getItem_ti_igv().trim()));
                TaxableAmount.appendChild(doc.createTextNode(listaDet.getSubtotal().toString()));// Oswaldo

                Element TaxAmount3 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount3.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo()); //================>errror estaba con item..getItem_moneda()
                TaxAmount3.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal1.appendChild(TaxAmount3);//se anade al grupo TaxSubtotal1
                TaxAmount3.appendChild(doc.createTextNode(listaDet.getIgv().toString()));

                LOGGER.info("creaXML - tasa Igv ");
                Element Percent = doc.createElementNS("", "cbc:Percent");
                TaxSubtotal1.appendChild(Percent);//se anade al grupo TaxSubtotal1
                //Percent.appendChild(doc.createTextNode("0.0"));
                Percent.appendChild(doc.createTextNode(String.valueOf(18)));

                Element TaxCategory1 = doc.createElementNS("", "cac:TaxCategory");
                TaxSubtotal1.appendChild(TaxCategory1);//se anade al grupo TaxSubtotal1
                TaxCategory1.appendChild(doc.createTextNode("\n"));

                Element ID12 = doc.createElementNS("", "cbc:ID");
                TaxCategory1.appendChild(ID12);//se anade al grupo TaxCategory1
                ID12.appendChild(doc.createTextNode("VAT"));

                LOGGER.info("creaXML - afectacion ");
                Element TaxExemptionReasonCode = doc.createElementNS("", "cbc:TaxExemptionReasonCode");
                TaxCategory1.appendChild(TaxExemptionReasonCode);//se anade al grupo TaxCategory1
                TaxExemptionReasonCode.appendChild(doc.createTextNode(listaDet.getTipoAfectacionIgv().getCodigo()));

                Element TierRange = doc.createElementNS("", "cbc:TierRange");
                TaxCategory1.appendChild(TierRange);//se anade al grupo TaxCategory1
                TierRange.appendChild(doc.createTextNode("00"));

                Element TaxScheme1 = doc.createElementNS("", "cac:TaxScheme");
                TaxCategory1.appendChild(TaxScheme1);//se anade al grupo TaxCategory1
                TaxScheme1.appendChild(doc.createTextNode("\n"));

                Element ID15 = doc.createElementNS("", "cbc:ID");
                TaxScheme1.appendChild(ID15);//se anade al grupo TaxCategory1
                ID15.appendChild(doc.createTextNode("1000"));

                Element Name9 = doc.createElementNS("", "cbc:Name");
                TaxScheme1.appendChild(Name9);//se anade al grupo TaxCategory1
                Name9.appendChild(doc.createTextNode("IGV"));

                Element TaxTypeCode1 = doc.createElementNS("", "cbc:TaxTypeCode");
                TaxScheme1.appendChild(TaxTypeCode1);//se anade al grupo TaxCategory1
                TaxTypeCode1.appendChild(doc.createTextNode("VAT"));

                Element Item = doc.createElementNS("", "cac:Item");
                InvoiceLine.appendChild(Item);//se anade al grupo InvoiceLine
                Item.appendChild(doc.createTextNode("\n"));

                Element Description = doc.createElementNS("", "cbc:Description");
                Item.appendChild(Description);//se anade al grupo Item
                cdata = doc.createCDATASection(listaDet.getDescripcion());
                //Description.appendChild(doc.createTextNode(listaDet.getItem_descripcion().trim()));
                Description.appendChild(cdata);

                Element SellersItemIdentification = doc.createElementNS("", "cac:SellersItemIdentification");
                Item.appendChild(SellersItemIdentification);//se anade al grupo Item
                SellersItemIdentification.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - codigo Producto ");
                Element ID18 = doc.createElementNS("", "cbc:ID");
                SellersItemIdentification.appendChild(ID18);//se anade al grupo Item
                ID18.appendChild(doc.createTextNode(listaDet.getCodigoProducto()));

                Element Price = doc.createElementNS("", "cac:Price");
                InvoiceLine.appendChild(Price);//se anade al grupo InvoiceLine
                Price.appendChild(doc.createTextNode("\n"));

                LOGGER.info("creaXML - precio venta ");
                Element PriceAmount2 = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount2.setAttributeNS(null, "currencyID", factura.getMoneda().getCodigo());
                PriceAmount2.setIdAttributeNS(null, "currencyID", true);
                Price.appendChild(PriceAmount2);//se anade al grupo Price
                PriceAmount2.appendChild(doc.createTextNode(listaDet.getPrecioVenta().toString()));
            }

            LOGGER.info("creaXML - Prepara firma digital ");
            sig.setId(factura.getEmpresa().getNumeroDocumento());
            sig.addKeyInfo(cert);
            {
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
            }
            /* 
                 * Add KeyInfo and sign() 
             */
            {
                //Firmar el documento
            	LOGGER.info("creaXML - firma el XML ");
                sig.sign(privateKey);
            }
            //--------------------fin de construccion del xml---------------------
            ///*combinacion de firma y construccion xml////
            FileOutputStream f = new FileOutputStream(signatureFile);
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            //tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.STANDALONE, "no");
            //Writer out = new StringWriter();
            StreamResult sr = new StreamResult(f);
            tf.transform(new DOMSource(doc), sr);
            sr.getOutputStream().close();

            LOGGER.info("creaXML - XML creado " + pathXMLFile);

            //====================== CREAR ZIP PARA EL ENVIO A SUNAT =======================
            resultado = FuncionesUtiles.crearZip(factura, unidadEnvio,signatureFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "0100|Error al generar el archivo de formato xml de la Factura.";
            LOGGER.error("creaXML - error  " + ex.toString());

        }
        return resultado;
    }
}
