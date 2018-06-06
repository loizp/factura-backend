package com.sunqubit.faqture.beans.utils;

import static com.sunqubit.faqture.beans.utils.ConstantProperty.GCSTORE_BUCKET;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.ARRAY_MONTH;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.CONFIG_FOLDER;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.TOSUNAT_FOLDER;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.FROMSUNAT_FOLDER;
import static com.sunqubit.faqture.beans.utils.ConstantProperty.QRPDF417_FOLDER;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.common.net.MediaType;

public class StoreManager {	
	private static Storage storage = StorageOptions.getDefaultInstance().getService();
	
	public static int store = 1; // 1:Local - 2:Google
	
	public static String getDirectorioLocal(int tipo, String foldername, Timestamp fechaTS) {
		LocalProperties mainProperties = new LocalProperties();
		String ruta = mainProperties.getPropertyValue("store.urlLocal");
    	String sep = mainProperties.getPropertyValue("store.separadorurl");    	
    	ruta += "faqture" + sep;
    	switch (tipo) {
			case 1: 
				ruta += "privado" + sep + foldername + sep + "config" + sep;	
				break;
			case 2: 
				ruta += "privado" + sep + foldername + sep + "sunat" + sep + "envio" + sep; 
				break;
			case 3: 
				ruta += "privado" + sep + foldername + sep + "sunat" + sep + "recepcion" + sep; 
				break;
			case 4: 
				ruta += "publico" + sep + foldername + sep + "sunat" + sep; 
				break;
			case 5: 
				ruta += "publico" + sep + foldername + sep + "codigoqr" + sep; 
				break;
    	}
    	if(tipo > 1 && tipo < 6 && fechaTS != null) {
    		LocalDateTime fecha = fechaTS.toLocalDateTime();
    		String anio = String.valueOf(fecha.getYear());
    		String mes = ARRAY_MONTH[Integer.valueOf(fecha.getMonthValue() - 1)];
    		ruta += anio + sep + mes + sep;
    	}
    	File folder = new File(ruta);
		if (!folder.exists()) folder.mkdirs();
    	return ruta;
	}
	
	public static String getDirectorioGCloud(int tipo, String foldername, Timestamp fechaTS) {
		String ruta = "";
		switch (tipo) {
			case 1: 
				ruta += foldername + CONFIG_FOLDER;	
				break;
			case 2: 
				ruta += foldername + TOSUNAT_FOLDER; 
				break;
			case 3: 
				ruta += foldername + FROMSUNAT_FOLDER; 
				break;
			case 4: 
				ruta += foldername + QRPDF417_FOLDER; 
				break;
		}
		if(tipo > 1 && tipo < 5 && fechaTS != null) {
			LocalDateTime fecha = fechaTS.toLocalDateTime();
			String anio = String.valueOf(fecha.getYear());
			String mes = ARRAY_MONTH[Integer.valueOf(fecha.getMonthValue() - 1)];
			ruta += anio + "/" + mes + "/";
		}
		return ruta;
	}
	
	public static void qrpdf417Store(BufferedImage codigoImg, String blobName) throws Exception {
		try {
			BlobId blobId = BlobId.of(GCSTORE_BUCKET, blobName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(MediaType.PNG.toString()).build();
			storage.create(blobInfo, toByteArrayAutoClosable(codigoImg, blobName.substring(blobName.length() - 3)));
			storage.createAcl(blobId, Acl.of(User.ofAllUsers(), Role.READER));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }
	
	public static byte[] getBlobGCloud(String blobName) {
		BlobId blobId = BlobId.of(GCSTORE_BUCKET, blobName);
		return storage.readAllBytes(blobId);
	}
	
	public static InputStream getFileStore(String blobName) {
		InputStream myInputStream = new ByteArrayInputStream(getBlobGCloud(blobName));
		return myInputStream;
	}
	
	public static void saveXMLStore(byte[] archivo, String blobName) throws Exception {
		BlobId blobId = BlobId.of(GCSTORE_BUCKET, blobName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(MediaType.XML_UTF_8.toString()).build();
		storage.create(blobInfo, archivo);
	}
	
	@SuppressWarnings("deprecation")
	public static void saveXMLZipStore(byte[] archivo, String blobName) throws Exception {
		InputStream in = new ByteArrayInputStream(archivo);
		BlobId blobId = BlobId.of(GCSTORE_BUCKET, blobName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(MediaType.ZIP.toString()).build();
		storage.create(blobInfo, in);
	}
}
