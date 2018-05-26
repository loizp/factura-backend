package com.sunqubit.faqture.sunat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;

public class DigitalFileCreator {
	private static final Logger LOGGER = LoggerFactory.getLogger(DigitalFileCreator.class);
	
	public static Boolean crearZip(String xmlName, String unidadEnvio, File signatureFile) {
		Boolean resultado = true;
        try {
            LOGGER.info("DigFileCreator - crearZip | Iniciando con la creación");
            String inputFile = signatureFile.toString();
            FileInputStream in = new FileInputStream(inputFile);
            FileOutputStream out = new FileOutputStream(unidadEnvio + xmlName.substring(0,xmlName.length() - 4) + ".zip");

            byte b[] = new byte[2048];
            try (ZipOutputStream zipOut = new ZipOutputStream(out)) {
                ZipEntry entry2 = new ZipEntry(xmlName);
                zipOut.putNextEntry(entry2);
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    zipOut.write(b, 0, len);
                }
                zipOut.closeEntry();
            } catch (Exception e) {
            	e.printStackTrace();
				resultado = false;
				LOGGER.info("DigFileCreator - crearZip | error  " + e.toString());
			}
            out.close();
            in.close();
            LOGGER.info("DigFileCreator - crearZip | Zip creado " + unidadEnvio + xmlName.substring(0,xmlName.length() - 4) + ".zip");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = false;
            LOGGER.info("DigFileCreator - crearZip | error  " + ex.toString());
        }
        return resultado;
	}
	
	public static Boolean creaImgQRPdf417(String infobarcode, String fileFinal) {
		Boolean resultado = true;
		try {			
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(infobarcode, BarcodeFormat.QR_CODE, 350, 350);
			Path path = FileSystems.getDefault().getPath(fileFinal + "-qr.png");
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			
			Writer writer = new PDF417Writer();
			bitMatrix = writer.encode(infobarcode, BarcodeFormat.PDF_417, 200, 100);
			path = FileSystems.getDefault().getPath(fileFinal + "-pdf417.png");
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		} catch (Exception ex) {
			ex.printStackTrace();
			resultado = false;
			LOGGER.info("DigFileCreator - creaImgQRPdf417 | error  " + ex.toString());
		}
		return resultado;
	}
}
