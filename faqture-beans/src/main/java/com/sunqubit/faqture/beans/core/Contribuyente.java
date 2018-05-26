package com.sunqubit.faqture.beans.core;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Pais;
import com.sunqubit.faqture.beans.catalogs.TipoDocumentoIdentidad;
import com.sunqubit.faqture.beans.catalogs.Ubigeo;

public class Contribuyente {
	private long id;
	private String numeroDocumento;
	private String nombreLegal;
	private String nombreComercial;
	private String direccion;
	private String urbanizacion;
	private String email;
	private String telefono;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private Ubigeo ubigeo;
	private Pais pais;
	private List<Sucursal> sucursales;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombreLegal() {
		return nombreLegal;
	}
	public void setNombreLegal(String nombreLegal) {
		this.nombreLegal = nombreLegal;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUrbanizacion() {
		return urbanizacion;
	}
	public void setUrbanizacion(String urbanizacion) {
		this.urbanizacion = urbanizacion;
	}
	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}
	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		if(pais == null) {
			pais = new Pais();
			pais.setCodigo("PE");
		}
		this.pais = pais;
	}
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
