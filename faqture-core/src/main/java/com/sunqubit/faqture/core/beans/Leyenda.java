package com.sunqubit.faqture.core.beans;

public class Leyenda {
	private ComprobantePago empresa;
	private TipoLeyenda tipoLeyenda;
	private String descripcion;
	
	public ComprobantePago getEmpresa() {
		return empresa;
	}
	public void setEmpresa(ComprobantePago empresa) {
		this.empresa = empresa;
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
