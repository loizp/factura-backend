package com.sunqubit.faqture.sunat.test;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.rest.ServiceResponse;
import com.sunqubit.faqture.sunat.core.FacturaBoletaElectronica;

public class SunatTest {
	 public static void main(String[] args) {		 
		 String fact = "{\r\n" + 
		 		"    \"id\": 1,\r\n" + 
		 		"    \"idSysEmisor\": 132836,\r\n" + 
		 		"    \"fechaEmision\": \"2018-05-24T18:58:21\",\r\n" + 
		 		"    \"numero\": \"F002-00011\",\r\n" + 
		 		"    \"observacion\": \"\",\r\n" + 
		 		"    \"fechaProceso\": \"2018-05-24T18:58:25\",\r\n" + 
		 		"    \"estadoProceso\": \"N\",\r\n" + 
		 		"    \"tipoDocumento\": {\r\n" + 
		 		"        \"codigo\": \"01\",\r\n" + 
		 		"        \"descripcion\": \"FACTURA\",\r\n" + 
		 		"        \"tiposNotas\": [\r\n" + 
		 		"            {\r\n" + 
		 		"                \"id\": 0,\r\n" + 
		 		"                \"codigo\": null,\r\n" + 
		 		"                \"descripcion\": null,\r\n" + 
		 		"                \"tipoDocumento\": {\r\n" + 
		 		"                    \"codigo\": \"01\",\r\n" + 
		 		"                    \"descripcion\": \"FACTURA\",\r\n" + 
		 		"                    \"tiposNotas\": null\r\n" + 
		 		"                }\r\n" + 
		 		"            },\r\n" + 
		 		"            {\r\n" + 
		 		"                \"id\": 0,\r\n" + 
		 		"                \"codigo\": null,\r\n" + 
		 		"                \"descripcion\": null,\r\n" + 
		 		"                \"tipoDocumento\": {\r\n" + 
		 		"                    \"codigo\": \"01\",\r\n" + 
		 		"                    \"descripcion\": \"FACTURA\",\r\n" + 
		 		"                    \"tiposNotas\": null\r\n" + 
		 		"                }\r\n" + 
		 		"            }\r\n" + 
		 		"        ]\r\n" + 
		 		"    },\r\n" + 
		 		"    \"empresa\": {\r\n" + 
		 		"        \"id\": 1,\r\n" + 
		 		"        \"numeroDocumento\": \"20603088981\",\r\n" + 
		 		"        \"nombreLegal\": \"QUANTUM INC S.A.C.\",\r\n" + 
		 		"        \"nombreComercial\": \"-\",\r\n" + 
		 		"        \"direccion\": \"PRO.CALLAO NRO. 931 (FRENTE A COMERCIAL DAVID) SAN MARTIN - MOYOBAMBA - MOYOBAMBA\",\r\n" + 
		 		"        \"urbanizacion\": null,\r\n" + 
		 		"        \"tipoDocumentoIdentidad\": {\r\n" + 
		 		"            \"codigo\": \"6\",\r\n" + 
		 		"            \"descripcion\": \"REG. UNICO DE CONTRIBUYENTES\",\r\n" + 
		 		"            \"descripcionCorta\": \"RUC\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"ubigeo\": {\r\n" + 
		 		"            \"id\": 1923,\r\n" + 
		 		"            \"codigo\": \"220101\",\r\n" + 
		 		"            \"departamento\": \"DPTO SAN MARTIN\",\r\n" + 
		 		"            \"provincia\": \"PROV MOYOBAMBA\",\r\n" + 
		 		"            \"distrito\": \"DIST MOYOBAMBA\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"pais\": {\r\n" + 
		 		"            \"codigo\": \"PE\",\r\n" + 
		 		"            \"nombre\": \"PERU\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"sucursales\": null,\r\n" + 
		 		"        \"activo\": true,\r\n" + 
		 		"        \"keystoreType\": \"JKS\",\r\n" + 
		 		"        \"keystoreFile\": \"MiAlmacen.jks\",\r\n" + 
		 		"        \"keystorePass\": \"MzBlZTEyODEwZjAwMTVhNzQwNzA0MDBiYzkxNTIwNzBlMTBlYzYxZmI5NDY3MmUzNTBhMjlhZWJiYzQ4Y2ZlYWI4Nzk5NDMzMmRiNzMwYjU=\",\r\n" + 
		 		"        \"privateKeyAlias\": \"ODIxYzBkYTI0NzZhZTg3NjA0YzQ0YmIwY2NhNDc1MDQ5OWY4YTRiZjBhN2Y2ZmJkOTExOWU2MTc2OGU0NDlmOTY0NTRjMjczZWRlNjY0Zjk=\",\r\n" + 
		 		"        \"privateKeyPass\": \"MDE3YmRiOTM4OWJhOTZhNWIzNDNhZTFlODQ5NGNlOWMyYzA2MjE1OTUxMzMwYzBjOTUyMGUwMDU5YzQ1N2QyZDU3MDUzYWNiNzRiOTExNTY=\",\r\n" + 
		 		"        \"certificateAlias\": \"YmUxYWM2YjhmY2ZlODViMzI3YjA0NTg1OWNiZjUxY2Y0ZGFmODU3OTc2MDNiY2U5ZWRlNmJmM2FjNzJmMTU1YzBkM2E4NTlkMjE1NWViYzM=\"\r\n" + 
		 		"    },\r\n" + 
		 		"    \"emprSucursal\": null,\r\n" + 
		 		"    \"moneda\": {\r\n" + 
		 		"        \"codigo\": \"PEN\",\r\n" + 
		 		"        \"descripcion\": \"Nuevo Sol\"\r\n" + 
		 		"    },\r\n" + 
		 		"    \"enviarSunat\": true,\r\n" + 
		 		"    \"tasaIgv\": 18.0,\r\n" + 
		 		"    \"linkPdf\": null,\r\n" + 
		 		"    \"linkXml\": null,\r\n" + 
		 		"    \"hashSunat\": null,\r\n" + 
		 		"    \"linkCdr\": null,\r\n" + 
		 		"    \"cdrStatus\": null,\r\n" + 
		 		"    \"cdrNota\": null,\r\n" + 
		 		"    \"cdrObservacion\": null,\r\n" + 
		 		"    \"leyendas\": [\r\n" + 
		 		"        {\r\n" + 
		 		"            \"documento\": null,\r\n" + 
		 		"            \"tipoLeyenda\": {\r\n" + 
		 		"                \"codigo\": \"1000\",\r\n" + 
		 		"                \"descripcion\": \"Monto en Letras\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"descripcion\": \"Cuatrocientos siete con 40/100 Soles\"\r\n" + 
		 		"        }\r\n" + 
		 		"    ],\r\n" + 
		 		"    \"docsReferenciados\": [],\r\n" + 
		 		"    \"cliente\": {\r\n" + 
		 		"        \"id\": 2,\r\n" + 
		 		"        \"numeroDocumento\": \"20440333304\",\r\n" + 
		 		"        \"nombreLegal\": \"CLINICA VISION S.A.C.\",\r\n" + 
		 		"        \"nombreComercial\": null,\r\n" + 
		 		"        \"direccion\": \"JR. FRANCISCO BOLOGNESI NRO. 564 CENTRO-CERCADO LA LIBERTAD - TRUJILLO - TRUJILLO\",\r\n" + 
		 		"        \"urbanizacion\": null,\r\n" + 
		 		"        \"tipoDocumentoIdentidad\": {\r\n" + 
		 		"            \"codigo\": \"6\",\r\n" + 
		 		"            \"descripcion\": \"REG. UNICO DE CONTRIBUYENTES\",\r\n" + 
		 		"            \"descripcionCorta\": \"RUC\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"ubigeo\": {\r\n" + 
		 		"            \"id\": 1348,\r\n" + 
		 		"            \"codigo\": \"130101\",\r\n" + 
		 		"            \"departamento\": \"DPTO LA LIBERTAD\",\r\n" + 
		 		"            \"provincia\": \"PROV TRUJILLO\",\r\n" + 
		 		"            \"distrito\": \"DIST TRUJILLO\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"pais\": {\r\n" + 
		 		"            \"codigo\": \"PE\",\r\n" + 
		 		"            \"nombre\": \"PERU\"\r\n" + 
		 		"        },\r\n" + 
		 		"        \"sucursales\": null\r\n" + 
		 		"    },\r\n" + 
		 		"    \"clieSucursal\": null,\r\n" + 
		 		"    \"tipoOperacion\": {\r\n" + 
		 		"        \"codigo\": \"01\",\r\n" + 
		 		"        \"descripcion\": \"Venta lnterna\"\r\n" + 
		 		"    },\r\n" + 
		 		"    \"fechaVencimiento\": null,\r\n" + 
		 		"    \"subtotal\": 345.25,\r\n" + 
		 		"    \"grabada\": 0.0,\r\n" + 
		 		"    \"inafecta\": 0.0,\r\n" + 
		 		"    \"exonerada\": 0.0,\r\n" + 
		 		"    \"gratuita\": 0.0,\r\n" + 
		 		"    \"descuento\": 0.0,\r\n" + 
		 		"    \"igv\": 62.15,\r\n" + 
		 		"    \"isc\": 0.0,\r\n" + 
		 		"    \"otrosTributos\": 0.0,\r\n" + 
		 		"    \"otrosCargos\": 0.0,\r\n" + 
		 		"    \"total\": 407.4,\r\n" + 
		 		"    \"vendedor\": \"E0001\",\r\n" + 
		 		"    \"emailCliente\": null,\r\n" + 
		 		"    \"anulado\": false,\r\n" + 
		 		"    \"detallesDocumento\": [\r\n" + 
		 		"        {\r\n" + 
		 		"            \"id\": 2,\r\n" + 
		 		"            \"comprobantePago\": {\r\n" + 
		 		"                \"id\": 1,\r\n" + 
		 		"                \"idSysEmisor\": 0,\r\n" + 
		 		"                \"fechaEmision\": null,\r\n" + 
		 		"                \"numero\": null,\r\n" + 
		 		"                \"observacion\": null,\r\n" + 
		 		"                \"fechaProceso\": null,\r\n" + 
		 		"                \"estadoProceso\": \"N\",\r\n" + 
		 		"                \"tipoDocumento\": null,\r\n" + 
		 		"                \"empresa\": null,\r\n" + 
		 		"                \"emprSucursal\": null,\r\n" + 
		 		"                \"moneda\": null,\r\n" + 
		 		"                \"enviarSunat\": true,\r\n" + 
		 		"                \"tasaIgv\": 18.0,\r\n" + 
		 		"                \"linkPdf\": null,\r\n" + 
		 		"                \"linkXml\": null,\r\n" + 
		 		"                \"hashSunat\": null,\r\n" + 
		 		"                \"linkCdr\": null,\r\n" + 
		 		"                \"cdrStatus\": null,\r\n" + 
		 		"                \"cdrNota\": null,\r\n" + 
		 		"                \"cdrObservacion\": null,\r\n" + 
		 		"                \"leyendas\": null,\r\n" + 
		 		"                \"docsReferenciados\": null,\r\n" + 
		 		"                \"cliente\": null,\r\n" + 
		 		"                \"clieSucursal\": null,\r\n" + 
		 		"                \"tipoOperacion\": null,\r\n" + 
		 		"                \"fechaVencimiento\": null,\r\n" + 
		 		"                \"subtotal\": 0.0,\r\n" + 
		 		"                \"grabada\": 0.0,\r\n" + 
		 		"                \"inafecta\": 0.0,\r\n" + 
		 		"                \"exonerada\": 0.0,\r\n" + 
		 		"                \"gratuita\": 0.0,\r\n" + 
		 		"                \"descuento\": 0.0,\r\n" + 
		 		"                \"igv\": 0.0,\r\n" + 
		 		"                \"isc\": 0.0,\r\n" + 
		 		"                \"otrosTributos\": 0.0,\r\n" + 
		 		"                \"otrosCargos\": 0.0,\r\n" + 
		 		"                \"total\": 0.0,\r\n" + 
		 		"                \"vendedor\": null,\r\n" + 
		 		"                \"emailCliente\": null,\r\n" + 
		 		"                \"anulado\": false,\r\n" + 
		 		"                \"detallesDocumento\": null\r\n" + 
		 		"            },\r\n" + 
		 		"            \"orden\": 2,\r\n" + 
		 		"            \"codigoProducto\": \"PR000018170\",\r\n" + 
		 		"            \"descripcion\": \"BLOCK SADIPAL PAPEL SURTIDOS X 42HJS\",\r\n" + 
		 		"            \"unidadMedida\": {\r\n" + 
		 		"                \"codigo\": \"NIU\",\r\n" + 
		 		"                \"descripcion\": \"number of international units\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"moneda\": {\r\n" + 
		 		"                \"codigo\": \"PEN\",\r\n" + 
		 		"                \"descripcion\": \"Nuevo Sol\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"cantidad\": 3.0,\r\n" + 
		 		"            \"precioVenta\": 26.8,\r\n" + 
		 		"            \"descuento\": 0.0,\r\n" + 
		 		"            \"ventaNoOnerosa\": 0.0,\r\n" + 
		 		"            \"igv\": 0.0,\r\n" + 
		 		"            \"isc\": 0.0,\r\n" + 
		 		"            \"subtotal\": 80.4,\r\n" + 
		 		"            \"tipoAfectacionIgv\": {\r\n" + 
		 		"                \"codigo\": \"10\",\r\n" + 
		 		"                \"descripcion\": \"Gravado - Operación Onerosa\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"tipoIsc\": null\r\n" + 
		 		"        },\r\n" + 
		 		"        {\r\n" + 
		 		"            \"id\": 1,\r\n" + 
		 		"            \"comprobantePago\": {\r\n" + 
		 		"                \"id\": 1,\r\n" + 
		 		"                \"idSysEmisor\": 0,\r\n" + 
		 		"                \"fechaEmision\": null,\r\n" + 
		 		"                \"numero\": null,\r\n" + 
		 		"                \"observacion\": null,\r\n" + 
		 		"                \"fechaProceso\": null,\r\n" + 
		 		"                \"estadoProceso\": \"N\",\r\n" + 
		 		"                \"tipoDocumento\": null,\r\n" + 
		 		"                \"empresa\": null,\r\n" + 
		 		"                \"emprSucursal\": null,\r\n" + 
		 		"                \"moneda\": null,\r\n" + 
		 		"                \"enviarSunat\": true,\r\n" + 
		 		"                \"tasaIgv\": 18.0,\r\n" + 
		 		"                \"linkPdf\": null,\r\n" + 
		 		"                \"linkXml\": null,\r\n" + 
		 		"                \"hashSunat\": null,\r\n" + 
		 		"                \"linkCdr\": null,\r\n" + 
		 		"                \"cdrStatus\": null,\r\n" + 
		 		"                \"cdrNota\": null,\r\n" + 
		 		"                \"cdrObservacion\": null,\r\n" + 
		 		"                \"leyendas\": null,\r\n" + 
		 		"                \"docsReferenciados\": null,\r\n" + 
		 		"                \"cliente\": null,\r\n" + 
		 		"                \"clieSucursal\": null,\r\n" + 
		 		"                \"tipoOperacion\": null,\r\n" + 
		 		"                \"fechaVencimiento\": null,\r\n" + 
		 		"                \"subtotal\": 0.0,\r\n" + 
		 		"                \"grabada\": 0.0,\r\n" + 
		 		"                \"inafecta\": 0.0,\r\n" + 
		 		"                \"exonerada\": 0.0,\r\n" + 
		 		"                \"gratuita\": 0.0,\r\n" + 
		 		"                \"descuento\": 0.0,\r\n" + 
		 		"                \"igv\": 0.0,\r\n" + 
		 		"                \"isc\": 0.0,\r\n" + 
		 		"                \"otrosTributos\": 0.0,\r\n" + 
		 		"                \"otrosCargos\": 0.0,\r\n" + 
		 		"                \"total\": 0.0,\r\n" + 
		 		"                \"vendedor\": null,\r\n" + 
		 		"                \"emailCliente\": null,\r\n" + 
		 		"                \"anulado\": false,\r\n" + 
		 		"                \"detallesDocumento\": null\r\n" + 
		 		"            },\r\n" + 
		 		"            \"orden\": 1,\r\n" + 
		 		"            \"codigoProducto\": \"PR000017082\",\r\n" + 
		 		"            \"descripcion\": \"CATALOGO ALPHA   A4 20 MICAS PRESENTACION\",\r\n" + 
		 		"            \"unidadMedida\": {\r\n" + 
		 		"                \"codigo\": \"NIU\",\r\n" + 
		 		"                \"descripcion\": \"number of international units\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"moneda\": {\r\n" + 
		 		"                \"codigo\": \"PEN\",\r\n" + 
		 		"                \"descripcion\": \"Nuevo Sol\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"cantidad\": 30.0,\r\n" + 
		 		"            \"precioVenta\": 10.9,\r\n" + 
		 		"            \"descuento\": 0.0,\r\n" + 
		 		"            \"ventaNoOnerosa\": 0.0,\r\n" + 
		 		"            \"igv\": 0.0,\r\n" + 
		 		"            \"isc\": 0.0,\r\n" + 
		 		"            \"subtotal\": 327.0,\r\n" + 
		 		"            \"tipoAfectacionIgv\": {\r\n" + 
		 		"                \"codigo\": \"10\",\r\n" + 
		 		"                \"descripcion\": \"Gravado - Operación Onerosa\"\r\n" + 
		 		"            },\r\n" + 
		 		"            \"tipoIsc\": null\r\n" + 
		 		"        }\r\n" + 
		 		"    ]\r\n" + 
		 		"}";
		 
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			ComprobantePago cp = mapper.readValue(fact, ComprobantePago.class);
			ServiceResponse sr = FacturaBoletaElectronica.generarQRPdf417(cp);
			ComprobantePago cpres = (ComprobantePago) sr.getData();
			System.out.println("hashcode : " + cpres.getHashSunat());
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
