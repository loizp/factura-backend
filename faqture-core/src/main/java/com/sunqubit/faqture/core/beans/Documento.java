package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Documento {

	private long id;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String numero;	
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
	private String observacion;
	private String sustentoNota;
	private Date fechaProceso;
	private String estadoProceso;
	private Boolean anulado;
	private TipoDocumento tipoDocumento;
	private TipoNota tipoNota;
	private Empresa empresa;
	private Sucursal sucursal;
	private Cliente cliente;
	private Moneda moneda;
	private TipoOperacion tipoOperacion;
	private Boolean enviarSunat;
	private String linkPdf;
	private String linkXml;
	private String hashSunat;
	private String linkCdr;
	private String cdrStatus;
	private String cdrNota;
	private String cdrObservacion;
	private List<DetalleDocumento> detallesDocumento;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getSustentoNota() {
		return sustentoNota;
	}
	public void setSustentoNota(String sustentoNota) {
		this.sustentoNota = sustentoNota;
	}
	public Date getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public Boolean getAnulado() {
		return anulado;
	}
	public void setAnulado(Boolean anulado) {
		this.anulado = anulado;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public TipoNota getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Boolean getEnviarSunat() {
		return enviarSunat;
	}
	public void setEnviarSunat(Boolean enviarSunat) {
		this.enviarSunat = enviarSunat;
	}
	public String getLinkPdf() {
		return linkPdf;
	}
	public void setLinkPdf(String linkPdf) {
		this.linkPdf = linkPdf;
	}
	public String getLinkXml() {
		return linkXml;
	}
	public void setLinkXml(String linkXml) {
		this.linkXml = linkXml;
	}
	public String getHashSunat() {
		return hashSunat;
	}
	public void setHashSunat(String hashSunat) {
		this.hashSunat = hashSunat;
	}
	public String getLinkCdr() {
		return linkCdr;
	}
	public void setLinkCdr(String linkCdr) {
		this.linkCdr = linkCdr;
	}
	public String getCdrStatus() {
		return cdrStatus;
	}
	public void setCdrStatus(String cdrStatus) {
		this.cdrStatus = cdrStatus;
	}
	public String getCdrNota() {
		return cdrNota;
	}
	public void setCdrNota(String cdrNota) {
		this.cdrNota = cdrNota;
	}
	public String getCdrObservacion() {
		return cdrObservacion;
	}
	public void setCdrObservacion(String cdrObservacion) {
		this.cdrObservacion = cdrObservacion;
	}
	public List<DetalleDocumento> getDetallesDocumento() {
		return detallesDocumento;
	}
	public void setDetallesDocumento(List<DetalleDocumento> detallesDocumento) {
		this.detallesDocumento = detallesDocumento;
	}
}
