package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.UnidadMedida;
import com.sunqubit.faqture.core.daos.contracts.IUnidadMedidaDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class UnidadMedidaService {
	
	@Autowired
    private IUnidadMedidaDao unidadMedidaDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de las unidades de medida";
		List<UnidadMedida> res = null;
		try {
			res = unidadMedidaDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener las unidades de medida debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
