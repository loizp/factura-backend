package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoOperacion;

public interface TipoOperacionMapper {
	List<TipoOperacion> getAll();
	
	Long tiopExist(String tiopCodigo);
}
