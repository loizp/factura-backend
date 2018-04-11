package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;

public class DetalleDocumento {
	private long dedoId;
	private Documento documento;
	private long dedoOrden;
	private String dedoCodigoProducto;
	private String dedoDescripcion;
	private UnidadMedida unidadMedida;
	private BigDecimal dedoCantidad;
	private BigDecimal dedoPrecioVenta;
	private BigDecimal dedoVentaNoOnerosa;
	private BigDecimal dedoIGV;
	private TipoAfectacionIgv tipoAfectacionIgv;

	public long getDedoId() {
		return dedoId;
	}

	

	public void setDedoId(long dedoId) {
		this.dedoId = dedoId;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public long getDedoOrden() {
		return dedoOrden;
	}

	public void setDedoOrden(long dedoOrden) {
		this.dedoOrden = dedoOrden;
	}

	public String getDedoCodigoProducto() {
		return dedoCodigoProducto;
	}

	public void setDedoCodigoProducto(String dedoCodigoProducto) {
		this.dedoCodigoProducto = dedoCodigoProducto;
	}

	public String getDedoDescripcion() {
		return dedoDescripcion;
	}

	public void setDedoDescripcion(String dedoDescripcion) {
		this.dedoDescripcion = dedoDescripcion;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public BigDecimal getDedoCantidad() {
		return dedoCantidad;
	}

	public void setDedoCantidad(BigDecimal dedoCantidad) {
		this.dedoCantidad = dedoCantidad;
	}

	public BigDecimal getDedoPrecioVenta() {
		return dedoPrecioVenta;
	}

	public void setDedoPrecioVenta(BigDecimal dedoPrecioVenta) {
		this.dedoPrecioVenta = dedoPrecioVenta;
	}

	public BigDecimal getDedoVentaNoOnerosa() {
		return dedoVentaNoOnerosa;
	}

	public void setDedoVentaNoOnerosa(BigDecimal dedoVentaNoOnerosa) {
		this.dedoVentaNoOnerosa = dedoVentaNoOnerosa;
	}

	public BigDecimal getDedoIGV() {
		return dedoIGV;
	}

	public void setDedoIGV(BigDecimal dedoIGV) {
		this.dedoIGV = dedoIGV;
	}

	public TipoAfectacionIgv getTipoAfectacionIgv() {
		return tipoAfectacionIgv;
	}

	public void setTipoAfectacionIgv(TipoAfectacionIgv tipoAfectacionIgv) {
		this.tipoAfectacionIgv = tipoAfectacionIgv;
	}

}
