package com.sunqubit.faqture.beans.core;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Moneda;
import com.sunqubit.faqture.beans.catalogs.TipoDocumento;

public class Documento {

    private long id;
    private long idSysEmisor = 0;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CST")
    private Timestamp fechaEmision;
    private String numero;
    private String observacion;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CST")
    private Timestamp fechaProceso;
    private String estadoProceso = "N";
    private TipoDocumento tipoDocumento;
    private Contribuyente empresa;
    private Sucursal emprSucursal;
    private Moneda moneda;
    private Boolean enviarSunat = true;
    private BigDecimal tasaIgv = BigDecimal.valueOf(18.00);
    private String linkPdf;
    private String linkXml;
    private String hashSunat;
    private String linkCdr;
    private String cdrStatus;
    private String cdrNota;
    private String cdrObservacion;
    private List<Leyenda> leyendas;
    private List<Documento> docsReferenciados;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public Long getIdSysEmisor() {
		return idSysEmisor;
	}

	public void setIdSysEmisor(Long idSysEmisor) {
		this.idSysEmisor = idSysEmisor;
	}

	public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Timestamp getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Timestamp fechaProceso) {
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

    public Contribuyente getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Contribuyente empresa) {
        this.empresa = empresa;
    }

    public Sucursal getEmprSucursal() {
        return emprSucursal;
    }

    public void setEmprSucursal(Sucursal emprSucursal) {
        this.emprSucursal = emprSucursal;
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

    public List<Leyenda> getLeyendas() {
        return leyendas;
    }

    public void setLeyendas(List<Leyenda> leyendas) {
        this.leyendas = leyendas;
    }

    public BigDecimal getTasaIgv() {
        return tasaIgv;
    }

    public void setTasaIgv(BigDecimal tasaIgv) {
        this.tasaIgv = tasaIgv;
    }
}
