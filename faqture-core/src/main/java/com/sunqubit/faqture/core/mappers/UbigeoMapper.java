package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.Ubigeo;

public interface UbigeoMapper {
	List<Ubigeo> filter(String filtro);
	
	Long ubigeoExist(Long ubigId);
}
