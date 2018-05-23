package com.sunqubit.faqture.dao.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.catalogs.Ubigeo;
import com.sunqubit.faqture.dao.contracts.IUbigeoDao;

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
	
	public void validaSucuNombreLegal(String sucuNombreLegal) throws ValidatorException{
		if (sucuNombreLegal == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombreLegal' de la sucursal");
		
		if (sucuNombreLegal.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombreLegal' no debe exceder los 200 caracteres");
	}
	
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException{
		if (sucuDireccion == null)
            throw new ValidatorException("Es necesario contener el atributo 'direccion' de la sucursal");
		
		if (sucuDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaSucuUnigeo(Ubigeo sucuUbigeo) throws ValidatorException{
		try {
			if (!ubigeoDao.ubigeoExist(sucuUbigeo.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'ubigeo' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaSucuUrbanizacion(String sucuUrbanizacion) throws ValidatorException{		
		if (sucuUrbanizacion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'urbanizacion' no debe exceder los 200 caracteres");
	}
}
