package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;

public interface ITipoDocumentoIdentidadDao {

    List<TipoDocumentoIdentidad> getAll();
}
