package com.sunqubit.faqture.beans.core;

import java.math.BigDecimal;

import com.sunqubit.faqture.beans.catalogs.TipoNota;

public class NotaDC extends Documento {
	private TipoNota tipoNota;
	private String sustentoNota;
	private BigDecimal igv = BigDecimal.valueOf(0.00);
	private BigDecimal total = BigDecimal.valueOf(0.00);
	
	public TipoNota getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}
	public String getSustentoNota() {
		return sustentoNota;
	}
	public void setSustentoNota(String sustentoNota) {
		this.sustentoNota = sustentoNota;
	}
	public BigDecimal getIgv() {
		return igv;
	}
	public void setIgv(BigDecimal igv) {
		if(igv == null) igv = BigDecimal.valueOf(0.00);
		this.igv = igv;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		if(total == null) total = BigDecimal.valueOf(0.00);
		this.total = total;
	}	
}
