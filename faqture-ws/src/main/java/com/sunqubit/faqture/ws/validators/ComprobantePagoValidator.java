package com.sunqubit.faqture.ws.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.TipoDocumento;
import com.sunqubit.faqture.core.validators.DocumentoDaoValidator;
import com.sunqubit.faqture.core.validators.ValidatorException;

public class ComprobantePagoValidator extends DocumentoDaoValidator {
	
	@Autowired
	private DocumentoBaseValidator documentoBaseValidator;
	
	@Autowired
	private CodigoDocValidator codigoDocValidator;
	
	@Override
	public void validaDocuNumero(Contribuyente docuEmpresa, TipoDocumento docuTipoDocumento, String docuNumero) throws ValidatorException {
		super.validaDocuNumero(docuEmpresa, docuTipoDocumento, docuNumero);
		
		if (docuNumero.trim().isEmpty() || !codigoDocValidator.numComprobantePagoValido(docuNumero, docuTipoDocumento.getCodigo()))
            throw new ValidatorException("Es necesario contener el atributo 'numero' del documento sea válido según el tipo de documento");
	}

	@Override
	public void validaDocuVendedor(String docuVendedor) throws ValidatorException {
		super.validaDocuVendedor(docuVendedor);
		
		if (docuVendedor.trim().isEmpty() || !docuVendedor.trim().matches("^[\\w-]{2,}$"))
            throw new ValidatorException("Es necesario que el atributo 'leyenda' debe estar correctamente expresado");
	}

	@Override
	public void validaDocuEmailCliente(String docuEmailCliente) throws ValidatorException {
		super.validaDocuEmailCliente(docuEmailCliente);
		
		if (!docuEmailCliente.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            throw new ValidatorException("Es necesario que el atributo 'emailCliente' debe estar correctamente expresado");
	}
	
	public void validaDocBaseSingle(ComprobantePago compPago) throws ValidatorException {
		documentoBaseValidator.validaDocuFechaEmision(compPago.getFechaEmision());
    	if(compPago.getLeyenda() != null)
    		documentoBaseValidator.validaDocuLeyenda(compPago.getTipoLeyenda(), compPago.getLeyenda());
    	if(compPago.getObservacion() != null)
    		documentoBaseValidator.validaDocuObservacion(compPago.getObservacion());
    	documentoBaseValidator.validaDocuFechaProceso(compPago.getFechaProceso());
    	if(compPago.getEstadoProceso() != null)
    		documentoBaseValidator.validaDocuEstadoProceso(compPago.getFechaProceso(), compPago.getEstadoProceso());
    	documentoBaseValidator.validaDocuMoneda(compPago.getMoneda());
    	if(compPago.getEmprSucursal() != null)
    		documentoBaseValidator.validaDocuEmprSucursal(compPago.getEmpresa(), compPago.getEmprSucursal());
	}
}
