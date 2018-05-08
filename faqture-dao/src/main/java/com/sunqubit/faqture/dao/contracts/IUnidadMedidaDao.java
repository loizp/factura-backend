package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.UnidadMedida;

public interface IUnidadMedidaDao {
	List<UnidadMedida> getAll() throws Exception;
}
