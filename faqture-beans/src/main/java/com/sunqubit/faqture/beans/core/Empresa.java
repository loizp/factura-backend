package com.sunqubit.faqture.beans.core;

public class Empresa extends Contribuyente {
	private boolean activo = true;
	private String keystoreType = "JKS";
	private String keystoreFile;
	private String keystorePass;
	private String privateKeyAlias;
	private String privateKeyPass;
	private String certificateAlias;
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getKeystoreType() {
		return keystoreType;
	}
	public void setKeystoreType(String keystoreType) {
		this.keystoreType = keystoreType;
	}
	public String getKeystoreFile() {
		return keystoreFile;
	}
	public void setKeystoreFile(String keystoreFile) {
		this.keystoreFile = keystoreFile;
	}
	public String getKeystorePass() {
		return keystorePass;
	}
	public void setKeystorePass(String keystorePass) {
		this.keystorePass = keystorePass;
	}
	public String getPrivateKeyAlias() {
		return privateKeyAlias;
	}
	public void setPrivateKeyAlias(String privateKeyAlias) {
		this.privateKeyAlias = privateKeyAlias;
	}
	public String getPrivateKeyPass() {
		return privateKeyPass;
	}
	public void setPrivateKeyPass(String privateKeyPass) {
		this.privateKeyPass = privateKeyPass;
	}
	public String getCertificateAlias() {
		return certificateAlias;
	}
	public void setCertificateAlias(String certificateAlias) {
		this.certificateAlias = certificateAlias;
	}
}
