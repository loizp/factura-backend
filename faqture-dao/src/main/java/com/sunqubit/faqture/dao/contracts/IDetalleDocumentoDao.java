package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.DetalleDocumento;

public interface IDetalleDocumentoDao {
	void insert(DetalleDocumento detalleDocumento) throws Exception;
	
	void update(DetalleDocumento detalleDocumento) throws Exception;
	
	void delete(DetalleDocumento detalleDocumento) throws Exception;
	
	List<DetalleDocumento> getDetalleDoc(Long docId) throws Exception;
}
