package com.sunqubit.faqture.beans.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class LocalProperties {
	private static Properties Propiedades;
    private String archivo = getClass().getClassLoader().getResource("faqture.properties").getPath();
    
	public LocalProperties() {
		Propiedades = new Properties();
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getPropertyValue(String paramName) {
        String msg = "";
        try {
            Propiedades.load(new FileInputStream(this.archivo));
            msg = "" + Propiedades.getProperty(paramName);
        } catch (Exception ex) {
        	ex.printStackTrace();
            msg ="Ocurrió un error a intentar obtener la propiedad: " + paramName;
        }
        return msg;
    }
}
