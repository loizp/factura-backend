package com.sunqubit.faqture.ws.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.validators.ContribuyenteDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

@Component
public class ClienteValidator extends ContribuyenteDaoValidator {
	
	@Autowired
	private CodigoDocValidator codigoDocValidator;
	
	@Override
	public void validaContDoc(String contDoc, TipoDocumentoIdentidad contTipoDocIdentidad)
			throws ValidatorException {
		this.validaContTipoDocIdentidad(contTipoDocIdentidad);
		
		if(!contTipoDocIdentidad.getCodigo().equals("0")) {
			if (contDoc == null || contDoc.trim().isEmpty())
	            throw new ValidatorException("Es necesario contener el atributo 'numeroDocumento' del cliente");
			
			if (!codigoDocValidator.docIdentidadValido(contDoc, contTipoDocIdentidad.getCodigo()))
	            throw new ValidatorException("Es necesario contener el atributo 'numeroDocumento' del cliente sea válido según el tipo de documento");
		}
		
		if(contDoc != null && !contDoc.trim().isEmpty()) {
			if(!contDoc.matches("^[A-Z0-9]*$"))
				throw new ValidatorException("Es necesario contener el atributo 'numeroDocumento' del cliente sea válido");
			
			super.validaContDoc(contDoc, contTipoDocIdentidad);
		}
	}
	
	@Override
	public void validaContTipoDocIdentidad(TipoDocumentoIdentidad contTipoDocIdentidad) throws ValidatorException {		
		super.validaContTipoDocIdentidad(contTipoDocIdentidad);
		
		if(contTipoDocIdentidad.getCodigo().trim().isEmpty())
			throw new ValidatorException("Es necesario que el atributo 'tipoDocumentoIdentidad' del cliente");
	}
}
