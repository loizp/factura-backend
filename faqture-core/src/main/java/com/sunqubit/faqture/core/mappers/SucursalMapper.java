package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;

import com.sunqubit.faqture.core.beans.Sucursal;

public interface SucursalMapper {
	void insert(Sucursal sucursal);
	
	void update(Sucursal sucursal);
	
	Long sucuExist(HashMap<String, Object> hmFind);
}
