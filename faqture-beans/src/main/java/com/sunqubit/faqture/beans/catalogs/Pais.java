package com.sunqubit.faqture.beans.catalogs;

public class Pais {
	private String codigo = "PE";
	private String nombre;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		if(codigo == null) codigo = "PE";
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
