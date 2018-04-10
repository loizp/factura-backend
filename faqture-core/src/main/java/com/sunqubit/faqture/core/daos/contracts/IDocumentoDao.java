package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;
import com.sunqubit.faqture.core.beans.Documento;
import com.sunqubit.faqture.core.beans.Empresa;

public interface IDocumentoDao {
	
	void insert(Documento documento);
	
	void insertDetails(Documento documento);
	
	void update(Documento documento);
	
	Documento get(long docuId);
	
	List<Documento> getList(Empresa empresa);
}
