package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Pais;

public interface IPaisDao {
	List<Pais> getAll() throws Exception;
}
