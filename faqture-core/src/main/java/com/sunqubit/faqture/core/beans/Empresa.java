package com.sunqubit.faqture.core.beans;

import java.util.List;

public class Empresa extends Contribuyente {
	private List<Documento> documentos;
	
	public List<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
}
