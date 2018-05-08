package com.sunqubit.faqture.dao.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoNota;

public interface TipoNotaMapper {
	List<TipoNota> getAll();
	
	Long tinoExist(HashMap<String, Object> hmFind);
}
