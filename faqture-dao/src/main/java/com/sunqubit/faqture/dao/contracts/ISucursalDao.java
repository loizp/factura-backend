package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.Sucursal;

public interface ISucursalDao {
	long insert(Sucursal sucursal) throws Exception;
	
	void update(Sucursal sucursal) throws Exception;
	
	List<Sucursal> getList(long contId) throws Exception;
	
	Boolean sucuExist(long sucuId, long contId) throws Exception;
}
