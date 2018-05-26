package com.sunqubit.faqture.beans.core;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunqubit.faqture.beans.catalogs.TipoOperacion;

public class ComprobantePago extends Documento {
	private Contribuyente cliente;
	private Sucursal clieSucursal;
	private TipoOperacion tipoOperacion;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="CST")
	private Timestamp fechaVencimiento;
	private BigDecimal subtotal = BigDecimal.valueOf(0.00);
	private BigDecimal grabada = BigDecimal.valueOf(0.00);
	private BigDecimal inafecta = BigDecimal.valueOf(0.00);
	private BigDecimal exonerada = BigDecimal.valueOf(0.00);
	private BigDecimal gratuita = BigDecimal.valueOf(0.00);
	private BigDecimal descuento = BigDecimal.valueOf(0.00);
	private BigDecimal igv = BigDecimal.valueOf(0.00);
	private BigDecimal isc = BigDecimal.valueOf(0.00);
	private BigDecimal otrosTributos = BigDecimal.valueOf(0.00);
	private BigDecimal otrosCargos = BigDecimal.valueOf(0.00);
	private BigDecimal total = BigDecimal.valueOf(0.00);
	private String vendedor;
	private String emailCliente;
	private Boolean anulado = false;
	private List<DetalleDocumento> detallesDocumento;
	
	public Contribuyente getCliente() {
		return cliente;
	}
	public void setCliente(Contribuyente cliente) {
		this.cliente = cliente;
	}
	public Sucursal getClieSucursal() {
		return clieSucursal;
	}
	public void setClieSucursal(Sucursal clieSucursal) {
		this.clieSucursal = clieSucursal;
	}
	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		if(tipoOperacion == null) {
			tipoOperacion = new TipoOperacion();
			tipoOperacion.setCodigo("01");
		}
		this.tipoOperacion = tipoOperacion;
	}
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		if(subtotal == null) subtotal = BigDecimal.valueOf(0.00);
		this.subtotal = subtotal;
	}
	public BigDecimal getGrabada() {
		return grabada;
	}
	public void setGrabada(BigDecimal grabada) {
		if(grabada == null) grabada = BigDecimal.valueOf(0.00);
		this.grabada = grabada;
	}
	public BigDecimal getInafecta() {
		return inafecta;
	}
	public void setInafecta(BigDecimal inafecta) {
		if(inafecta == null) inafecta = BigDecimal.valueOf(0.00);
		this.inafecta = inafecta;
	}
	public BigDecimal getExonerada() {
		return exonerada;
	}
	public void setExonerada(BigDecimal exonerada) {
		if(exonerada == null) exonerada = BigDecimal.valueOf(0.00);
		this.exonerada = exonerada;
	}
	public BigDecimal getGratuita() {
		return gratuita;
	}
	public void setGratuita(BigDecimal gratuita) {
		if(gratuita == null) gratuita = BigDecimal.valueOf(0.00);
		this.gratuita = gratuita;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		if(descuento == null) descuento = BigDecimal.valueOf(0.00);
		this.descuento = descuento;
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
	public BigDecimal getOtrosTributos() {
		return otrosTributos;
	}
	public void setOtrosTributos(BigDecimal otrosTributos) {
		if(otrosTributos == null) otrosTributos = BigDecimal.valueOf(0.00);
		this.otrosTributos = otrosTributos;
	}
	public BigDecimal getOtrosCargos() {
		return otrosCargos;
	}
	public void setOtrosCargos(BigDecimal otrosCargos) {
		if(otrosCargos == null) otrosCargos = BigDecimal.valueOf(0.00);
		this.otrosCargos = otrosCargos;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		if(total == null) total = BigDecimal.valueOf(0.00);
		this.total = total;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public Boolean getAnulado() {
		return anulado;
	}
	public void setAnulado(Boolean anulado) {
		this.anulado = anulado;
	}
	public List<DetalleDocumento> getDetallesDocumento() {
		return detallesDocumento;
	}
	public void setDetallesDocumento(List<DetalleDocumento> detallesDocumento) {
		this.detallesDocumento = detallesDocumento;
	}
}
