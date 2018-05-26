package com.sunqubit.faqture.beans.core;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Usuario {
	private long id;
	private String loginName;
	private String password;
	private String nombre;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="CST")
	private Timestamp dateUpKey;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="CST")
	private Timestamp dateLogin;
	private String email;
	private Boolean activo = true;
	private Contribuyente empresa;
	private Boolean soloSucursales = false;
	private List<RolUsuario> roles;
	private List<Sucursal> sucursales;
	

	public Boolean getSoloSucursales() {
		return soloSucursales;
	}
	public void setSoloSucursales(Boolean soloSucursales) {
		this.soloSucursales = soloSucursales;
	}
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public List<RolUsuario> getRoles() {
		return roles;
	}
	public void setRoles(List<RolUsuario> roles) {
		this.roles = roles;
	}
	public Timestamp getDateUpKey() {
		return dateUpKey;
	}
	public void setDateUpKey(Timestamp dateUpKey) {
		this.dateUpKey = dateUpKey;
	}
	public Timestamp getDateLogin() {
		return dateLogin;
	}
	public void setDateLogin(Timestamp dateLogin) {
		this.dateLogin = dateLogin;
	}
	public Contribuyente getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Contribuyente empresa) {
		this.empresa = empresa;
	}
}
