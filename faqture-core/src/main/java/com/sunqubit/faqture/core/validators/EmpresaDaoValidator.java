package com.sunqubit.faqture.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IEmpresaDao;

@Component
public class EmpresaDaoValidator {
	
	@Autowired
    private IEmpresaDao empresaDao;
	
	public void validaEmprId(long emprId) throws ValidatorException{
		if (Long.valueOf(emprId) == null)
            throw new ValidatorException("Es necesario contener el atributo 'id' de la empresa");
	}
	
	public void validaEmprRuc(String emprRuc, TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException{
		validaEmprTipoDocIdentidad(emprTipoDocIdentidad);
		
		if (emprRuc == null)
            throw new ValidatorException("Es necesario contener el atributo 'ruc' de la empresa");
		
		if (emprRuc.length() > 11)
            throw new ValidatorException("la longitud del atributo 'ruc' no debe exceder los 11 caracteres");
		
		try {
    		if (empresaDao.rucExist(emprRuc, emprTipoDocIdentidad.getCodigo())) {
                throw new ValidatorException("El numero de RUC ya existe registrado");
            }
    	} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaEmprRazonSocial(String emprRazonSocial) throws ValidatorException{
		if (emprRazonSocial == null)
            throw new ValidatorException("Es necesario contener el atributo 'razonSocial' de la empresa");
		
		if (emprRazonSocial.length() > 200)
            throw new ValidatorException("la longitud del atributo 'razonSocial' no debe exceder los 200 caracteres");
	}
	
	public void validaEmprNombreComercial(String emprNombreComercial) throws ValidatorException{
		if (emprNombreComercial == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombreComercial' de la empresa");
		
		if (emprNombreComercial.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombreComercial' no debe exceder los 200 caracteres");
	}
	
	public void validaEmprDireccion(String emprDireccion) throws ValidatorException{
		if (emprDireccion == null)
            throw new ValidatorException("Es necesario contener el atributo 'direccion' de la empresa");
		
		if (emprDireccion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'direccion' no debe exceder los 200 caracteres");
	}
	
	public void validaEmprUnigeo(Ubigeo emprUbigeo) throws ValidatorException{
		if (emprUbigeo == null || Long.valueOf(emprUbigeo.getId()) == null)
            throw new ValidatorException("Es necesario contener el atributo 'ubigeo' de la empresa");
	}
	
	public void validaEmprTipoDocIdentidad(TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException{
		if (emprTipoDocIdentidad == null || emprTipoDocIdentidad.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoDocumentoIdentidad' de la empresa");
	}
	
}
