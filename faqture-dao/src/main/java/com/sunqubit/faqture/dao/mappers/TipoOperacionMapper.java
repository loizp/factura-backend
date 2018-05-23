package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoOperacion;

public interface TipoOperacionMapper {
	List<TipoOperacion> getAll();
	
	long tiopExist(String tiopCodigo);
}
