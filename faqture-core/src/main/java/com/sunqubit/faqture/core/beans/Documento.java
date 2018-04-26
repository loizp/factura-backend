package com.sunqubit.faqture.core.beans;

import java.sql.Date;
import java.util.List;

public class Documento {
	private long id;
	private Date fechaEmision;
	private String numero;
	private String leyenda;
	private String observacion;
	private Date fechaProceso;
	private String estadoProceso;
	private TipoDocumento tipoDocumento;
	private Empresa empresa;
	private Sucursal emprSucursal;
	private TipoLeyenda tipoLeyenda;
	private Moneda moneda;
	private Boolean enviarSunat;
	private String linkPdf;
	private String linkXml;
	private String hashSunat;
	private String linkCdr;
	private String cdrStatus;
	private String cdrNota;
	private String cdrObservacion;
	private List<Documento> docsReferenciados;
	
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
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
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Sucursal getEmprSucursal() {
		return emprSucursal;
	}
	public void setEmprSucursal(Sucursal emprSucursal) {
		this.emprSucursal = emprSucursal;
	}
	public TipoLeyenda getTipoLeyenda() {
		return tipoLeyenda;
	}
	public void setTipoLeyenda(TipoLeyenda tipoLeyenda) {
		this.tipoLeyenda = tipoLeyenda;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
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
	public List<Documento> getDocsReferenciados() {
		return docsReferenciados;
	}
	public void setDocsReferenciados(List<Documento> docsReferenciados) {
		this.docsReferenciados = docsReferenciados;
	}
}
