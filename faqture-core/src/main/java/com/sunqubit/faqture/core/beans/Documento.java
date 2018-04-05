package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Documento {

	private long docuId;
	private Empresa empresa;
	private Cliente cliente;
	private Date docuFecha;
	private String docuNumero;
	private Moneda moneda;
	private TipoDocumento tipoDocumento;
	private BigDecimal docuGrabada;
	private BigDecimal docuInafecta;
	private BigDecimal docuExonerada;
	private BigDecimal docuGratuita;
	private BigDecimal docuDescuento;
	private BigDecimal docuSubtotal;
	private BigDecimal docuTotal;
	private BigDecimal docuIgv;
	private BigDecimal docuIsc;
	private BigDecimal docuOtrosTributos;
	private BigDecimal docuOtrosCargos;
	private String docuVendedor;
	private TipoOperacion tipoOperacion;
	private List<DetalleDocumento> detalleDocumentos;

	public long getDocuId() {
		return docuId;
	}

	public void setDocuId(long docuId) {
		this.docuId = docuId;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDocuFecha() {
		return docuFecha;
	}

	public void setDocuFecha(Date docuFecha) {
		this.docuFecha = docuFecha;
	}

	public String getDocuNumero() {
		return docuNumero;
	}

	public void setDocuNumero(String docuNumero) {
		this.docuNumero = docuNumero;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public BigDecimal getDocuGrabada() {
		return docuGrabada;
	}

	public void setDocuGrabada(BigDecimal docuGrabada) {
		this.docuGrabada = docuGrabada;
	}

	public BigDecimal getDocuInafecta() {
		return docuInafecta;
	}

	public void setDocuInafecta(BigDecimal docuInafecta) {
		this.docuInafecta = docuInafecta;
	}

	public BigDecimal getDocuExonerada() {
		return docuExonerada;
	}

	public void setDocuExonerada(BigDecimal docuExonerada) {
		this.docuExonerada = docuExonerada;
	}

	public BigDecimal getDocuGratuita() {
		return docuGratuita;
	}

	public void setDocuGratuita(BigDecimal docuGratuita) {
		this.docuGratuita = docuGratuita;
	}

	public BigDecimal getDocuDescuento() {
		return docuDescuento;
	}

	public void setDocuDescuento(BigDecimal docuDescuento) {
		this.docuDescuento = docuDescuento;
	}

	public BigDecimal getDocuSubtotal() {
		return docuSubtotal;
	}

	public void setDocuSubtotal(BigDecimal docuSubtotal) {
		this.docuSubtotal = docuSubtotal;
	}

	public BigDecimal getDocuTotal() {
		return docuTotal;
	}

	public void setDocuTotal(BigDecimal docuTotal) {
		this.docuTotal = docuTotal;
	}

	public BigDecimal getDocuIgv() {
		return docuIgv;
	}

	public void setDocuIgv(BigDecimal docuIgv) {
		this.docuIgv = docuIgv;
	}

	public BigDecimal getDocuIsc() {
		return docuIsc;
	}

	public void setDocuIsc(BigDecimal docuIsc) {
		this.docuIsc = docuIsc;
	}

	public BigDecimal getDocuOtrosTributos() {
		return docuOtrosTributos;
	}

	public void setDocuOtrosTributos(BigDecimal docuOtrosTributos) {
		this.docuOtrosTributos = docuOtrosTributos;
	}

	public BigDecimal getDocuOtrosCargos() {
		return docuOtrosCargos;
	}

	public void setDocuOtrosCargos(BigDecimal docuOtrosCargos) {
		this.docuOtrosCargos = docuOtrosCargos;
	}

	public String getDocuVendedor() {
		return docuVendedor;
	}

	public void setDocuVendedor(String docuVendedor) {
		this.docuVendedor = docuVendedor;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public List<DetalleDocumento> getDetalleDocumentos() {
		return detalleDocumentos;
	}

	public void setDetalleDocumentos(List<DetalleDocumento> detalleDocumentos) {
		this.detalleDocumentos = detalleDocumentos;
	}

}
