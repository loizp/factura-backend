package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;

public interface ITipoLeyendaDao {
	List<TipoLeyenda> getAll() throws Exception;
	
	Boolean tleyExist(String tleyCodigo) throws Exception;
}
