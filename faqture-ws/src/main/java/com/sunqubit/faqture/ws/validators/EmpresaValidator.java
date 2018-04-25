package com.sunqubit.faqture.ws.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.validators.ContribuyenteDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

@Component
public class EmpresaValidator extends ContribuyenteDaoValidator {
	
	@Autowired
	private DocIdentidadValidator docIdentidadValidator;
	
	@Override
	public void validaEmprId(long emprId) throws ValidatorException{
		super.validaEmprId(emprId);
	}
	
	@Override
	public void validaEmprRuc(String emprRuc, TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException {
		super.validaEmprRuc(emprRuc, emprTipoDocIdentidad);
		
		this.validaEmprTipoDocIdentidad(emprTipoDocIdentidad);
		
		if(emprRuc.trim().isEmpty())
			throw new ValidatorException("Es necesario que el atributo 'ruc' de la empresa");
		
		if(!docIdentidadValidator.docIdentidadValido(emprRuc, emprTipoDocIdentidad.getCodigo())) {
			throw new ValidatorException("Es necesario que el atributo 'ruc' sea valido segun el tipo de documento");
		}
	}
	
	@Override
	public void validaEmprRazonSocial(String emprRazonSocial) throws ValidatorException{
		super.validaEmprRazonSocial(emprRazonSocial);
		
		if (emprRazonSocial.isEmpty() || !emprRazonSocial.matches("^[\\w- ]+(\\.[\\w- ]+)*$"))
			throw new ValidatorException("Es necesario que el atributo 'razonSocial' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaEmprNombreComercial(String emprNombreComercial) throws ValidatorException{
		super.validaEmprNombreComercial(emprNombreComercial);
		
		if (emprNombreComercial.isEmpty() || !emprNombreComercial.matches("^[\\w- ]+(\\.[\\w- ]+)*$"))
			throw new ValidatorException("Es necesario que el atributo 'razonSocial' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaEmprDireccion(String emprDireccion) throws ValidatorException{
		super.validaEmprDireccion(emprDireccion);
		
		if (emprDireccion.isEmpty() || !emprDireccion.matches("^[\\w- ]+(\\.[\\w- ]+)*#[\\w- ]+(\\.[\\w- ]+)*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaEmprUnigeo(Ubigeo emprUbigeo) throws ValidatorException{
		super.validaEmprUnigeo(emprUbigeo);
	}
	
	@Override
	public void validaEmprTipoDocIdentidad(TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException {
		super.validaEmprTipoDocIdentidad(emprTipoDocIdentidad);
		String tidoPermitidos="1467A";
		if(!tidoPermitidos.contains(emprTipoDocIdentidad.getCodigo()))
			throw new ValidatorException("Es necesario que el atributo 'tipoDocumentoIdentidad' exija el numero de documento de la empresa");
	} 

}
