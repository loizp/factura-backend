package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.UnidadMedida;

public interface IUnidadMedidaDao {
	List<UnidadMedida> getAll() throws Exception;
}
