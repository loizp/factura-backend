package com.sunqubit.faqture.service.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.TipoDocumentoIdentidad;
import com.sunqubit.faqture.dao.validators.ContribuyenteDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class EmpresaValidator extends ContribuyenteDaoValidator {
	
	@Autowired
	private CodigoDocValidator codigoDocValidator;
	
	@Override
	public void validaContDoc(String contDoc, TipoDocumentoIdentidad contTipoDocIdentidad) throws ValidatorException {
		this.validaContTipoDocIdentidad(contTipoDocIdentidad);
		
		if(contDoc == null || contDoc.trim().isEmpty())
			throw new ValidatorException("Es necesario que el atributo 'numeroDocumento' de la empresa");
		
		super.validaContDoc(contDoc, contTipoDocIdentidad);
		
		if(!codigoDocValidator.docIdentidadValido(contDoc, contTipoDocIdentidad.getCodigo())) {
			throw new ValidatorException("Es necesario que el atributo 'numeroDocumento' sea válido según el tipo de documento");
		}
	}

	@Override
	public void validaContTipoDocIdentidad(TipoDocumentoIdentidad contTipoDocIdentidad) throws ValidatorException {		
		super.validaContTipoDocIdentidad(contTipoDocIdentidad);
		
		String tidoPermitidos="1467ABCD";
		if(contTipoDocIdentidad.getCodigo().trim().isEmpty() || tidoPermitidos.indexOf(contTipoDocIdentidad.getCodigo()) == -1)
			throw new ValidatorException("Es necesario que el atributo 'tipoDocumentoIdentidad' exija el numero de documento de la empresa");
	}
}
