package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Pais;
import com.sunqubit.faqture.core.daos.contracts.IPaisDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class PaisService {
	
	@Autowired
    private IPaisDao paisDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los paises";
		List<Pais> res = null;
		try {
			res = paisDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener los paises debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
