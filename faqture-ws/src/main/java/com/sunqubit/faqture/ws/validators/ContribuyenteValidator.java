package com.sunqubit.faqture.ws.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.validators.ContribuyenteDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

@Component
public class ContribuyenteValidator extends ContribuyenteDaoValidator {
	
	@Override
	public void validaContNombreLegal(String contNombreLegal) throws ValidatorException{
		super.validaContNombreLegal(contNombreLegal);
		
		if (contNombreLegal.trim().isEmpty() || !contNombreLegal.matches("^[A-Za-z][\\w- \\. \\#]*$"))
			throw new ValidatorException("Es necesario que el atributo 'nombreLegal' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaContNombreComercial(String contNombreComercial) throws ValidatorException{
		if (contNombreComercial == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombreComercial' del contribuyente");
		
		super.validaContNombreComercial(contNombreComercial);
		
		if (contNombreComercial.trim().isEmpty() || !contNombreComercial.matches("^[A-Za-z][\\w- \\. \\#]*$"))
			throw new ValidatorException("Es necesario que el atributo 'nombreComercial' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaContDireccion(String contDireccion) throws ValidatorException {
		if(contDireccion == null)
			throw new ValidatorException("Es necesario que el atributo 'direccion' del contribuyente");
			
		super.validaContDireccion(contDireccion);
		
		if (contDireccion.trim().isEmpty() || !contDireccion.matches("^[A-Za-z][\\w- \\. \\#]*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaContUrbanizacion(String contUrbanizacion) throws ValidatorException{
		if (contUrbanizacion == null)
            throw new ValidatorException("Es necesario contener el atributo 'urbanizacion' del contribuyente");
		
		super.validaContUrbanizacion(contUrbanizacion);
		
		if (contUrbanizacion.trim().isEmpty() || !contUrbanizacion.matches("^[A-Za-z][\\w- \\. \\#]*$"))
            throw new ValidatorException("Es necesario que el atributo 'urbanizacion' debe estar correctamente expresado");
	}
}
