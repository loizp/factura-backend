package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Sucursal;

public interface ISucursalDao {
	void insert(Sucursal sucursal) throws Exception;
	
	void update(Sucursal sucursal) throws Exception;
}
