package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoAfectacionIgv;

public interface ITipoAfectacionIgvDao {
	List<TipoAfectacionIgv> getAll() throws Exception;
}
