package com.sunqubit.faqture.service.validators;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.Moneda;
import com.sunqubit.faqture.beans.core.Leyenda;
import com.sunqubit.faqture.dao.validators.DocumentoDaoValidator;
import com.sunqubit.faqture.dao.validators.LeyendaDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class DocumentoBaseValidator extends DocumentoDaoValidator {
	
	@Autowired
	LeyendaDaoValidator leyendaDaoValidator;

    @Override
    public void validaDocuObservacion(String docuObservacion) throws ValidatorException {
    	if (docuObservacion == null)
            throw new ValidatorException("Es necesario contener el atributo 'observacion' del documento");
    	
    	super.validaDocuObservacion(docuObservacion);

        if (!docuObservacion.matches("^[\\w-.# ]*$")) {
            throw new ValidatorException("Es necesario que el atributo 'observacion' debe estar correctamente expresado");
        }
    }

    public void validaDocuEstadoProceso(Timestamp docuFechaProceso, String docuEstadoProceso) throws ValidatorException {
        super.validaDocuEstadoProceso(docuFechaProceso, docuEstadoProceso);

        String estados = "NMBPEX";
        if (docuEstadoProceso.trim().isEmpty() || !estados.contains(docuEstadoProceso)) {
            throw new ValidatorException("Es necesario que el atributo 'estadoProceso' no es válido");
        }
    }

    @Override
    public void validaDocuMoneda(Moneda docuMoneda) throws ValidatorException {
        super.validaDocuMoneda(docuMoneda);

        if (docuMoneda.getCodigo().trim().isEmpty() || !docuMoneda.getCodigo().matches("^[A-Z]{2,3}$")) {
            throw new ValidatorException("Es necesario que el atributo 'moneda' no es válido");
        }
    }
    
    public void validaDocuLeyendas(List<Leyenda> docuLeyendas) throws ValidatorException {
    	if(docuLeyendas == null || docuLeyendas.isEmpty())
    		throw new ValidatorException("Es necesario que el atributo 'leyendas' del documento");
    	
    	for (Leyenda leyenda : docuLeyendas) {
    		leyendaDaoValidator.validaLeyeTipo(leyenda.getTipoLeyenda());
    		leyendaDaoValidator.validaLeyeDescripcion(leyenda.getDescripcion());
    		
    		if(leyenda.getDescripcion().trim().isEmpty() || leyenda.getDescripcion().trim().matches("^[\\w.-# ]*$"))
    			throw new ValidatorException("Es necesario que el atributo 'descripcion' de las leyendas sean correctamente llenadas");
		}
    }
}
