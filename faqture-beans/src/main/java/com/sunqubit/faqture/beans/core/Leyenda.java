package com.sunqubit.faqture.beans.core;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;

public class Leyenda {
	private Documento documento;
	private TipoLeyenda tipoLeyenda;
	private String descripcion;
	
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public TipoLeyenda getTipoLeyenda() {
		return tipoLeyenda;
	}
	public void setTipoLeyenda(TipoLeyenda tipoLeyenda) {
		this.tipoLeyenda = tipoLeyenda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
