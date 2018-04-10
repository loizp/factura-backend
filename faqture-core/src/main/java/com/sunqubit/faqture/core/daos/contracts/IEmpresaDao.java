package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Empresa;
import java.util.List;

public interface IEmpresaDao {
	
	void insert(Empresa empresa);
	
	void update(Empresa empresa);
	
	Empresa get(long emprId);
	
	Empresa get(String emprRuc);
	
	List<Empresa> filterName(String nombre);
}
