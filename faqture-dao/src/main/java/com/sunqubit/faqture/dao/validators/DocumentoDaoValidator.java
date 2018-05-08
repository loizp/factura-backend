package com.sunqubit.faqture.dao.validators;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.catalogs.Moneda;
import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.beans.catalogs.TipoDocumento;
import com.sunqubit.faqture.beans.catalogs.TipoNota;
import com.sunqubit.faqture.beans.catalogs.TipoOperacion;
import com.sunqubit.faqture.dao.contracts.IContribuyenteDao;
import com.sunqubit.faqture.dao.contracts.ISucursalDao;
import com.sunqubit.faqture.dao.contracts.ITipoNotaDao;
import com.sunqubit.faqture.dao.contracts.ITipoOperacionDao;

@Component
public class DocumentoDaoValidator {
	
	@Autowired
    private IContribuyenteDao contribuyenteDao;
	
	@Autowired
	private ISucursalDao sucursalDao;
	
	@Autowired
	private ITipoOperacionDao tipoOperacionDao;
	
	@Autowired
	private ITipoNotaDao tipoNotaDao;
    
	public void validaDocuId(long docuId) throws ValidatorException{
		if (docuId < 1)
            throw new ValidatorException("Es necesario contener el atributo 'id' del documento");
	}
	
	public void validaDocuFechaEmision(Timestamp docuFechaEmision) throws ValidatorException{
		if (docuFechaEmision == null)
            throw new ValidatorException("Es necesario contener el atributo 'fechaEmision' del documento");
	}
	
	public void validaDocuNumero(Contribuyente docuEmpresa, TipoDocumento docuTipoDocumento, String docuNumero) throws ValidatorException{
		this.validaDocuEmpresa(docuEmpresa);
		this.validaDocuTipoDocumento(docuTipoDocumento);
		
		if (docuNumero == null)
            throw new ValidatorException("Es necesario contener el atributo 'numero' del documento");
		
		if (docuNumero.length() > 20)
            throw new ValidatorException("la longitud del atributo 'numero' no debe exceder los 20 caracteres");
	}
	
	public void validaDocuObservacion(String docuObservacion) throws ValidatorException{		
		if (docuObservacion.length() > 250)
            throw new ValidatorException("la longitud del atributo 'observacion' no debe exceder los 250 caracteres");
	}
	
	public void validaDocuFechaProceso(Timestamp docuFechaProceso) throws ValidatorException{
		if (docuFechaProceso == null)
            throw new ValidatorException("Es necesario contener el atributo 'fechaProceso' del documento");
	}
	
	public void validaDocuEstadoProceso(Timestamp docuFechaProceso, String docuEstadoProceso) throws ValidatorException{
		this.validaDocuFechaProceso(docuFechaProceso);
		
		if (docuEstadoProceso == null)
            throw new ValidatorException("Es necesario contener el atributo 'estadoProceso' del documento");
		
		if (docuEstadoProceso.length() != 1)
            throw new ValidatorException("la longitud del atributo 'estadoProceso' solo debe tener un caracter");
	}
	
