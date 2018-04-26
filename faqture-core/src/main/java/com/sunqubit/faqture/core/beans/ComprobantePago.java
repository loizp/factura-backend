package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ComprobantePago extends Documento {
	private Cliente cliente;
	private Sucursal clieSucursal;
	private TipoOperacion tipoOperacion;
	private Date fechaVencimiento;
	private BigDecimal subtotal;
	private BigDecimal grabada;
	private BigDecimal inafecta;
	private BigDecimal exonerada;
	private BigDecimal gratuita;
	private BigDecimal descuento;
	private BigDecimal igv;
	private BigDecimal isc;
	private BigDecimal otrosTributos;
	private BigDecimal otrosCargos;
	private BigDecimal total;
	private String vendedor;
	private String emailCliente;
	private Boolean anulado;
	private List<DetalleDocumento> detallesDocumento;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
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
		this.tipoOperacion = tipoOperacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getGrabada() {
		return grabada;
	}
	public void setGrabada(BigDecimal grabada) {
		this.grabada = grabada;
	}
	public BigDecimal getInafecta() {
		return inafecta;
	}
	public void setInafecta(BigDecimal inafecta) {
		this.inafecta = inafecta;
	}
	public BigDecimal getExonerada() {
		return exonerada;
	}
	public void setExonerada(BigDecimal exonerada) {
		this.exonerada = exonerada;
	}
	public BigDecimal getGratuita() {
		return gratuita;
	}
	public void setGratuita(BigDecimal gratuita) {
		this.gratuita = gratuita;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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
	public BigDecimal getOtrosTributos() {
		return otrosTributos;
	}
	public void setOtrosTributos(BigDecimal otrosTributos) {
		this.otrosTributos = otrosTributos;
	}
	public BigDecimal getOtrosCargos() {
		return otrosCargos;
	}
	public void setOtrosCargos(BigDecimal otrosCargos) {
		this.otrosCargos = otrosCargos;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
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
