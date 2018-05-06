package com.sunqubit.faqture.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IContribuyenteDao;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;

@Component
public class ContribuyenteDaoValidator {
	
	@Autowired
    private IContribuyenteDao contribuyenteDao;
	
	@Autowired
	private IUbigeoDao ubigeoDao;
	
	public void validaContId(long contId) throws ValidatorException{
		if (contId < 1)
            throw new ValidatorException("Es necesario contener el atributo 'id' del contribuyente");
	}
	
	public void validaContDoc(String contDoc, TipoDocumentoIdentidad contTipoDocIdentidad) throws ValidatorException{
		validaContTipoDocIdentidad(contTipoDocIdentidad);
		
		if (contDoc.length() > 15)
            throw new ValidatorException("la longitud del atributo 'numeroDocumento' no debe exceder los 15 caracteres");
		
		try {
    		if (contribuyenteDao.docIdentidadExist(contDoc, contTipoDocIdentidad.getCodigo())) {
                throw new ValidatorException("El numero de documento ya existe registrado");
            }
    	} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaContNombreLegal(String contNombreLegal) throws ValidatorException{
		if (contNombreLegal == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombreLegal' del contribuyente");
		
		if (contNombreLegal.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombreLegal' no debe exceder los 200 caracteres");
	}
	
	public void validaContNombreComercial(String contNombreComercial) throws ValidatorException{		
		if (contNombreComercial.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombreComercial' no debe exceder los 200 caracteres");
	}
	
	public void validaContDireccion(String contDireccion) throws ValidatorException{		
		if (contDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaContUrbanizacion(String contUrbanizacion) throws ValidatorException{		
		if (contUrbanizacion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'urbanizacion' no debe exceder los 200 caracteres");
	}
	
	public void validaContUnigeo(Ubigeo contUbigeo) throws ValidatorException{
		if (contUbigeo == null || contUbigeo.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'ubigeo' del contribuyente");
		
		try {
			if (!ubigeoDao.ubigeoExist(contUbigeo.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'ubigeo' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaContTipoDocIdentidad(TipoDocumentoIdentidad contTipoDocIdentidad) throws ValidatorException{
		if (contTipoDocIdentidad == null || contTipoDocIdentidad.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoDocumentoIdentidad' del contribuyente");
		
		if (contTipoDocIdentidad.getCodigo().length() > 2)
            throw new ValidatorException("la longitud del atributo 'tipoDocumentoIdentidad' no debe exceder los dos caracteres");
	}
	
}
