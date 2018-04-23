package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.Moneda;

public interface IMonedaDao {
	List<Moneda> getAll() throws Exception;
}
