package com.sunqubit.faqture.core.beans;

import java.util.List;

public class Usuario {
	private long id;
	private String loginName;
	private String password;
	private String nombre;
	private String email;
	private Boolean activo;
	private List<RolUsuario> roles;
	
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
}