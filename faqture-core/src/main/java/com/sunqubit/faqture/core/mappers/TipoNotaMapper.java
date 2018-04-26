package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.core.beans.TipoNota;

public interface TipoNotaMapper {
	List<TipoNota> getAll();
	
	Long tinoExist(HashMap<String, Object> hmFind);
}
