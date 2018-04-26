package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;

public interface ContribuyenteMapper {
	Long docIdentidadExist(HashMap<String, String> hmFind);
	
	Long contExist(Long contId);
}
