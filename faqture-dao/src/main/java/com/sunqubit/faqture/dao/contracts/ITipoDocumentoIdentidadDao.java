package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.catalogs.TipoDocumentoIdentidad;

public interface ITipoDocumentoIdentidadDao {
    List<TipoDocumentoIdentidad> getAll() throws Exception;
}
