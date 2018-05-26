package com.sunqubit.faqture.beans.utils;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StoreManager {
	static String[] rutaAMes = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	
	public static String getDirectorio(int tipo, String tiid, String numd, Timestamp fechaTS) {
		MainProperties mainProperties = new MainProperties();
		String ruta = mainProperties.getPropertyValue("store.mainurlserver");
    	String sep = mainProperties.getPropertyValue("store.separadorurl");
    	ruta += "faqture" + sep;
    	switch (tipo) {
			case 1: 
				ruta += "privado" + sep + tiid + "-" + numd + sep + "config" + sep;	
				break;
			case 2: 
				ruta += "privado" + sep + tiid + "-" + numd + sep + "sunat" + sep + "envio" + sep; 
				break;
			case 3: 
				ruta += "privado" + sep + tiid + "-" + numd + sep + "sunat" + sep + "recepcion" + sep; 
				break;
			case 4: 
				ruta += "publico" + sep + tiid + "-" + numd + sep + "sunat" + sep; 
				break;
			case 5: 
				ruta += "publico" + sep + tiid + "-" + numd + sep + "codigoqr" + sep; 
				break;
    	}
    	if(tipo > 1 && tipo < 6 && fechaTS != null) {
    		LocalDateTime fecha = fechaTS.toLocalDateTime();
    		String anio = String.valueOf(fecha.getYear());
    		String mes = rutaAMes[Integer.valueOf(fecha.getMonthValue() - 1)];
    		ruta += anio + sep + mes + sep;
    	}
    	File folder = new File(ruta);
		if (!folder.exists()) folder.mkdirs();
    	return ruta;
	}
}
