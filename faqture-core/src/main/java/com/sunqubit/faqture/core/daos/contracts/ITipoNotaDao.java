package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoNota;

public interface ITipoNotaDao {
	List<TipoNota> getAll() throws Exception;
	
	Boolean tinoExist(String tidoCodigo, Long tinoId) throws Exception;
}
