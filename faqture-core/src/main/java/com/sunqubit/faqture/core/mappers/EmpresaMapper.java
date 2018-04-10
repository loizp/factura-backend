package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.Empresa;

public interface EmpresaMapper {
	
	void insert(Empresa empresa);
	
	void update(Empresa empresa);
	
	Empresa getForId(long emprId);
	
	Empresa getForRuc(String emprRuc);
	
	List<Empresa> filterName(String nombre);
}
