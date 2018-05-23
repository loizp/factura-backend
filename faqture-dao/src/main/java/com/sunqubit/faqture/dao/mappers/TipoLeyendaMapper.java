package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;

public interface TipoLeyendaMapper {
	List<TipoLeyenda> getAll();
	
	long tipoLeyendaExist(String tleyCodigo);
}
