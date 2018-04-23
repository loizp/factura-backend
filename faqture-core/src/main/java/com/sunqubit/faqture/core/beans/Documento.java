package com.sunqubit.faqture.core.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Documento {

	private long id;
	private Empresa empresa;
	private Sucursal sucursal;
	private Cliente cliente;
	private Date fecha;
	private String numero;
	private Moneda moneda;
	private TipoDocumento tipoDocumento;
	private BigDecimal grabada;
	private BigDecimal inafecta;
	private BigDecimal exonerada;
	private BigDecimal gratuita;
	private BigDecimal descuento;
	private BigDecimal subtotal;
	private BigDecimal total;
	private BigDecimal igv;
	private BigDecimal isc;
	private BigDecimal tributos;
	private BigDecimal otrosCargos;
	private String vendedor;
	private TipoOperacion tipoOperacion;
	private List<DetalleDocumento> detallesDocumento;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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
	public BigDecimal getTributos() {
		return tributos;
	}
	public void setTributos(BigDecimal tributos) {
		this.tributos = tributos;
	}
	public BigDecimal getOtrosCargos() {
		return otrosCargos;
	}
	public void setOtrosCargos(BigDecimal otrosCargos) {
		this.otrosCargos = otrosCargos;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public List<DetalleDocumento> getDetallesDocumento() {
		return detallesDocumento;
	}
	public void setDetallesDocumento(List<DetalleDocumento> detallesDocumento) {
		this.detallesDocumento = detallesDocumento;
	}

}
