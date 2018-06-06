package com.sunqubit.faqture.sunat.test;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.rest.ServiceResponse;
import com.sunqubit.faqture.sunat.core.FacturaBoletaElectronica;

import pe.gob.sunat.service.NoExisteWSSunatException;

public class SunatTest {
	 public static void main(String[] args) {		 
		 String fact = "{\r\n" + 
		 		"        \"id\": 1,\r\n" + 
		 		"        \"idSysEmisor\": 132820,\r\n" + 
		 		"        \"fechaEmision\": \"2018-05-24T03:20:48\",\r\n" + 
		 		"        \"numero\": \"F002-00001\",\r\n" + 
		 		"        \"observacion\": \"\",\r\n" + 
		 		"        \"fechaProceso\": \"2018-06-06T01:36:41\",\r\n" + 
		 		"        \"estadoProceso\": \"N\",\r\n" + 
		 		"        \"tipoDocumento\": {\r\n" + 
		 		"            \"codigo\": \"01\",\r\n" + 
		 		"            \"descripcion\": \"FACTURA\",\r\n" + 
		 		"            \"tiposNotas\": [\r\n" + 
		 		"                {\r\n" + 
		 		"                    \"id\": 0,\r\n" + 
		 		"                    \"codigo\": null,\r\n" + 
		 		"                    \"descripcion\": null,\r\n" + 
		 		"                    \"tipoDocumento\": {\r\n" + 
		 		"                        \"codigo\": \"01\",\r\n" + 
		 		"                        \"descripcion\": \"FACTURA\",\r\n" + 
		 		"                        \"tiposNotas\": null\r\n" + 
		 		"                    }\r\n" + 
		 		"                }\r\n" + 
		 		"            ]\r\n" + 
		 		"        },\r\n" + 
		 		"        \"empresa\": {\r\n" + 
		 		"            \"id\": 1,\r\n" + 
		 		"            \"numeroDocumento\": \"20603088981\",\r\n" + 
		 		"            \"nombreLegal\": \"QUANTUM INC S.A.C.\",\r\n" + 
		 		"            \"nombreComercial\": \"-\",\r\n" + 
		 		"            \"direccion\": \"PRO.CALLAO NRO. 931 (FRENTE A COMERCIAL DAVID) SAN MARTIN - MOYOBAMBA - MOYOBAMBA\",\r\n" + 
		 		"            \"urbanizacion\": null,\r\n" + 
		 		"            \"email\": null,\r\n" + 
		 		"            \"telefono\": null,\r\n" + 
		 		"            \"tipoDocumentoIdentidad\": {\r\n" + 
		 		"                \"codigo\": \"6\",\r\n" + 
		 		"                \"descripcion\": \"REG. UNICO DE CONTRIBUYENTES\",\r\n" + 
		 		"                \"descripcionCorta\": \"RUC\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"ubigeo\": {\r\n" + 
		 		"                \"id\": 1923,\r\n" + 
		 		"                \"codigo\": \"220101\",\r\n" + 
		 		"                \"departamento\": \"DPTO SAN MARTIN\",\r\n" + 
		 		"                \"provincia\": \"PROV MOYOBAMBA\",\r\n" + 
		 		"                \"distrito\": \"DIST MOYOBAMBA\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"pais\": {\r\n" + 
		 		"                \"codigo\": \"PE\",\r\n" + 
		 		"                \"nombre\": \"PERU\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"sucursales\": null,\r\n" + 
		 		"            \"activo\": true,\r\n" + 
		 		"            \"tipoEnvio\": \"1\",\r\n" + 
		 		"            \"userSunat\": \"YjM4ZGNhZTBjOWU5MDZjZmNiMTJlNjY5YTY3OTY5NDY1OTg4OGUwODA0YjQ1MzU3ODQyZjA1NTY4ZGE4YWZiMDA3N2U1MzBkNjZjMDc5ZTA=\",\r\n" + 
		 		"            \"passSunat\": \"MTA5OTgzZTY0ODY0OTJmMmQ0YTZmMzk3YzYxODM1NjZkYmRkY2Y1N2VlNDVlYjA0YTk0ZTAwNzhjYmVmZmViNmQ0ZTMzY2ExN2NmYjgxZjU=\",\r\n" + 
		 		"            \"keystoreType\": \"JKS\",\r\n" + 
		 		"            \"keystoreFile\": \"MiAlmacen.jks\",\r\n" + 
		 		"            \"keystorePass\": \"NDljMTBiNjM3NjljNzI1NzRjNjZkMTAwZWE4Nzk1YjQxZmZjNzE2OGFmOGZjZDUwODNhNTEyNDQyNTUzNWYzOTQxMDM3NTAyNGQwMzdhYTA=\",\r\n" + 
		 		"            \"privateKeyAlias\": \"ZWU2NTM0NWZlZTljZTU2MDRjYmQxMDQ0MDUzMjIxYTcwZDc2NmQ3NDBhNWJhNDNhYTAyZGM2MzU1ZWY5ZjM4Yjg3Y2RiZGRlMjI4YWJmMTY=\",\r\n" + 
		 		"            \"privateKeyPass\": \"NmU2NzI1MjAzMjRjNTJhZjYzNGFkYmM0NTlkMzYyYTc4YjBiOWE5YzM1NzZhNDVhOTI1MDk4OWUxMmFkMzFhZDUxZGI5MjE4NzVlYWU3NzE=\",\r\n" + 
		 		"            \"certificateAlias\": \"ODFkZDMxODA1ZGZiMTAzMzU0MzYxMDUxMWVjZDNjNDgxOTBiMDE3MDEwNGUwNDliYTUzZjUzOGVkYTkyZDI0ZGJjNTM3YWM3ZDM4MWJmMGU=\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"emprSucursal\": null,\r\n" + 
		 		"        \"moneda\": {\r\n" + 
		 		"            \"codigo\": \"PEN\",\r\n" + 
		 		"            \"descripcion\": \"Nuevo Sol\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"enviarSunat\": true,\r\n" + 
		 		"        \"tasaIgv\": 18,\r\n" + 
		 		"        \"linkPdf\": null,\r\n" + 
		 		"        \"linkXml\": \"https://faqture-store.storage.googleapis.com/D:\\\\faqtureStore\\\\faqture\\\\privado\\\\6-20603088981\\\\sunat\\\\envio\\\\2018\\\\MAYO\\\\20603088981-01-F002-00001.xml\",\r\n" + 
		 		"        \"hashSunat\": \"sZfXvoZYw/pIafOYETUcG6b6JCg=\",\r\n" + 
		 		"        \"linkQR\": \"https://faqture-store.storage.googleapis.com/D:\\\\faqtureStore\\\\faqture\\\\publico\\\\6-20603088981\\\\sunat\\\\2018\\\\MAYO\\\\20603088981-01-F002-00001-qr.png\",\r\n" + 
		 		"        \"linkPDF417\": \"https://faqture-store.storage.googleapis.com/D:\\\\faqtureStore\\\\faqture\\\\publico\\\\6-20603088981\\\\sunat\\\\2018\\\\MAYO\\\\20603088981-01-F002-00001-pdf417.png\",\r\n" + 
		 		"        \"linkCdr\": null,\r\n" + 
		 		"        \"cdrStatus\": null,\r\n" + 
		 		"        \"cdrNota\": null,\r\n" + 
		 		"        \"cdrObservacion\": null,\r\n" + 
		 		"        \"leyendas\": [\r\n" + 
		 		"            {\r\n" + 
		 		"                \"documento\": null,\r\n" + 
		 		"                \"tipoLeyenda\": {\r\n" + 
		 		"                    \"codigo\": \"1000\",\r\n" + 
		 		"                    \"descripcion\": \"Monto en Letras\"\r\n" + 
		 		"                },\r\n" + 
		 		"                \"descripcion\": \"Un mil trescientos cuarenta con 00/100 Soles\"\r\n" + 
		 		"            }\r\n" + 
		 		"        ],\r\n" + 
		 		"        \"docsReferenciados\": [],\r\n" + 
		 		"        \"cliente\": {\r\n" + 
		 		"            \"id\": 2,\r\n" + 
		 		"            \"numeroDocumento\": \"20477152962\",\r\n" + 
		 		"            \"nombreLegal\": \"GRUPO EMPRESARIAL ALFER S.A.C\",\r\n" + 
		 		"            \"nombreComercial\": null,\r\n" + 
		 		"            \"direccion\": \"JR AYACUCHO 538 - TRUJILLO\",\r\n" + 
		 		"            \"urbanizacion\": null,\r\n" + 
		 		"            \"email\": null,\r\n" + 
		 		"            \"telefono\": null,\r\n" + 
		 		"            \"tipoDocumentoIdentidad\": {\r\n" + 
		 		"                \"codigo\": \"6\",\r\n" + 
		 		"                \"descripcion\": \"REG. UNICO DE CONTRIBUYENTES\",\r\n" + 
		 		"                \"descripcionCorta\": \"RUC\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"ubigeo\": {\r\n" + 
		 		"                \"id\": 1348,\r\n" + 
		 		"                \"codigo\": \"130101\",\r\n" + 
		 		"                \"departamento\": \"DPTO LA LIBERTAD\",\r\n" + 
		 		"                \"provincia\": \"PROV TRUJILLO\",\r\n" + 
		 		"                \"distrito\": \"DIST TRUJILLO\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"pais\": {\r\n" + 
		 		"                \"codigo\": \"PE\",\r\n" + 
		 		"                \"nombre\": \"PERU\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"sucursales\": null\r\n" + 
		 		"        },\r\n" + 
		 		"        \"clieSucursal\": null,\r\n" + 
		 		"        \"tipoOperacion\": {\r\n" + 
		 		"            \"codigo\": \"01\",\r\n" + 
		 		"            \"descripcion\": \"Venta lnterna\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"fechaVencimiento\": null,\r\n" + 
		 		"        \"subtotal\": 1135.59,\r\n" + 
		 		"        \"grabada\": 0,\r\n" + 
		 		"        \"inafecta\": 0,\r\n" + 
		 		"        \"exonerada\": 0,\r\n" + 
		 		"        \"gratuita\": 0,\r\n" + 
		 		"        \"descuento\": 0,\r\n" + 
		 		"        \"igv\": 204.41,\r\n" + 
		 		"        \"isc\": 0,\r\n" + 
		 		"        \"otrosTributos\": 0,\r\n" + 
		 		"        \"otrosCargos\": 0,\r\n" + 
		 		"        \"total\": 1340,\r\n" + 
		 		"        \"vendedor\": \"E0001\",\r\n" + 
		 		"        \"emailCliente\": null,\r\n" + 
		 		"        \"anulado\": false,\r\n" + 
		 		"        \"detallesDocumento\": [\r\n" + 
		 		"            {\r\n" + 
		 		"                \"id\": 1,\r\n" + 
		 		"                \"comprobantePago\": {\r\n" + 
		 		"                    \"id\": 1,\r\n" + 
		 		"                    \"idSysEmisor\": 0,\r\n" + 
		 		"                    \"fechaEmision\": null,\r\n" + 
		 		"                    \"numero\": null,\r\n" + 
		 		"                    \"observacion\": null,\r\n" + 
		 		"                    \"fechaProceso\": null,\r\n" + 
		 		"                    \"estadoProceso\": \"N\",\r\n" + 
		 		"                    \"tipoDocumento\": null,\r\n" + 
		 		"                    \"empresa\": null,\r\n" + 
		 		"                    \"emprSucursal\": null,\r\n" + 
		 		"                    \"moneda\": null,\r\n" + 
		 		"                    \"enviarSunat\": true,\r\n" + 
		 		"                    \"tasaIgv\": 18,\r\n" + 
		 		"                    \"linkPdf\": null,\r\n" + 
		 		"                    \"linkXml\": null,\r\n" + 
		 		"                    \"hashSunat\": null,\r\n" + 
		 		"                    \"linkQR\": null,\r\n" + 
		 		"                    \"linkPDF417\": null,\r\n" + 
		 		"                    \"linkCdr\": null,\r\n" + 
		 		"                    \"cdrStatus\": null,\r\n" + 
		 		"                    \"cdrNota\": null,\r\n" + 
		 		"                    \"cdrObservacion\": null,\r\n" + 
		 		"                    \"leyendas\": null,\r\n" + 
		 		"                    \"docsReferenciados\": null,\r\n" + 
		 		"                    \"cliente\": null,\r\n" + 
		 		"                    \"clieSucursal\": null,\r\n" + 
		 		"                    \"tipoOperacion\": null,\r\n" + 
		 		"                    \"fechaVencimiento\": null,\r\n" + 
		 		"                    \"subtotal\": 0,\r\n" + 
		 		"                    \"grabada\": 0,\r\n" + 
		 		"                    \"inafecta\": 0,\r\n" + 
		 		"                    \"exonerada\": 0,\r\n" + 
		 		"                    \"gratuita\": 0,\r\n" + 
		 		"                    \"descuento\": 0,\r\n" + 
		 		"                    \"igv\": 0,\r\n" + 
		 		"                    \"isc\": 0,\r\n" + 
		 		"                    \"otrosTributos\": 0,\r\n" + 
		 		"                    \"otrosCargos\": 0,\r\n" + 
		 		"                    \"total\": 0,\r\n" + 
		 		"                    \"vendedor\": null,\r\n" + 
		 		"                    \"emailCliente\": null,\r\n" + 
		 		"                    \"anulado\": false,\r\n" + 
		 		"                    \"detallesDocumento\": null\r\n" + 
		 		"                },\r\n" + 
		 		"                \"orden\": 1,\r\n" + 
		 		"                \"codigoProducto\": \"PR000018170\",\r\n" + 
		 		"                \"descripcion\": \"BLOCK SADIPAL PAPEL SURTIDOS X 42HJS\",\r\n" + 
		 		"                \"unidadMedida\": {\r\n" + 
		 		"                    \"codigo\": \"NIU\",\r\n" + 
		 		"                    \"descripcion\": \"number of international units\"\r\n" + 
		 		"                },\r\n" + 
		 		"                \"moneda\": {\r\n" + 
		 		"                    \"codigo\": \"PEN\",\r\n" + 
		 		"                    \"descripcion\": \"Nuevo Sol\"\r\n" + 
		 		"                },\r\n" + 
		 		"                \"cantidad\": 50,\r\n" + 
		 		"                \"precioVenta\": 26.8,\r\n" + 
		 		"                \"descuento\": 0,\r\n" + 
		 		"                \"ventaNoOnerosa\": 0,\r\n" + 
		 		"                \"igv\": 0,\r\n" + 
		 		"                \"isc\": 0,\r\n" + 
		 		"                \"subtotal\": 1340,\r\n" + 
		 		"                \"tipoAfectacionIgv\": {\r\n" + 
		 		"                    \"codigo\": \"20\",\r\n" + 
		 		"                    \"descripcion\": \"Exonerado - Operación Onerosa\"\r\n" + 
		 		"                },\r\n" + 
		 		"                \"tipoIsc\": null\r\n" + 
		 		"            }\r\n" + 
		 		"        ]\r\n" + 
		 		"    }\r\n" + 
		 		"}";
		 
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			ComprobantePago cp = mapper.readValue(fact, ComprobantePago.class);
			//ServiceResponse sr = FacturaBoletaElectronica.generarQRPdf417(cp);
			//ComprobantePago cpres = (ComprobantePago) sr.getData();
			//System.out.println("hashcode : " + cpres.getHashSunat());
			try {
				FacturaBoletaElectronica.enviarASunat(cp);
			} catch (NoExisteWSSunatException e) {
				e.printStackTrace();
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
