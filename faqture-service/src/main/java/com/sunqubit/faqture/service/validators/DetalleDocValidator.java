package com.sunqubit.faqture.service.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.TipoIsc;
import com.sunqubit.faqture.dao.validators.DetalleDocumentoDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class DetalleDocValidator extends DetalleDocumentoDaoValidator {
	
	@Override
	public void validaDedoTipoIsc(TipoIsc dedoTipoIsc) throws ValidatorException {
		if (dedoTipoIsc == null || dedoTipoIsc.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoIsc' del detalle");
		
		super.validaDedoTipoIsc(dedoTipoIsc);
		
		if(dedoTipoIsc.getCodigo().trim().isEmpty())
			throw new ValidatorException("Es necesario contener el atributo 'tipoIsc' del detalle");
	}
	
	@Override
	public void validaDedoCodigoProducto(String dedoCodigoProducto) throws ValidatorException {
		if (dedoCodigoProducto == null)
            throw new ValidatorException("Es necesario contener el atributo 'codigoProducto' del detalle");
		
		super.validaDedoCodigoProducto(dedoCodigoProducto);
	}
	
	@Override
	public void validaDedoDescripcion(String dedoDescripcion) throws ValidatorException {
		super.validaDedoDescripcion(dedoDescripcion);
		
		if (dedoDescripcion.trim().isEmpty() || !dedoDescripcion.trim().matches("^[\\w-.#\\(\\) ]{2,}$")) {
            throw new ValidatorException("Es necesario que el atributo 'descripcion' debe estar correctamente expresado");
        }
	}
}
