package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;

public class DetalleDocumento {
	private long id;
	private Documento documento;
	private long orden;
	private String codigoProducto;
	private String descripcion;
	private UnidadMedida unidadMedida;
	private BigDecimal cantidad;
	private BigDecimal precioVenta;
	private BigDecimal ventaNoOnerosa;
	private BigDecimal igv;
	private TipoAfectacionIgv tipoAfectacionIgv;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public long getOrden() {
		return orden;
	}
	public void setOrden(long orden) {
		this.orden = orden;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public BigDecimal getVentaNoOnerosa() {
		return ventaNoOnerosa;
	}
	public void setVentaNoOnerosa(BigDecimal ventaNoOnerosa) {
		this.ventaNoOnerosa = ventaNoOnerosa;
	}
	public BigDecimal getIgv() {
		return igv;
	}
	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}
	public TipoAfectacionIgv getTipoAfectacionIgv() {
		return tipoAfectacionIgv;
	}
	public void setTipoAfectacionIgv(TipoAfectacionIgv tipoAfectacionIgv) {
		this.tipoAfectacionIgv = tipoAfectacionIgv;
	}

}
