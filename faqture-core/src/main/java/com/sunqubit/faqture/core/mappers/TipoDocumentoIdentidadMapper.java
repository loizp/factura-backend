package com.sunqubit.faqture.core.mappers;

import java.util.List;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;

public interface TipoDocumentoIdentidadMapper {
	List<TipoDocumentoIdentidad> getAll();
	TipoDocumentoIdentidad get(String tiidCodigo);
}
