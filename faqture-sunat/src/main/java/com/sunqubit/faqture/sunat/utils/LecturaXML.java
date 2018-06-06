package com.sunqubit.faqture.sunat.utils;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sunqubit.faqture.beans.utils.StoreManager;

public class LecturaXML {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LecturaXML.class);

	public static String getDigestValue(String path) {
        String firma = "";
        try {
            DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
            Document documento = null;
            if(StoreManager.store == 1) documento = creadorDocumento.parse(path);
            if(StoreManager.store == 2) documento = creadorDocumento.parse(new InputSource(StoreManager.getFileStore(path)));
            Element raiz = documento.getDocumentElement();
            
            LOGGER.info("LecturaXML - obtenerDigestValue | Iniciando con la lectura");
            NodeList listaNodos = raiz.getElementsByTagName("ds:Reference");
            for (int i = 0; i < listaNodos.getLength(); i++) {
            	Node nodo = listaNodos.item(i);
                NodeList datosNodo = nodo.getChildNodes();
                for (int j = 0; j < datosNodo.getLength(); j++) {
                    Node dato = datosNodo.item(j);
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        if (dato.getNodeName() == "ds:DigestValue") {
                            Node datoContenido = dato.getFirstChild();
                            if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                if (datoContenido.getNodeValue() != null) {
                                    firma = datoContenido.getNodeValue();
                                }
                            }
                        }
                    }
                }
            }
        } catch (org.xml.sax.SAXException ex) {
        	LOGGER.info("LecturaXML - obtenerDigestValue | formato XML incorrecto : " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
        	LOGGER.info("LecturaXML - obtenerDigestValue | error al leer el fichero : " + ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
        	LOGGER.info("LecturaXML - obtenerDigestValue | No se ha podido crear el generador de documentos XML : " + ex.getMessage());
            ex.printStackTrace();
        }catch (Exception exg) {
        	LOGGER.info("LecturaXML - getInfoToBarCode | error al leer el archivo : " + exg.getMessage());
            exg.printStackTrace();
        }
        LOGGER.info("LecturaXML - obtenerDigestValue | firma obtenida");
        return firma;
    }
	
	public static String getSignature(String path) {
        String respuesta = "";
        try {
            DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
            Document documento = null;
            if(StoreManager.store == 1) documento = creadorDocumento.parse(path);
            if(StoreManager.store == 2) documento = creadorDocumento.parse(new InputSource(StoreManager.getFileStore(path)));
            Element raiz = documento.getDocumentElement();

            NodeList nodlist = raiz.getElementsByTagName("ds:SignatureValue");
            respuesta += nodlist.item(0).getTextContent();
        } catch (org.xml.sax.SAXException ex) {
        	LOGGER.info("LecturaXML - getSignature | formato XML incorrecto : " + ex.getMessage());
            ex.printStackTrace();
            respuesta = "";
        } catch (IOException ex) {
        	LOGGER.info("LecturaXML - getSignature | error al leer el fichero : " + ex.getMessage());
        	respuesta = "";
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
        	LOGGER.info("LecturaXML - getSignature | No se ha podido crear el generador de documentos XML : " + ex.getMessage());
        	respuesta = "";
        	ex.printStackTrace();
        } catch (Exception exg) {
        	LOGGER.info("LecturaXML - getSignature | error al leer el archivo : " + exg.getMessage());
        	respuesta = "";
            exg.printStackTrace();
        }
        LOGGER.info("LecturaXML - getSignature | data obtenida");
        return respuesta;
    }
}
