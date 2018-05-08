package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.core.DetalleDocumento;


public interface DetalleDocumentoMapper {
	void insert(DetalleDocumento detalleDocumento);
	
	void update(DetalleDocumento detalleDocumento);
	
	void delete(DetalleDocumento detalleDocumento);
	
	List<DetalleDocumento> getDetalleDoc(long docId);
}
