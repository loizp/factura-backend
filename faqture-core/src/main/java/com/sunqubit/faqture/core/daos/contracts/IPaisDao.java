package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.Pais;

public interface IPaisDao {
	List<Pais> getAll() throws Exception;
}
