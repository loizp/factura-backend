package com.sunqubit.faqture.service.validators;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.catalogs.TipoDocumento;
import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.DetalleDocumento;
import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.dao.contracts.IContribuyenteDao;
import com.sunqubit.faqture.dao.validators.DocumentoDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class ComprobantePagoValidator extends DocumentoDaoValidator {
	
	@Autowired
	private DocumentoBaseValidator documentoBaseValidator;
	
	@Autowired
	private CodigoDocValidator codigoDocValidator;
	
	@Autowired
	private IContribuyenteDao contribuyenteDao;
	
	@Autowired
	private DetalleDocValidator detalleDocValidator;
	
	@Override
	public void validaDocuNumero(Contribuyente docuEmpresa, TipoDocumento docuTipoDocumento, String docuNumero) throws ValidatorException {
		super.validaDocuNumero(docuEmpresa, docuTipoDocumento, docuNumero);
		
		if (docuNumero.trim().isEmpty() || !codigoDocValidator.numComprobantePagoValido(docuNumero, docuTipoDocumento.getCodigo()))
            throw new ValidatorException("Es necesario contener el atributo 'numero' del documento sea válido según el tipo de documento");
	}

	@Override
	public void validaDocuVendedor(String docuVendedor) throws ValidatorException {
		super.validaDocuVendedor(docuVendedor);
		
		if (docuVendedor.trim().isEmpty() || !docuVendedor.trim().matches("^[\\w-. ]{2,}$"))
            throw new ValidatorException("Es necesario que el atributo 'leyenda' debe estar correctamente expresado");
	}

	@Override
	public void validaDocuEmailCliente(String docuEmailCliente) throws ValidatorException {
		super.validaDocuEmailCliente(docuEmailCliente);
		
		if (!docuEmailCliente.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            throw new ValidatorException("Es necesario que el atributo 'emailCliente' debe estar correctamente expresado");
	}
	
	public void validaDocBaseSinple(ComprobantePago compPago) throws ValidatorException {
		documentoBaseValidator.validaDocuFechaEmision(compPago.getFechaEmision());
		documentoBaseValidator.validaDocuFechaProceso(compPago.getFechaProceso());
		documentoBaseValidator.validaDocuLeyendas(compPago.getLeyendas());
			
    	if(compPago.getObservacion() != null)
    		documentoBaseValidator.validaDocuObservacion(compPago.getObservacion());
    	if(compPago.getEstadoProceso() != null)
    		documentoBaseValidator.validaDocuEstadoProceso(compPago.getFechaProceso(), compPago.getEstadoProceso());
    	if(compPago.getMoneda() != null)
    		documentoBaseValidator.validaDocuMoneda(compPago.getMoneda());
    	if(compPago.getEmprSucursal() != null)
    		documentoBaseValidator.validaDocuEmprSucursal(compPago.getEmpresa(), compPago.getEmprSucursal());
	}
	
	public void validaDocAutoEmision(long docEmpresaId, long docClienteId) throws ValidatorException {
		if(docEmpresaId == docClienteId)
			throw new ValidatorException("El documento no puede ser emitido a si mismo");
	}
	
	public void validaDocuClienteFactura(Long docuCliente, String tipoDoc) throws ValidatorException {
		try {
			Contribuyente cliente = contribuyenteDao.get(docuCliente);
			if(cliente == null)
				throw new ValidatorException("El cliente no cumple los requisitos necesarios para el documento");
			
			if(tipoDoc.equals("01") && (!cliente.getTipoDocumentoIdentidad().getCodigo().equals("6") || cliente.getNombreLegal().trim().isEmpty()  
					|| cliente.getDireccion().trim().isEmpty() || cliente.getNumeroDocumento().trim().isEmpty()))
				throw new ValidatorException("El cliente no cumple los requisitos necesarios para el documento");
		} catch (Exception e) {
			throw new ValidatorException(e.getMessage());
		}		
	}
	
	public void validaDocClienteFull(Contribuyente cliente, Sucursal sucursal) throws ValidatorException {
		if(cliente == null)
			throw new ValidatorException("No existe datos del cliente");
    	
    	if(cliente.getId() < 1 && (sucursal != null || cliente.getNombreLegal() == null 
    			|| cliente.getTipoDocumentoIdentidad() == null || cliente.getTipoDocumentoIdentidad().getCodigo() == null ))
    		throw new ValidatorException("Datos del cliente inconsistente");
	}
	
	public void validaDocuDetalleItems(List<DetalleDocumento> detalleItemsDoc) throws ValidatorException {
		if(detalleItemsDoc == null || detalleItemsDoc.isEmpty())
    		throw new ValidatorException("Es necesario que el atributo 'detallesDocumento' del documento");
		
		for (DetalleDocumento item : detalleItemsDoc) {
			detalleDocValidator.validaDedoOrden(item.getOrden());
			detalleDocValidator.validaDedoDescripcion(item.getDescripcion());
			detalleDocValidator.validaDedoUnidadMedida(item.getUnidadMedida());
			detalleDocValidator.validaDedoCodigoProducto(item.getCodigoProducto());
			detalleDocValidator.validaDedoTipoAfectIgv(item.getTipoAfectacionIgv());
			if(item.getIsc().compareTo(BigDecimal.ZERO) > 0)
				detalleDocValidator.validaDedoTipoIsc(item.getTipoIsc());
		}
	}
}
