package com.sunqubit.faqture.sunat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunqubit.faqture.core.beans.ComprobantePago;

public class FuncionesUtiles {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionesUtiles.class);
	
	/*
	static String eq[][] = { //equivalencia
	        {"1", "01", "Factura"},
	        {"2", "03", "Boleta"},
	        {"RS", "", "Resumen"},
	        {"AN", "", "Anulacion"},
	        {"6", "07", "Nota de Credito"},
	        {"7", "08", "Nota de Debito"},
	        {"OE", "08", "Obtener Estado"},
	        {"20", "20", "Retenciones"}
	        };*/
	static String[] rutaAMes = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	
    
	//@Value("${sunat.urlstorebase}")
	static String rutabase = "D:\\faqtureStore\\";
	
	//@Value("${sunat.urlstoredocs}")
	static String rutaenvios = "sunat\\sent\\";
	
	//@Value("${sunat.urlstorecdr}")
	static String rutarecepcion = "sunat\\received\\";
	
	//@Value("${sunat.urlstorepublic}")
	static String rutapublica = "sunat\\public\\";
	
	//@Value("${sunat.urlstoreconfig}")
	static String rutaconfig = "config\\";

	public static String getDirectorio(int tipo, String rutadocs, Timestamp fechaTS) {
		if(!rutadocs.endsWith("\\"))
			rutadocs += "\\";
		
		switch (tipo) {
			case 1: rutadocs += rutaenvios;	break;
			case 2: rutadocs += rutarecepcion; break;
			case 3: rutadocs += rutapublica; break;
			case 4: rutadocs += rutaconfig; break;
		}
		if(tipo == 4)
			return rutabase + rutadocs;
		
		LocalDateTime fecha = fechaTS.toLocalDateTime();
		String enviAnio = String.valueOf(fecha.getYear());
		String envioMes = rutaAMes[Integer.valueOf(fecha.getMonthValue() - 1)];
		
		File folder = new File(rutabase + rutadocs + enviAnio + "\\" + envioMes + "\\");
		if (!folder.exists())
			folder.mkdirs();
		
		return rutabase + rutadocs + enviAnio + "\\" + envioMes + "\\";
	}
	
	public static String getPropertyValue(String paramName, String vruc) {
        String msg = "";
        
        
        try {
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream(getDirectorio(4, vruc, null) + "property.properties"));
            msg = "" + propiedades.getProperty(paramName);
        } catch (Exception ex) {
            msg = "Error en getPathFiles: " + getDirectorio(4, vruc, null) + "property.properties   :::" + ex.getMessage();
        }
        return msg;
    }
	
	public static String crearZip(ComprobantePago compPago, String unidadEnvio, File signatureFile) {
        String resultado = "";
        try {
            //Mandar a zip
            LOGGER.info("generarXMLZipiadoFactura - Crear ZIP ");
            String inputFile = signatureFile.toString();
            FileInputStream in = new FileInputStream(inputFile);
            FileOutputStream out = new FileOutputStream(unidadEnvio + compPago.getEmpresa().getNumeroDocumento() + "-" + compPago.getTipoDocumento().getCodigo() + "-" + compPago.getNumero() + ".zip");

            byte b[] = new byte[2048];
            try (ZipOutputStream zipOut = new ZipOutputStream(out)) {
                ZipEntry entry2 = new ZipEntry(compPago.getEmpresa().getNumeroDocumento() + "-" + compPago.getTipoDocumento().getCodigo() + "-" + compPago.getNumero() + ".xml");
                zipOut.putNextEntry(entry2);
                System.out.println("==>Zip generado: " + compPago.getEmpresa().getNumeroDocumento() + "-" + compPago.getTipoDocumento().getCodigo() + "-" + compPago.getNumero() + ".zip");
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    zipOut.write(b, 0, len);
                }
                zipOut.closeEntry();
            }
            out.close();
            in.close();
            LOGGER.info("generarXMLZipiadoFactura - Zip creado " + unidadEnvio + compPago.getEmpresa().getNumeroDocumento() + "-" + compPago.getTipoDocumento().getCodigo() + "-" + compPago.getNumero() + ".zip");

        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "0100|Error al generar el archivo de formato xml de la Factura.";
            LOGGER.error("generarXMLZipiadoFactura - error  " + ex.toString());
        }
        return resultado;
    }
	    
	    /*

	    public static String getErrorMesageByCode(String errorCode, String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "errorFile.properties"));
	            msg = errorCode + "|" + propiedades.getProperty(errorCode);
	        } catch (Exception ex) {
	            msg = "0100|Error en getErrorMesageByCode: " + ex.getMessage();
	        }
	        return msg;
	    }

	    

	    

	    public static String getPathJasperFiles(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));
	            msg = "" + propiedades.getProperty("pathFilesJasper");
	        } catch (Exception ex) {
	            msg = "Error en getpathFilesRecepcion: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getPathFilesLogo(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));
	            msg = "" + propiedades.getProperty("pathFilesLogo");
	        } catch (Exception ex) {
	            msg = "Error en getpathFilesRecepcion: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getPurl(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));
	            msg = "" + propiedades.getProperty("p_url");
	        } catch (Exception ex) {
	            msg = "Error en getpathFilesRecepcion: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getPResolucion(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));
	            msg = "" + propiedades.getProperty("p_resolucion");
	        } catch (Exception ex) {
	            msg = "Error en getpathFilesRecepcion: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getFtpServer(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("subirFtpServer");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getFtpUser(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("subirFtpUser");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getFtpPass(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("subirFtpPass");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getFtpBase(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("subirFtpBase");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    

	    public static String equivalenciaTipo(String tipo) {
	        String result = "";
	        for (int i = 0; i < eq.length; i++) {
	            if (eq[i][0].equals(tipo)) {
	                result = eq[i][1];
	            }
	        }
	        return result;
	    }

	    public static String equivalenciaTipoDocNombre(String tipo) {
	        String result = "";
	        for (int i = 0; i < eq.length; i++) {
	            if (eq[i][0].equals(tipo)) {
	                result = eq[i][2];
	            }
	        }
	        return result;
	    }

	    public String Desencriptar(String textoEncriptado) throws Exception {
	        String secretKey = "imperiocore";
	        String base64EncryptedString = "";
	        byte message[] = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte digestOfPassword[] = md.digest(secretKey.getBytes("utf-8"));
	        byte keyBytes[] = Arrays.copyOf(digestOfPassword, 24);
	        javax.crypto.SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	        Cipher decipher = Cipher.getInstance("DESede");
	        decipher.init(2, key);
	        byte plainText[] = decipher.doFinal(message);
	        base64EncryptedString = new String(plainText, "UTF-8");
	        return base64EncryptedString;
	    }

	    public String Encriptar(String texto) throws Exception {
	        String secretKey = "imperiocore";
	        String base64EncryptedString = "";
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte digestOfPassword[] = md.digest(secretKey.getBytes("utf-8"));
	        byte keyBytes[] = Arrays.copyOf(digestOfPassword, 24);
	        javax.crypto.SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	        Cipher cipher = Cipher.getInstance("DESede");
	        cipher.init(1, key);
	        byte plainTextBytes[] = texto.getBytes("utf-8");
	        byte buf[] = cipher.doFinal(plainTextBytes);
	        byte base64Bytes[] = Base64.encodeBase64(buf);
	        base64EncryptedString = new String(base64Bytes);
	        return base64EncryptedString;
	    }

	    public static String getMailHost(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.server.host");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getMailPort(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.server.port");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getMailSsl(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.enable.ssl");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getMailAuth(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.auth");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getMailUser(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.username");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getMailPass(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("mail.password");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }

	    public static String getWsOpcion(String vruc) {
	        String msg = "";
	        try {
	            Properties propiedades = new Properties();
	            propiedades.load(new FileInputStream(rutaini + vruc + rutafin + "property.properties"));

	            msg = "" + propiedades.getProperty("wsopcion");

	        } catch (Exception ex) {
	            msg = "Error en getPathFilesEnvio: " + ex.getMessage();
	        }
	        return msg;
	    }*/
}
