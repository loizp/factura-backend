package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoNota;

public interface ITipoNotaDao {
	List<TipoNota> getAll() throws Exception;
	
	Boolean tinoExist(String tidoCodigo, Long tinoId) throws Exception;
}
