package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.Sucursal;

public interface ISucursalDao {
	Long insert(Sucursal sucursal) throws Exception;
	
	void update(Sucursal sucursal) throws Exception;
	
	List<Sucursal> getList(Long contId) throws Exception;
	
	Boolean sucuExist(Long sucuId, Long contId) throws Exception;
}
