package com.sunqubit.faqture.service.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.Ubigeo;
import com.sunqubit.faqture.dao.validators.SucursalDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class SucursalValidator extends SucursalDaoValidator {
	
	@Override
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException {
		super.validaSucuDireccion(sucuDireccion);
		
		if (!sucuDireccion.matches("^[\\w-.#\\(\\)/ ]*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaSucuUrbanizacion(String sucuUrbanizacion) throws ValidatorException {
		if (sucuUrbanizacion == null)
            throw new ValidatorException("Es necesario contener el atributo 'urbanizacion' de la sucursal");
		
		super.validaSucuUrbanizacion(sucuUrbanizacion);
		
		if (!sucuUrbanizacion.matches("^[\\w-.#\\(\\)/ ]*$"))
            throw new ValidatorException("Es necesario que el atributo 'urbanizacion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaSucuUnigeo(Ubigeo sucuUbigeo) throws ValidatorException {
		if (sucuUbigeo == null || sucuUbigeo.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'ubigeo' de la sucursal");
		
		super.validaSucuUnigeo(sucuUbigeo);
	}
}
