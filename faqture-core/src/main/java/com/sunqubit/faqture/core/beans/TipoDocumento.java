package com.sunqubit.faqture.core.beans;

import java.util.List;

public class TipoDocumento {

	private long codigo;
	private String descripcion;
	private List<TipoNota> tiposNotas;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
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
