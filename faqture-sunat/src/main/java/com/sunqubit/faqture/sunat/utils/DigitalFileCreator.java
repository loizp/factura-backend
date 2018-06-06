package com.sunqubit.faqture.sunat.utils;

import static com.sunqubit.faqture.beans.utils.ConstantProperty.QRNAME_EXT;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.PDF417NAME_EXT;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.ZIP_EXT;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import com.sunqubit.faqture.beans.utils.StoreManager;

public class DigitalFileCreator {
	private static final Logger LOGGER = LoggerFactory.getLogger(DigitalFileCreator.class);
	
	public static Boolean crearZip(String xmlName, String unidadEnvio) {
		Boolean resultado = true;
        try {
            LOGGER.info("DigFileCreator - crearZip | Iniciando con la creación");
            byte[] buffer = new byte[2048];
            if(StoreManager.store == 1) {
            	FileInputStream fin = new FileInputStream(unidadEnvio + xmlName);
            	FileOutputStream fout = new FileOutputStream(unidadEnvio + xmlName.substring(0,xmlName.length() - 4) + ZIP_EXT);
            	try (ZipOutputStream zipOut = new ZipOutputStream(fout)) {
            		ZipEntry entry = new ZipEntry(xmlName);
                    zipOut.putNextEntry(entry);
                    int len = 0;
                    while ((len = fin.read(buffer)) != -1) {
                        zipOut.write(buffer, 0, len);
                    }
                    zipOut.closeEntry();
                }
            	fout.close();
                fin.close();
            }
            if(StoreManager.store == 2) {
            	InputStream in = StoreManager.getFileStore(unidadEnvio + xmlName);
            	ByteArrayOutputStream bout = new ByteArrayOutputStream();
            	try (ZipOutputStream zipOut = new ZipOutputStream(bout)) {
            		ZipEntry entry = new ZipEntry(xmlName);
                    zipOut.putNextEntry(entry);
                    int len = 0;
                    while ((len = in.read(buffer)) != -1) {
                        zipOut.write(buffer, 0, len);
                    }
                    zipOut.closeEntry();
                }
            	StoreManager.saveXMLZipStore(bout.toByteArray(), unidadEnvio + xmlName.substring(0,xmlName.length() - 4) + ZIP_EXT);
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = false;
            LOGGER.info("DigFileCreator - crearZip | error  " + ex.toString());
        }
        return resultado;
	}
	
	public static Boolean creaImgQRPdf417(String infobarcode, String fileFinal) {
		LOGGER.info("DigFileCreator - creaImgQRPdf417 | Iniciando con la creación de las imagenes QRPDF417");
		Boolean resultado = true;
		try {			
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(infobarcode, BarcodeFormat.QR_CODE, 350, 350);
			if(StoreManager.store == 1) {
				Path pathqr = FileSystems.getDefault().getPath(fileFinal + QRNAME_EXT);
				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", pathqr);
			}
			if(StoreManager.store == 2) {
				BufferedImage codigoImgqr = MatrixToImageWriter.toBufferedImage(bitMatrix);
				StoreManager.qrpdf417Store(codigoImgqr, fileFinal + QRNAME_EXT);
			}
			Writer writer = new PDF417Writer();
			bitMatrix = writer.encode(infobarcode, BarcodeFormat.PDF_417, 200, 100);
			if(StoreManager.store == 1) {
				Path pathpdf417 = FileSystems.getDefault().getPath(fileFinal + PDF417NAME_EXT);
				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", pathpdf417);
			}
			if(StoreManager.store == 2) {
				BufferedImage codigoImgpdf417 = MatrixToImageWriter.toBufferedImage(bitMatrix);
				StoreManager.qrpdf417Store(codigoImgpdf417, fileFinal + PDF417NAME_EXT);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultado = false;
			LOGGER.info("DigFileCreator - creaImgQRPdf417 | error  " + ex.toString());
		}
		return resultado;
	}
}
