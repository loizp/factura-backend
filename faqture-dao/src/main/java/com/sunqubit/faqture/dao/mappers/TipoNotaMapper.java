package com.sunqubit.faqture.dao.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoNota;

public interface TipoNotaMapper {
	List<TipoNota> getAll();
	
	long tinoExist(HashMap<String, Object> hmFind);
}
