package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Documento;

public interface IDocumentoDao {
	
	void insert(Documento documento) throws Exception;
	
	void update(Documento documento) throws Exception;
	
	Documento get(long docuId) throws Exception;
}
