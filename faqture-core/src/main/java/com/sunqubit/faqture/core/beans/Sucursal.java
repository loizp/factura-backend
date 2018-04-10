package com.sunqubit.faqture.core.beans;

public class Sucursal {
	private long sucuId;
	private String sucuDireccion;
	private Empresa empresa;
	private Ubigeo ubigeo;
	
	public long getSucuId() {
		return sucuId;
	}
	public void setSucuId(long sucuId) {
		this.sucuId = sucuId;
	}
	public String getSucuDireccion() {
		return sucuDireccion;
	}
	public void setSucuDireccion(String sucuDireccion) {
		this.sucuDireccion = sucuDireccion;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	
}
