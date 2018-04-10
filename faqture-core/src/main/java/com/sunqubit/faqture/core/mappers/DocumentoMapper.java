package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.Documento;
import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.beans.Cliente;
import com.sunqubit.faqture.core.beans.DetalleDocumento;

import java.util.List;

public interface DocumentoMapper {
	void insert(Documento documento);
	void update(Documento documento);
	List<Documento> getListE(Empresa empresa);
	Documento get(long docuId);
}
