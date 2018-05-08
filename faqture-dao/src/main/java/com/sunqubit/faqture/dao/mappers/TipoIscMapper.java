package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoIsc;

public interface TipoIscMapper {
	List<TipoIsc> getAll();
	
	Long tipoIscExist(String tiscCodigo);
}