	public void validaDocuTipoDocumento(TipoDocumento docuTipoDocumento) throws ValidatorException{
		if (docuTipoDocumento == null || docuTipoDocumento.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoDocumento' del documento");
		
		if (docuTipoDocumento.getCodigo().length() != 2)
            throw new ValidatorException("la longitud del atributo 'tipoDocumento.codigo' solo debe tener dos caracteres");
	}
	
	public void validaDocuEmpresa(Contribuyente docuEmpresa) throws ValidatorException{
		if (docuEmpresa == null || docuEmpresa.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'empresa' del documento");
		
		try {
			if (!contribuyenteDao.contExist(docuEmpresa.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'empresa' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuEmprSucursal(Contribuyente docuEmpresa, Sucursal docuEmprSucursal) throws ValidatorException{
		this.validaDocuEmpresa(docuEmpresa);
		
		if (docuEmprSucursal == null || docuEmprSucursal.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'emprSucursal' del documento");
		
		try {
			if (!sucursalDao.sucuExist(docuEmprSucursal.getId(), docuEmpresa.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'emprSucursal' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuMoneda(Moneda docuMoneda) throws ValidatorException{
		if (docuMoneda == null || docuMoneda.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'moneda' del documento");
		
		if (docuMoneda.getCodigo().length() > 3)
            throw new ValidatorException("la longitud del atributo 'moneda.codigo' no debe exceder los tres caracteres");
	}
	
	public void validaDocuLinkPdf(String docuLinkPdf) throws ValidatorException{
		if (docuLinkPdf == null)
            throw new ValidatorException("Es necesario contener el atributo 'linkPdf' del documento");
		
		if (docuLinkPdf.length() > 200)
            throw new ValidatorException("la longitud del atributo 'linkPdf' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuLinkXml(String docuLinkXml) throws ValidatorException{
		if (docuLinkXml == null)
            throw new ValidatorException("Es necesario contener el atributo 'linkXml' del documento");
		
		if (docuLinkXml.length() > 200)
            throw new ValidatorException("la longitud del atributo 'linkXml' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuLinkHashSunat(String docuHashSunat) throws ValidatorException{
		if (docuHashSunat == null)
            throw new ValidatorException("Es necesario contener el atributo 'hashSunat' del documento");
		
		if (docuHashSunat.length() > 200)
            throw new ValidatorException("la longitud del atributo 'hashSunat' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuLinkCdr(String docuLinkCdr) throws ValidatorException{
		if (docuLinkCdr == null)
            throw new ValidatorException("Es necesario contener el atributo 'linkCdr' del documento");
		
		if (docuLinkCdr.length() > 200)
            throw new ValidatorException("la longitud del atributo 'linkCdr' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuCdrStatus(String docuCdrStatus) throws ValidatorException{
		if (docuCdrStatus == null)
            throw new ValidatorException("Es necesario contener el atributo 'cdrStatus' del documento");
		
		if (docuCdrStatus.length() > 200)
            throw new ValidatorException("la longitud del atributo 'cdrStatus' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuCdrNota(String docuCdrNota) throws ValidatorException{
		if (docuCdrNota == null)
            throw new ValidatorException("Es necesario contener el atributo 'cdrNota' del documento");
		
		if (docuCdrNota.length() > 200)
            throw new ValidatorException("la longitud del atributo 'cdrNota' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuCdrObservacion(String docuCdrObservacion) throws ValidatorException{
		if (docuCdrObservacion == null)
            throw new ValidatorException("Es necesario contener el atributo 'cdrObservacion' del documento");
		
		if (docuCdrObservacion.length() > 200)
            throw new ValidatorException("la longitud del atributo 'cdrObservacion' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuCliente(Contribuyente docuCliente) throws ValidatorException{
		if (docuCliente == null || docuCliente.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'cliente' del documento");
		
		try {
			if (!contribuyenteDao.contExist(docuCliente.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'cliente' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuClieSucursal(Contribuyente docuCliente, Sucursal docuClieSucursal) throws ValidatorException{
		this.validaDocuCliente(docuCliente);
		
		if (docuClieSucursal == null || docuClieSucursal.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'clieSucursal' del documento");
		
		try {
			if (!sucursalDao.sucuExist(docuClieSucursal.getId(), docuCliente.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'clieSucursal' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuTipoOperacion(TipoOperacion docuTipoOperacion) throws ValidatorException{
		if (docuTipoOperacion == null || docuTipoOperacion.getCodigo() == null)
            throw new ValidatorException("Es necesario contener el atributo 'tipoOperacion' del documento");
		
		try {
			if (!tipoOperacionDao.tiopExist(docuTipoOperacion.getCodigo()))
				throw new ValidatorException("Es necesario contener el atributo 'tipoOperacion' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuVendedor(String docuVendedor) throws ValidatorException{
		if (docuVendedor == null || docuVendedor == null)
            throw new ValidatorException("Es necesario contener el atributo 'vendedor' del documento");
		
		if (docuVendedor.length() > 200)
            throw new ValidatorException("la longitud del atributo 'vendedor' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuEmailCliente(String docuEmailCliente) throws ValidatorException{
		if (docuEmailCliente == null || docuEmailCliente == null)
            throw new ValidatorException("Es necesario contener el atributo 'emailCliente' del documento");
		
		if (docuEmailCliente.length() > 200)
            throw new ValidatorException("la longitud del atributo 'emailCliente' no debe exceder los 200 caracteres");
	}
	
	public void validaDocuTipoNota(TipoDocumento docuTipoDocumento, TipoNota docuTipoNota) throws ValidatorException{
		this.validaDocuTipoDocumento(docuTipoDocumento);
		
		if (docuTipoNota == null || docuTipoNota.getId() < 1)
            throw new ValidatorException("Es necesario contener el atributo 'tipoNota' del documento");
		
		try {
			if (!tipoNotaDao.tinoExist(docuTipoDocumento.getCodigo(), docuTipoNota.getId()))
				throw new ValidatorException("Es necesario contener el atributo 'tipoNota' del documento exista");
		} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaDocuSustentoNota(String docuSustentoNota) throws ValidatorException{
		if (docuSustentoNota == null || docuSustentoNota == null)
            throw new ValidatorException("Es necesario contener el atributo 'sustentoNota' del documento");
		
		if (docuSustentoNota.length() > 250)
            throw new ValidatorException("la longitud del atributo 'sustentoNota' no debe exceder los 250 caracteres");
	}
}
