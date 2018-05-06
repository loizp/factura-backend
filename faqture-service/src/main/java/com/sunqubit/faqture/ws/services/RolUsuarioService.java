package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.RolUsuario;
import com.sunqubit.faqture.core.daos.contracts.IRolUsuarioDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class RolUsuarioService {

	@Autowired
	private IRolUsuarioDao rolUsuarioDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los roles del sistema";
		List<RolUsuario> res = null;
		try {
			res = rolUsuarioDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener los roles del sistema debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
	
}
