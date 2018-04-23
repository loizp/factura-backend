package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoIsc;

public interface ITipoIscDao {
	List<TipoIsc> getAll() throws Exception;
}
