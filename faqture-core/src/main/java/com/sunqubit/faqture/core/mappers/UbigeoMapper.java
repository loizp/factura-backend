package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.Ubigeo;

public interface UbigeoMapper {
	List<Ubigeo> filter(String filtro);
	Ubigeo get(long ubigId);
}