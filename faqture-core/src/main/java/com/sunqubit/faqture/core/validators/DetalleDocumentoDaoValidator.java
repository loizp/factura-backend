package com.sunqubit.faqture.core.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.TipoAfectacionIgv;
import com.sunqubit.faqture.core.beans.TipoIsc;
import com.sunqubit.faqture.core.beans.UnidadMedida;

@Component
public class DetalleDocumentoDaoValidator {
	
	public void validaDedoId(long dedoId) throws ValidatorException{
		if (Long.valueOf(dedoId) == null)
            throw new ValidatorException("Es necesario contener el atributo 'id' del detalle");
	}
	
	public void validaDedoCompPago(ComprobantePago dedoCompPago)throws ValidatorException{
		if (dedoCompPago == null || Long.valueOf(dedoCompPago.getId()) == null)
            throw new ValidatorException("Es necesario contener el atributo 'comprobantePago' del detalle");
	}
	
	public void validaDedoUnidadMedida(UnidadMedida dedoUnidadMedidad)throws ValidatorException{
		if (dedoUnidadMedidad == null || dedoUnidadMedidad.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'unidadMedida' del detalle");
	
		if(dedoUnidadMedidad.getCodigo().length() > 5)
			 throw new ValidatorException("la longitud del atributo 'unidadMedida.codigo' no puede excedoer los 5 caracteres");
	}
	
	public void validaDedoTipoAfectIgv(TipoAfectacionIgv dedoTipoAfectIgv)throws ValidatorException{
		if (dedoTipoAfectIgv == null || dedoTipoAfectIgv.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoAfectacionIgv' del detalle");
	
		if(dedoTipoAfectIgv.getCodigo().length() != 2)
			 throw new ValidatorException("la longitud del atributo 'tipoAfectacionIgv.codigo' solo debe tener 2 caracteres");
	}
	
	public void validaDedoTipoIsc(TipoIsc dedoTipoIsc)throws ValidatorException{
		if (dedoTipoIsc == null || dedoTipoIsc.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoIsc' del detalle");
		
		if(dedoTipoIsc.getCodigo().length() != 2)
			 throw new ValidatorException("la longitud del atributo 'tipoIsc.codigo' solo debe tener 2 caracteres");
	}
	
	public void validaDedoOrden(long dedoOrden) throws ValidatorException{
		if (Long.valueOf(dedoOrden) == null)
            throw new ValidatorException("Es necesario contener el atributo 'orden' del detalle");
	}
	
	public void validaDedoCodigoProducto(String dedoCodigoProducto) throws ValidatorException{
		if (dedoCodigoProducto == null)
            throw new ValidatorException("Es necesario contener el atributo 'codigoProducto' del detalle");
		
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
