package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Ubigeo;

public interface IUbigeoDao {
	List<Ubigeo> filter(String filtro) throws Exception;
	
	Ubigeo get(String codigo) throws Exception;
	
	Boolean ubigeoExist(Long ubigId) throws Exception;
}
