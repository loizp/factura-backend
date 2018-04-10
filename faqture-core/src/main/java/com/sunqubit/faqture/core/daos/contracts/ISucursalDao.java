package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.beans.Sucursal;

public interface ISucursalDao {
	void insert(Sucursal sucursal);
	
	List<Sucursal> getList(Empresa empresa);
}
