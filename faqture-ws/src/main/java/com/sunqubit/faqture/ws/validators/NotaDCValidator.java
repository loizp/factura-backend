package com.sunqubit.faqture.ws.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.NotaDC;
import com.sunqubit.faqture.core.beans.TipoDocumento;
import com.sunqubit.faqture.core.validators.DocumentoDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

public class NotaDCValidator extends DocumentoDaoValidator {
	
	@Autowired
	private DocumentoBaseValidator documentoBaseValidator;
	
	@Autowired
	private CodigoDocValidator codigoDocValidator;
	
	@Override
	public void validaDocuNumero(Contribuyente docuEmpresa, TipoDocumento docuTipoDocumento, String docuNumero)
			throws ValidatorException {
		super.validaDocuNumero(docuEmpresa, docuTipoDocumento, docuNumero);
		
		if (docuNumero.trim().isEmpty() || !codigoDocValidator.numNotaValido(docuNumero, docuTipoDocumento.getCodigo()))
            throw new ValidatorException("Es necesario contener el atributo 'numero' del documento sea válido según el tipo de documento");
	}
	
	@Override
	public void validaDocuSustentoNota(String docuSustentoNota) throws ValidatorException {
		super.validaDocuSustentoNota(docuSustentoNota);
		
		if (docuSustentoNota.trim().isEmpty() || !docuSustentoNota.trim().matches("^[\\w- ]{2,}$"))
            throw new ValidatorException("Es necesario que el atributo 'sustentoNota' debe estar correctamente expresado");
	}
	
	public void validaDocBaseSingle(NotaDC notaDC) throws ValidatorException {
		documentoBaseValidator.validaDocuFechaEmision(notaDC.getFechaEmision());
    	if(notaDC.getLeyenda() != null)
    		documentoBaseValidator.validaDocuLeyenda(notaDC.getTipoLeyenda(), notaDC.getLeyenda());
    	if(notaDC.getObservacion() != null)
    		documentoBaseValidator.validaDocuObservacion(notaDC.getObservacion());
    	documentoBaseValidator.validaDocuFechaProceso(notaDC.getFechaProceso());
    	if(notaDC.getEstadoProceso() != null)
    		documentoBaseValidator.validaDocuEstadoProceso(notaDC.getFechaProceso(), notaDC.getEstadoProceso());
    	documentoBaseValidator.validaDocuMoneda(notaDC.getMoneda());
    	if(notaDC.getEmprSucursal() != null)
    		documentoBaseValidator.validaDocuEmprSucursal(notaDC.getEmpresa(), notaDC.getEmprSucursal());
	}
}
