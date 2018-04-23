package com.sunqubit.faqture.core.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.daos.contracts.IClienteDao;

public class ClienteDaoValidator {
	
	@Autowired
    private IClienteDao clienteDao;
	
	public void validaClieId(long clieId) throws ValidatorException{
		if (Long.valueOf(clieId) == null)
            throw new ValidatorException("Es necesario contener el atributo 'id' del cliente");
	}
	
	public void validaClieNumero(String clieNumero, TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException{
		validaClieTipoDocIdentidad(emprTipoDocIdentidad);
		
		if (clieNumero.length() > 15)
            throw new ValidatorException("la longitud del atributo 'numero' no debe exceder los 15 caracteres");
		
		try {
			if (emprTipoDocIdentidad.getCodigo() != "0")
	    		if (clienteDao.docIdentidadExist(clieNumero, emprTipoDocIdentidad.getCodigo()))
	    			throw new ValidatorException("El numero de documento ya existe registrado");
		} catch (Exception ex) {
			throw new ValidatorException(ex.getMessage());
		}
	}
	
	public void validaClieNombre(String clieNombre) throws ValidatorException{
		if (clieNombre == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombre' del cliente");
		
		if (clieNombre.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombre' no debe exceder los 200 caracteres");
	}
	
	public void validaClieDireccion(String clieDireccion) throws ValidatorException{		
		if (clieDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaClieTipoDocIdentidad(TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException{
		if (emprTipoDocIdentidad == null || emprTipoDocIdentidad.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoDocumentoIdentidad' del cliente");
	}
}
