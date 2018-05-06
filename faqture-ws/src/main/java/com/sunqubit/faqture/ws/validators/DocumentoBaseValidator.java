package com.sunqubit.faqture.ws.validators;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.Moneda;
import com.sunqubit.faqture.core.beans.TipoLeyenda;
import com.sunqubit.faqture.core.validators.DocumentoDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

@Component
public class DocumentoBaseValidator extends DocumentoDaoValidator {
	
	@Override
	public void validaDocuLeyenda(TipoLeyenda docuTipoLeyenda, String docuLeyenda) throws ValidatorException {
		super.validaDocuLeyenda(docuTipoLeyenda, docuLeyenda);
		
		if (docuLeyenda.trim().isEmpty() || !docuLeyenda.trim().matches("^[\\w- ]{2,}$"))
            throw new ValidatorException("Es necesario que el atributo 'leyenda' debe estar correctamente expresado");
	}

	@Override
	public void validaDocuObservacion(String docuObservacion) throws ValidatorException {
		super.validaDocuObservacion(docuObservacion);
		
		if (docuObservacion.trim().isEmpty() || !docuObservacion.trim().matches("^[\\w- ]{2,}$"))
            throw new ValidatorException("Es necesario que el atributo 'leyenda' debe estar correctamente expresado");
	}
	
	@Override
	public void validaDocuEstadoProceso(Timestamp docuFechaProceso, String docuEstadoProceso) throws ValidatorException {
		super.validaDocuEstadoProceso(docuFechaProceso, docuEstadoProceso);
		
		String estados = "NMBPEX";
		if(docuEstadoProceso.trim().isEmpty() || !estados.contains(docuEstadoProceso))
			throw new ValidatorException("Es necesario que el atributo 'estadoProceso' no es válido");
	}
	
	@Override
	public void validaDocuMoneda(Moneda docuMoneda) throws ValidatorException {
		super.validaDocuMoneda(docuMoneda);
		
		if(docuMoneda.getCodigo().trim().isEmpty() || !docuMoneda.getCodigo().matches("^[A-Z]{2,3}$"))
			throw new ValidatorException("Es necesario que el atributo 'moneda' no es válido");
	}
}
