package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoOperacion;

public interface ITipoOperacionDao {
	List<TipoOperacion> getAll() throws Exception;
}
