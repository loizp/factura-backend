package com.sunqubit.faqture.core.beans;

public class RolUsuario {
	private Long id;
	private String roleName;
	
	public RolUsuario() {
	}
	public RolUsuario(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
