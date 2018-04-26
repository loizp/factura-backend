package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.DetalleDocumento;

public interface IDetalleDocumentoDao {
	void insert(DetalleDocumento detalleDocumento) throws Exception;
	
	void update(DetalleDocumento detalleDocumento) throws Exception;
	
	void delete(DetalleDocumento detalleDocumento) throws Exception;
	
	List<DetalleDocumento> getDetalleDoc(long docId) throws Exception;
}
