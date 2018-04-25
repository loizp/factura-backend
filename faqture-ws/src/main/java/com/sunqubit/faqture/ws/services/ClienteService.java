package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Cliente;
import com.sunqubit.faqture.core.daos.contracts.IClienteDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.ClienteValidator;

@Service
public class ClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	public ApiRestFullResponse insert(Cliente cliente) {
		Boolean ok = true;
		int code = 201;
		String msg = "El cliente fue registrado correctamente";
		
		try {
			clienteValidator.validaClieNombres(cliente.getNombres());
			clienteValidator.validaClieNumero(cliente.getNumero(), cliente.getTipoDocumentoIdentidad());
			if(cliente.getDireccion() != null || cliente.getTipoDocumentoIdentidad().getCodigo() == "6")
				clienteValidator.validaClieDireccion(cliente.getDireccion());
			clienteDao.insert(cliente);
		} catch (ValidatorException ve) {
			ok = false;
			code = 400;
			msg = "No se puede registrar el cliente debido a: " + ve.getMessage();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede registrar el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
	
	public ApiRestFullResponse update(Cliente cliente) {
		Boolean ok = true;
		int code = 201;
		String msg = "El cliente fue modificado correctamente";
		
		try {
			clienteValidator.validaClieId(cliente.getId());
			clienteValidator.validaClieNombres(cliente.getNombres());
			clienteValidator.validaClieNumero(cliente.getNumero(), cliente.getTipoDocumentoIdentidad());
			if(cliente.getDireccion() != null || cliente.getTipoDocumentoIdentidad().getCodigo() == "6")
				clienteValidator.validaClieDireccion(cliente.getDireccion());
			clienteDao.update(cliente);
		} catch (ValidatorException ve) {
			ok = false;
			code = 400;
			msg = "No se puede modificar el cliente debido a: " + ve.getMessage();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede registrar el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
	
	public ApiRestFullResponse get(long clieId) {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega del cliente solicitado";
		Cliente res = null;
		try {
			res = clienteDao.get(clieId);
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
	
	public ApiRestFullResponse get(String numDoc, String TipoDoc) {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega del cliente solicitado";
		Cliente res = null;
		try {
			res = clienteDao.get(numDoc, TipoDoc);
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
	
	public ApiRestFullResponse getFilter(String filtro) {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega dell cliente solicitado";
		List<Cliente> res = null;
		try {
			res = clienteDao.getFilter(filtro);
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtenerla lista de clientes debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
}
