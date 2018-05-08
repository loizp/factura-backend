package com.sunqubit.faqture.dao.mappers;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Ubigeo;

public interface UbigeoMapper {
	List<Ubigeo> filter(String filtro);
	
	Long ubigeoExist(Long ubigId);
}
