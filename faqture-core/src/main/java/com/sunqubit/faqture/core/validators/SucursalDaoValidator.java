package com.sunqubit.faqture.core.validators;

import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.Ubigeo;

public class SucursalDaoValidator {
	
	public void validaSucuId(long sucuId) throws ValidatorException{
		if (Long.valueOf(sucuId) == null)
            throw new ValidatorException("Es necesario contener el atributo 'id' de la sucursal");
	}
	
	public void validaSucuContribuyente(Contribuyente sucuContribuyente) throws ValidatorException{
		if (sucuContribuyente == null || Long.valueOf(sucuContribuyente.getId()) == null)
            throw new ValidatorException("Es necesario contener el atributo 'contribuyente' de la sucursal");
	}
	
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException{
		if (sucuDireccion == null)
            throw new ValidatorException("Es necesario contener el atributo 'direccion' de la sucursal");
		
		if (sucuDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaSucuUnigeo(Ubigeo sucuUbigeo) throws ValidatorException{
		if (sucuUbigeo == null || Long.valueOf(sucuUbigeo.getId()) == null)
            throw new ValidatorException("Es necesario contener el atributo 'ubigeo' de la sucursal");
	}
}
