package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.DetalleDocumento;

public interface DetalleDocumentoMapper {
	void insert(DetalleDocumento detalleDocumento);
	
	void update(DetalleDocumento detalleDocumento);
	
	void delete(DetalleDocumento detalleDocumento);
}
