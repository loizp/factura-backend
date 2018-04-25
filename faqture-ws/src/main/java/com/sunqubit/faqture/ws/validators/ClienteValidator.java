package com.sunqubit.faqture.ws.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.validators.ClienteDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

public class ClienteValidator extends ClienteDaoValidator {
	
	@Autowired
	private DocIdentidadValidator docIdentidadValidator;
	
	@Override
	public void validaClieId(long clieId) throws ValidatorException {
		super.validaClieId(clieId);
	}
	
	@Override
	public void validaClieNumero(String clieNumero, TipoDocumentoIdentidad clieTipoDocIdentidad)
			throws ValidatorException {
		super.validaClieNumero(clieNumero, clieTipoDocIdentidad);
		
		if (clieTipoDocIdentidad.getCodigo() != "0" && (clieNumero == null || clieNumero.trim().isEmpty()))
            throw new ValidatorException("Es necesario contener el atributo 'numero' del cliente");
		
		if (!docIdentidadValidator.docIdentidadValido(clieNumero, clieTipoDocIdentidad.getCodigo()))
            throw new ValidatorException("Es necesario contener el atributo 'numero' del cliente sea valido segun el tipo de documento");
	}
	
	@Override
	public void validaClieNombres(String clieNombres) throws ValidatorException {
		super.validaClieNombres(clieNombres);
		
		if (clieNombres.trim().isEmpty() || !clieNombres.matches("^[\\w- ]+(\\.[\\w- ]+)*$"))
			throw new ValidatorException("Es necesario que el atributo 'nombre' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaClieDireccion(String clieDireccion) throws ValidatorException {
		if(clieDireccion == null)
			throw new ValidatorException("Es necesario que el atributo 'direccion' del cliente");
			
		super.validaClieDireccion(clieDireccion);
		
		if (clieDireccion.trim().isEmpty() || !clieDireccion.matches("^[\\w- ]+(\\.[\\w- ]+)*#[\\w- ]+(\\.[\\w- ]+)*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
}
