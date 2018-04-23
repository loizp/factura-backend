package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.Ubigeo;

public interface IUbigeoDao {
	List<Ubigeo> filter(String filtro) throws Exception;
}
