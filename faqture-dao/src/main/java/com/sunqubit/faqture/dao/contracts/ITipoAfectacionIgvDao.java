package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoAfectacionIgv;

public interface ITipoAfectacionIgvDao {
	List<TipoAfectacionIgv> getAll() throws Exception;
	
	Boolean tiaiExist(String tiaiCodigo) throws Exception;
}
