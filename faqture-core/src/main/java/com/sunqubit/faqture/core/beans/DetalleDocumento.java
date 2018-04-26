package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;

public class DetalleDocumento {
	private long id;
	private ComprobantePago comprobantePago;
	private long orden;
	private String codigoProducto;
	private String descripcion;
	private UnidadMedida unidadMedida;
	private BigDecimal cantidad;
	private BigDecimal precioVenta;
	private BigDecimal descuento;
	private BigDecimal ventaNoOnerosa;
	private BigDecimal igv;
	private BigDecimal isc;
	private BigDecimal subtotal;
	private TipoAfectacionIgv tipoAfectacionIgv;
	private TipoIsc tipoIsc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ComprobantePago getCpmprobanteDocumento() {
		return comprobantePago;
	}
	public void setComprobanteDocumento(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
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
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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
	public BigDecimal getIsc() {
		return isc;
	}
	public void setIsc(BigDecimal isc) {
		this.isc = isc;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public TipoAfectacionIgv getTipoAfectacionIgv() {
		return tipoAfectacionIgv;
	}
	public void setTipoAfectacionIgv(TipoAfectacionIgv tipoAfectacionIgv) {
		this.tipoAfectacionIgv = tipoAfectacionIgv;
	}
	public TipoIsc getTipoIsc() {
		return tipoIsc;
	}
	public void setTipoIsc(TipoIsc tipoIsc) {
		this.tipoIsc = tipoIsc;
	}
}
