package com.sunqubit.faqture.core.beans;

public class TipoDocumento {

	public TipoDocumento(long tidoCodigo, String tidoDescripcion) {
		super();
		this.tidoCodigo = tidoCodigo;
		this.tidoDescripcion = tidoDescripcion;
	}

	private long tidoCodigo;
	private String tidoDescripcion;

	public long getTidoCodigo() {
		return tidoCodigo;
	}

	public void setTidoCodigo(long tidoCodigo) {
		this.tidoCodigo = tidoCodigo;
	}

	public String getTidoDescripcion() {
		return tidoDescripcion;
	}

	public void setTidoDescripcion(String tidoDescripcion) {
		this.tidoDescripcion = tidoDescripcion;
	}

}
