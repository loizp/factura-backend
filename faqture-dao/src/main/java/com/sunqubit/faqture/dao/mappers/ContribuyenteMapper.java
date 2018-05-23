package com.sunqubit.faqture.dao.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.beans.core.Contribuyente;

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
	
	long docIdentidadExist(HashMap<String, String> hmFind);
	
	long contExist(long contId);
	
	long selectKey();
}
