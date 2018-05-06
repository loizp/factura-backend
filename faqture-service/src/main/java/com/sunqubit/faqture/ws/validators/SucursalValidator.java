package com.sunqubit.faqture.ws.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.validators.SucursalDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

@Component
public class SucursalValidator extends SucursalDaoValidator {
	
	@Override
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException {
		super.validaSucuDireccion(sucuDireccion);
		
		if (!sucuDireccion.matches("^[A-Za-z][\\w- \\. \\#]*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
}
