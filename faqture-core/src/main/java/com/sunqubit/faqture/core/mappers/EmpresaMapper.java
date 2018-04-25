package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.core.beans.Empresa;

public interface EmpresaMapper {
	
	void insert(Empresa empresa);
	
	void update(Empresa empresa);
	
	void changeDoc(Empresa empresa);
	
	Empresa getById(long emprId);
	
	Empresa getSucursales(long emprId);
	
	Empresa getByDoc(HashMap<String, String> hmFind);
	
	List<Empresa> filterName(String nombre);
}
