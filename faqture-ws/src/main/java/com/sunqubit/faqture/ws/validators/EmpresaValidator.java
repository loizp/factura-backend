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
	public void validaContId(long emprId) throws ValidatorException{
		super.validaContId(emprId);
	}
	
	@Override
	public void validaContDoc(String emprDoc, TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException {
		super.validaContDoc(emprDoc, emprTipoDocIdentidad);
		
		this.validaContTipoDocIdentidad(emprTipoDocIdentidad);
		
		if(emprDoc.trim().isEmpty())
			throw new ValidatorException("Es necesario que el atributo 'numeroDocumento' de la empresa");
		
		if(!docIdentidadValidator.docIdentidadValido(emprDoc, emprTipoDocIdentidad.getCodigo())) {
			throw new ValidatorException("Es necesario que el atributo 'numeroDocumento' sea valido segun el tipo de documento");
		}
	}
	
	@Override
	public void validaContNombreLegal(String emprRazonSocial) throws ValidatorException{
		super.validaContNombreLegal(emprRazonSocial);
		
		if (emprRazonSocial.isEmpty() || !emprRazonSocial.matches("^[\\w- ]+(\\.[\\w- ]+)*$"))
			throw new ValidatorException("Es necesario que el atributo 'nombreLegal' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaContNombreComercial(String emprNombreComercial) throws ValidatorException{
		super.validaContNombreComercial(emprNombreComercial);
		
		if (emprNombreComercial.isEmpty() || !emprNombreComercial.matches("^[\\w- ]+(\\.[\\w- ]+)*$"))
			throw new ValidatorException("Es necesario que el atributo 'razonSocial' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaContDireccion(String emprDireccion) throws ValidatorException{
		super.validaContDireccion(emprDireccion);
		
		if (emprDireccion.isEmpty() || !emprDireccion.matches("^[\\w- ]+(\\.[\\w- ]+)*#[\\w- ]+(\\.[\\w- ]+)*$"))
            throw new ValidatorException("Es necesario que el atributo 'direccion' debe estar correctamente expresado");
	}
	
	@Override
	public void validaContUnigeo(Ubigeo emprUbigeo) throws ValidatorException{
		super.validaContUnigeo(emprUbigeo);
	}
	
	@Override
	public void validaContTipoDocIdentidad(TipoDocumentoIdentidad emprTipoDocIdentidad) throws ValidatorException {
		super.validaContTipoDocIdentidad(emprTipoDocIdentidad);
		
		String tidoPermitidos="1467A";
		if(!tidoPermitidos.contains(emprTipoDocIdentidad.getCodigo()))
			throw new ValidatorException("Es necesario que el atributo 'tipoDocumentoIdentidad' exija el numero de documento de la empresa");
	} 

}
