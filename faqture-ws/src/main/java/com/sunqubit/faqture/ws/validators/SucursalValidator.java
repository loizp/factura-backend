package com.sunqubit.faqture.ws.validators;

import com.sunqubit.faqture.core.validators.SucursalDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

public class SucursalValidator extends SucursalDaoValidator {
	
	@Override
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException {
		super.validaSucuDireccion(sucuDireccion);
		
		if (!sucuDireccion.matches("^[\\w- ]+(\\.[\\w- ]+)*#[\\w- ]+(\\.[\\w- ]+)*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
}
