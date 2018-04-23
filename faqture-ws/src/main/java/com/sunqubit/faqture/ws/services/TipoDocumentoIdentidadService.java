package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

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
			code = 400;
			msg = "No se puede obtener los tipos de documentos de identidad debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
