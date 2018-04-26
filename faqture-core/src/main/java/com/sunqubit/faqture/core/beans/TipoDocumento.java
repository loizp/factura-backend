package com.sunqubit.faqture.core.beans;

import java.util.List;

public class TipoDocumento {

	private String codigo;
	private String descripcion;
	private List<TipoNota> tiposNotas;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<TipoNota> getTiposNotas() {
		return tiposNotas;
	}
	public void setTiposNotas(List<TipoNota> tiposNotas) {
		this.tiposNotas = tiposNotas;
	}	
}
