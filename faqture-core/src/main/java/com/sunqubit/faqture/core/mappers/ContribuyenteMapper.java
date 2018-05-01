package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.core.beans.Contribuyente;

public interface ContribuyenteMapper {
	void insertE(Contribuyente contribuyente);
	
	void updateE(Contribuyente contribuyente);
	
	void insertC(Contribuyente contribuyente);
	
	void updateC(Contribuyente contribuyente);
	
	void changeDoc(Contribuyente contribuyente);
	
	Contribuyente getVerif(long contId);
	
	Contribuyente getById(long contId);
	
	Contribuyente getByDoc(HashMap<String, String> hmFind);
	
	List<Contribuyente> filterName(String nombre);
	
	Long docIdentidadExist(HashMap<String, String> hmFind);
	
	Long contExist(Long contId);
}
