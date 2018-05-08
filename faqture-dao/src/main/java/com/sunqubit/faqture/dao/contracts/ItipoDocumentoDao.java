package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoDocumento;

public interface ItipoDocumentoDao {
	List<TipoDocumento> getAll() throws Exception;
}
