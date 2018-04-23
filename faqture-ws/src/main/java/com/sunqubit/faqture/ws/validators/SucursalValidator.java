package com.sunqubit.faqture.ws.validators;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.validators.SucursalDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

public class SucursalValidator extends SucursalDaoValidator {
	
	@Override
	public void validaSucuId(long sucuId) throws ValidatorException {
		super.validaSucuId(sucuId);
	}
	
	@Override
	public void validaSucuEmpresa(Empresa sucuEmpresa) throws ValidatorException {
		super.validaSucuEmpresa(sucuEmpresa);
	}
	
	@Override
	public void validaSucuDireccion(String sucuDireccion) throws ValidatorException {
		super.validaSucuDireccion(sucuDireccion);
		
		if (!sucuDireccion.matches("^[\\w- ]+(\\.[\\w- ]+)*#[\\w- ]+(\\.[\\w- ]+)*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaSucuUnigeo(Ubigeo sucuUbigeo) throws ValidatorException {
		super.validaSucuUnigeo(sucuUbigeo);
	}
}
