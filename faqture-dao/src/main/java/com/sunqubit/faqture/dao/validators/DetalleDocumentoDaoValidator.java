package com.sunqubit.faqture.dao.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.catalogs.TipoAfectacionIgv;
import com.sunqubit.faqture.beans.catalogs.TipoIsc;
import com.sunqubit.faqture.beans.catalogs.UnidadMedida;

@Component
public class DetalleDocumentoDaoValidator {
	
	public void validaDedoId(long dedoId) throws ValidatorException{
		if (dedoId < 1)
            throw new ValidatorException("Es necesario contener el atributo 'id' del detalle");
	}
	
	public void validaDedoCompPago(ComprobantePago dedoCompPago)throws ValidatorException{
		if (dedoCompPago == null || dedoCompPago.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'comprobantePago' del detalle");
	}
	
	public void validaDedoUnidadMedida(UnidadMedida dedoUnidadMedidad)throws ValidatorException{
		if (dedoUnidadMedidad == null || dedoUnidadMedidad.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'unidadMedida' del detalle");
	
		if(dedoUnidadMedidad.getCodigo().length() > 5)
			 throw new ValidatorException("la longitud del atributo 'unidadMedida.codigo' no puede excedoer los 5 caracteres");
	}
	
	public void validaDedoMoneda(UnidadMedida dedoMoneda)throws ValidatorException{
		if (dedoMoneda == null || dedoMoneda.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'unidadMedida' del detalle");
	
		if(dedoMoneda.getCodigo().length() != 3)
			 throw new ValidatorException("la longitud del atributo 'moneda.codigo' solo acepta 3 caracteres");
	}
	
	public void validaDedoTipoAfectIgv(TipoAfectacionIgv dedoTipoAfectIgv)throws ValidatorException{
		if(dedoTipoAfectIgv.getCodigo().length() != 2)
			 throw new ValidatorException("la longitud del atributo 'tipoAfectacionIgv.codigo' solo debe tener 2 caracteres");
	}
	
	public void validaDedoTipoIsc(TipoIsc dedoTipoIsc)throws ValidatorException{
		if(dedoTipoIsc.getCodigo().length() != 2)
			 throw new ValidatorException("la longitud del atributo 'tipoIsc.codigo' solo debe tener 2 caracteres");
	}
	
	public void validaDedoOrden(long dedoOrden) throws ValidatorException{
		if (dedoOrden < 1)
            throw new ValidatorException("Es necesario contener el atributo 'orden' del detalle");
	}
	
	public void validaDedoCodigoProducto(String dedoCodigoProducto) throws ValidatorException{
		if (dedoCodigoProducto.length() > 20)
            throw new ValidatorException("la longitud del atributo 'codigoProducto' no debe exceder los 20 caracteres");
	}
	
	public void validaDedoDescripcion(String dedoDescripcion) throws ValidatorException{
		if (dedoDescripcion == null)
            throw new ValidatorException("Es necesario contener el atributo 'descripcion' del detalle");
		
		if (dedoDescripcion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'descripcion' no debe exceder los 20 caracteres");
	}
}
