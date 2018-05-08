package com.sunqubit.faqture.dao.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;
import com.sunqubit.faqture.dao.contracts.ITipoLeyendaDao;

@Component
public class LeyendaDaoValidator {
	
	@Autowired
	private ITipoLeyendaDao tipoLeyendaDao;
	
	public void validaLeyeDocumento(long docuId) throws ValidatorException{
		if (docuId < 1)
            throw new ValidatorException("Es necesario contener el atributo 'documento' de la leyenda");
	}
	
	public void validaLeyeTipo(TipoLeyenda leyeTipoLeyenda) throws ValidatorException{		
		if (leyeTipoLeyenda == null || leyeTipoLeyenda.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'leyeTipoLeyenda' de la leyenda");
		
		try {
			if (!tipoLeyendaDao.tleyExist(leyeTipoLeyenda.getCodigo()))
				throw new ValidatorException("Es necesario contener el atributo 'tipoLeyenda' de la leyenda exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaLeyeDescripcion(String leyeDescripcion) throws ValidatorException{
		if (leyeDescripcion == null)
            throw new ValidatorException("Es necesario contener el atributo 'descripcion' de la leyenda");
		
		if (leyeDescripcion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'descripcion' no debe exceder los 200 caracteres");
	}
}
