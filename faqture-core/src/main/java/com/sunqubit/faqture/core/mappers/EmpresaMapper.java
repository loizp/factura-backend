package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.core.beans.Empresa;

public interface EmpresaMapper {
	
	void insert(Empresa empresa);
	
	void update(Empresa empresa);
	
	void changeRuc(Empresa empresa);
	
	Empresa getById(long emprId);
	
	Empresa getSucursales(long emprId);
	
	Empresa getByRuc(HashMap<String, String> hmFind);
	
	Long rucExist(HashMap<String, String> hmFind);
	
	List<Empresa> filterName(String nombre);
}
