package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.Moneda;

public interface IMonedaDao {
	List<Moneda> getAll() throws Exception;
}
