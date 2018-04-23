package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;
import com.sunqubit.faqture.core.beans.Documento;
import com.sunqubit.faqture.core.beans.Empresa;

public interface IDocumentoDao {
	
	void insert(Documento documento) throws Exception;
	
	void insertDetails(Documento documento) throws Exception;
	
	void update(Documento documento) throws Exception;
	
	Documento get(long docuId) throws Exception;
	
	List<Documento> getList(Empresa empresa) throws Exception;
}
