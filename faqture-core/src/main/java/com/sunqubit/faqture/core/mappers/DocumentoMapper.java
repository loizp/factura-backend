package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.Documento;

public interface DocumentoMapper {
	void insert(Documento documento);
	
	void update(Documento documento);
	
	Documento get(long docuId);
}
