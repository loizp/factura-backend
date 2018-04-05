package com.sunqubit.faqture.core.beans;

public class Empresa {

	private long emprId;
	private String emprRuc;
	private String emprRazonSocial;
	private String emprNombreComercial;
	private String emprDireccion;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private Ubigeo ubigeo;

	public long getEmprId() {
		return emprId;
	}

	public void setEmprId(long emprId) {
		this.emprId = emprId;
	}

	public String getEmprRuc() {
		return emprRuc;
	}

	public void setEmprRuc(String emprRuc) {
		this.emprRuc = emprRuc;
	}

	public String getEmprRazonSocial() {
		return emprRazonSocial;
	}

	public void setEmprRazonSocial(String emprRazonSocial) {
		this.emprRazonSocial = emprRazonSocial;
	}

	public String getEmprNombreComercial() {
		return emprNombreComercial;
	}

	public void setEmprNombreComercial(String emprNombreComercial) {
		this.emprNombreComercial = emprNombreComercial;
	}

	public String getEmprDireccion() {
		return emprDireccion;
	}

	public void setEmprDireccion(String emprDireccion) {
		this.emprDireccion = emprDireccion;
	}

	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

}
