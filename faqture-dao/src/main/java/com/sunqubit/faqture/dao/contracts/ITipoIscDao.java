package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoIsc;

public interface ITipoIscDao {
	List<TipoIsc> getAll() throws Exception;
	
	Boolean tiscExist(String tiscCodigo) throws Exception;
}
