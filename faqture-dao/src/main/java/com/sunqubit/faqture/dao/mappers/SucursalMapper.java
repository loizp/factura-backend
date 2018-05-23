package com.sunqubit.faqture.dao.mappers;

import java.util.HashMap;
import java.util.List;

import com.sunqubit.faqture.beans.core.Sucursal;

public interface SucursalMapper {
	void insert(Sucursal sucursal);
	
	void update(Sucursal sucursal);
	
	long sucuExist(HashMap<String, Object> hmFind);
	
	List<Sucursal> getList(long contId);
	
	Sucursal getExist(Sucursal sucursal);
	
	long selectKey();
}
