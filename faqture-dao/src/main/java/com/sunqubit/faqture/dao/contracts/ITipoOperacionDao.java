package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoOperacion;

public interface ITipoOperacionDao {
	List<TipoOperacion> getAll() throws Exception;
	
	Boolean tiopExist(String tiopCodigo) throws Exception;
}
