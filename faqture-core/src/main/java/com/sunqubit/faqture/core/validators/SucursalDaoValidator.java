package com.sunqubit.faqture.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;

@Component
public class SucursalDaoValidator {
	
	@Autowired
	private IUbigeoDao ubigeoDao;
	
	public void validaSucuId(long sucuId) throws ValidatorException{
		if (sucuId < 1)
            throw new ValidatorException("Es necesario contener el atributo 'id' de la sucursal");
	}
	
	public void validaSucuContribuyente(Contribuyente sucuContribuyente) throws ValidatorException{
		if (sucuContribuyente == null || sucuContribuyente.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'contribuyente' de la sucursal");
	}
	
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException{
		if (sucuDireccion == null)
            throw new ValidatorException("Es necesario contener el atributo 'direccion' de la sucursal");
		
		if (sucuDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaSucuUnigeo(Ubigeo sucuUbigeo) throws ValidatorException{
		if (sucuUbigeo == null || sucuUbigeo.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'ubigeo' de la sucursal");
		
		try {
			if (!ubigeoDao.ubigeoExist(sucuUbigeo.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'ubigeo' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
}
