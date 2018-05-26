package com.sunqubit.faqture.beans.core;

import java.math.BigDecimal;

import com.sunqubit.faqture.beans.catalogs.Moneda;
import com.sunqubit.faqture.beans.catalogs.TipoAfectacionIgv;
import com.sunqubit.faqture.beans.catalogs.TipoIsc;
import com.sunqubit.faqture.beans.catalogs.UnidadMedida;

public class DetalleDocumento {
	private long id;
	private ComprobantePago comprobantePago;
	private long orden;
	private String codigoProducto;
	private String descripcion;
	private UnidadMedida unidadMedida;
	private Moneda moneda;
	private BigDecimal cantidad = BigDecimal.valueOf(0.00);
	private BigDecimal precioVenta = BigDecimal.valueOf(0.00);
	private BigDecimal descuento = BigDecimal.valueOf(0.00);
	private BigDecimal ventaNoOnerosa = BigDecimal.valueOf(0.00);
	private BigDecimal igv = BigDecimal.valueOf(0.00);
	private BigDecimal isc = BigDecimal.valueOf(0.00);
	private BigDecimal subtotal = BigDecimal.valueOf(0.00);
	private TipoAfectacionIgv tipoAfectacionIgv;
	private TipoIsc tipoIsc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		if(codigoProducto == null) codigoProducto = "SIN CODIGO";
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
		if(unidadMedida == null) {
			unidadMedida = new UnidadMedida();
			unidadMedida.setCodigo("NIU");
		}
		this.unidadMedida = unidadMedida;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		if(cantidad == null) cantidad = BigDecimal.valueOf(0.00);
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		if(precioVenta == null) precioVenta = BigDecimal.valueOf(0.00);
		this.precioVenta = precioVenta;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		if(descuento == null) descuento = BigDecimal.valueOf(0.00);
		this.descuento = descuento;
	}
	public BigDecimal getVentaNoOnerosa() {
		return ventaNoOnerosa;
	}
	public void setVentaNoOnerosa(BigDecimal ventaNoOnerosa) {
		if(ventaNoOnerosa == null) ventaNoOnerosa = BigDecimal.valueOf(0.00);
		this.ventaNoOnerosa = ventaNoOnerosa;
	}
	public BigDecimal getIgv() {
		return igv;
	}
	public void setIgv(BigDecimal igv) {
		if(igv == null) igv = BigDecimal.valueOf(0.00);
		this.igv = igv;
	}
	public BigDecimal getIsc() {
		return isc;
	}
	public void setIsc(BigDecimal isc) {
		if(isc == null) isc = BigDecimal.valueOf(0.00);
		this.isc = isc;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		if(subtotal == null) subtotal = BigDecimal.valueOf(0.00);
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
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		if(moneda == null) {
			moneda = new Moneda();
			moneda.setCodigo("PEN");
		}
		this.moneda = moneda;
	}
	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}
	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}
}
