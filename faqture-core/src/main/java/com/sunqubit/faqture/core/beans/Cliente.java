package com.sunqubit.faqture.core.beans;

public class Cliente {
	private long clieId;
	private String clieNumero;
	private String clieNombres;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;

	public long getClieId() {
		return clieId;
	}

	public void setClieId(long clieId) {
		this.clieId = clieId;
	}

	public String getClieNumero() {
		return clieNumero;
	}

	public void setClieNumero(String clieNumero) {
		this.clieNumero = clieNumero;
	}

	public String getClieNombres() {
		return clieNombres;
	}

	public void setClieNombres(String clieNombres) {
		this.clieNombres = clieNombres;
	}

	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

}
