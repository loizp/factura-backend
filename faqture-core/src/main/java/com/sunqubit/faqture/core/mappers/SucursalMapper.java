package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.beans.Sucursal;

public interface SucursalMapper {
	void insert(Sucursal sucursal);
	
	List<Sucursal> getList(Empresa empresa);
}