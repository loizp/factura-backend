package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoAfectacionIgv;

public interface TipoAfectacionIgvMapper {
	List<TipoAfectacionIgv> getAll();
	
	Long tipoIgvExist(String tiaiCodigo);
}
