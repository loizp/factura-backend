package com.sunqubit.faqture.service.catalogs;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.catalogs.TipoDocumentoIdentidad;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.ITipoDocumentoIdentidadDao;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TipoDocumentoIdentidadService {

    @Autowired
    private ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao;

    public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los tipos de documentos de Identidad";
		List<TipoDocumentoIdentidad> res = null;
		try {
			res = tipoDocumentoIdentidadDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener los tipos de documentos de identidad debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
