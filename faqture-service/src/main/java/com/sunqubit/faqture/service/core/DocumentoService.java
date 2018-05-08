package com.sunqubit.faqture.service.core;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.NotaDC;
import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.IDocumentoDao;
import com.sunqubit.faqture.dao.validators.ValidatorException;
import com.sunqubit.faqture.service.validators.ComprobantePagoValidator;
import com.sunqubit.faqture.service.validators.NotaDCValidator;

@Service
public class DocumentoService {

    @Autowired
    private IDocumentoDao documentoDao;

    @Autowired
    private ComprobantePagoValidator comprobantePagoValidator;

    @Autowired
    private NotaDCValidator notaDCValidator;
    
    @Autowired
    private ContribuyenteService contribuyenteService;
    
    @Autowired
    private SucursalService sucursalService;

    public ApiRestFullResponse insertSimple(ComprobantePago compPago) {
        Boolean ok = true;
        int code = 201;
        String msg = "El comprobante de pago fue registrado correctamente";
        Long res = null;
        try {
            compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            compPago.setEstadoProceso("N");
            comprobantePagoValidator.validaDocuNumero(compPago.getEmpresa(), compPago.getTipoDocumento(), compPago.getNumero());
            comprobantePagoValidator.validaDocBaseSinple(compPago);
            comprobantePagoValidator.validaDocuCliente(compPago.getCliente());
            comprobantePagoValidator.validaDocuClienteFactura(compPago.getCliente().getId(), compPago.getTipoDocumento().getCodigo());
            if (compPago.getClieSucursal() != null) {
                comprobantePagoValidator.validaDocuClieSucursal(compPago.getCliente(), compPago.getClieSucursal());
            }
            comprobantePagoValidator.validaDocuTipoOperacion(compPago.getTipoOperacion());
            if (compPago.getVendedor() != null) {
                comprobantePagoValidator.validaDocuVendedor(compPago.getVendedor());
            }
            if (compPago.getEmailCliente() != null) {
                comprobantePagoValidator.validaDocuEmailCliente(compPago.getEmailCliente());
            }
            comprobantePagoValidator.validaDocAutoEmision(compPago.getEmpresa().getId(), compPago.getCliente().getId());
            comprobantePagoValidator.validaDocuDetalleItems(compPago.getDetallesDocumento());
            res = documentoDao.insert(compPago);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar el comprobante de pago debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar el comprobante de pago debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse insertFull(ComprobantePago compPago) {
        Boolean ok = true;
        int code = 201;
        String msg = "El comprobante de pago fue registrado correctamente";
        Long res = null;
        ApiRestFullResponse servicio;
        try {
        	compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            compPago.setEstadoProceso("N");
            comprobantePagoValidator.validaDocuNumero(compPago.getEmpresa(), compPago.getTipoDocumento(), compPago.getNumero());
            comprobantePagoValidator.validaDocBaseSinple(compPago);
            comprobantePagoValidator.validaDocuTipoOperacion(compPago.getTipoOperacion());
            comprobantePagoValidator.validaDocClienteFull(compPago.getCliente(), compPago.getClieSucursal());
            if (compPago.getVendedor() != null) {
                comprobantePagoValidator.validaDocuVendedor(compPago.getVendedor());
            }
            if (compPago.getEmailCliente() != null) {
                comprobantePagoValidator.validaDocuEmailCliente(compPago.getEmailCliente());
            }
            comprobantePagoValidator.validaDocuDetalleItems(compPago.getDetallesDocumento());
            HashMap<String, Object> dataC = registrarClienteCP(compPago.getCliente());
            if((boolean) dataC.get("ok")) {
            	compPago.getCliente().setId((long) dataC.get("clie"));
            	comprobantePagoValidator.validaDocuClienteFactura(compPago.getCliente().getId(), compPago.getTipoDocumento().getCodigo());
            	if(dataC.get("sucu") != null) {
            		compPago.setClieSucursal((Sucursal) dataC.get("sucu"));
            	}
            	comprobantePagoValidator.validaDocAutoEmision(compPago.getEmpresa().getId(), compPago.getCliente().getId());
            	if(compPago.getClieSucursal() != null) {
            		if(compPago.getClieSucursal().getId() > 0)
            			comprobantePagoValidator.validaDocuClieSucursal(compPago.getCliente(), compPago.getClieSucursal());
            		else {
            			servicio = sucursalService.insert(compPago.getClieSucursal());
            			ok = servicio.getResponse().isSuccess();
            			if(servicio.getResponse().isSuccess()) {
            				compPago.getClieSucursal().setId((long) servicio.getData());
            			}
            			else {
                            code = servicio.getResponse().getCode();
                            msg = servicio.getResponse().getMessage();
            			}
            		}
            		comprobantePagoValidator.validaDocAutoEmision(compPago.getEmpresa().getId(), compPago.getCliente().getId());
            	}
            } else {
            	ok = (Boolean) dataC.get("ok");
                code = (int) dataC.get("code");
                msg = (String) dataC.get("msg");
            }
            if(ok)
            	res = documentoDao.insert(compPago);
        } catch (ValidatorException ve) {
			ok = false;
            code = 400;
            msg = "No se puede registrar el comprobante de pago debido a: " + ve.getMessage();
		} catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar el comprobante de pago debido a: " + ex.getMessage();
        }
        
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }

    public ApiRestFullResponse insert(NotaDC notaDC) {
        Boolean ok = true;
        int code = 201;
        String msg = "La nota fue registrado correctamente";
        Long res = null;
        try {
            notaDC.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            notaDC.setEstadoProceso("N");

            notaDCValidator.validaDocuNumero(notaDC.getEmpresa(), notaDC.getTipoDocumento(), notaDC.getNumero());
            notaDCValidator.validaDocBaseSinple(notaDC);
            notaDCValidator.validaDocuTipoNota(notaDC.getTipoDocumento(), notaDC.getTipoNota());
            notaDCValidator.validaDocuSustentoNota(notaDC.getSustentoNota());
            res = documentoDao.insert(notaDC);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar la nota debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar la nota debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }

    public ApiRestFullResponse getC(long id) {
        Boolean ok = true;
        int code = 200;
        String msg = "Entrega del documento solicitado";
        ComprobantePago res = null;
        try {
            res = documentoDao.getCompPago(id);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el comprobante de pago debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }

    public ApiRestFullResponse getN(long id) {
        Boolean ok = true;
        int code = 200;
        String msg = "Entrega del documento solicitado";
        NotaDC res = null;
        try {
            res = documentoDao.getNotaDC(id);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el comprobante de pago debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    private HashMap<String, Object> registrarClienteCP(Contribuyente cliente) {
    	Contribuyente clie = null;
    	cliente.setSucursales(null);
    	Sucursal sucu =  new Sucursal();
        HashMap<String, Object> res = new HashMap<>();
        res.put("ok", true);
        res.put("code", 200);
        res.put("msg", "Entrega del cabecera cliente solicitado");
        res.put("clie", null);
        res.put("sucu", null);
        ApiRestFullResponse servicio = null;
        if(cliente.getId() > 0) {
            servicio = contribuyenteService.get(cliente.getId());
            res.replace("ok",servicio.getResponse().isSuccess());
            if(servicio.getResponse().isSuccess()) {
            	 clie = (Contribuyente) servicio.getData();
            	 if(clie == null) {
            		 res.replace("ok",false);
            		 res.replace("code",400);
            		 res.replace("msg","El Cliente no existe");
            	 } else {
            		 if (!isClient(cliente, clie, 0)) {
            			 res.replace("ok",false);
            			 res.replace("code",400);
            			 res.replace("msg","El Cliente es inconsistente");
            		 } else {
            			 res.replace("clie", clie.getId());        					 
            		 }
            	 }
            } else {
            	res.replace("code",servicio.getResponse().getCode());
            	res.replace("msg",servicio.getResponse().getMessage());
            }  
        } else {
        	Boolean creaclie = true;
        	if(!cliente.getTipoDocumentoIdentidad().getCodigo().equals("0")) {
        		servicio = contribuyenteService.get(cliente.getNumeroDocumento(), cliente.getTipoDocumentoIdentidad().getCodigo());
        		res.replace("ok",servicio.getResponse().isSuccess());
                if(servicio.getResponse().isSuccess()) {
                	 clie = (Contribuyente) servicio.getData();
                	 if(clie != null) {
                		 if (!isClient(cliente, clie, 1)) {
                			 res.replace("ok",false);
                			 res.replace("code",400);
                			 res.replace("msg","El Cliente es inconsistente");
                    		 creaclie = false;
                		 } else {                				 
                			 res.replace("clie", clie.getId());
                			 if(!isClient(cliente, clie, 2)) {
                				 sucu.setContribuyente(clie);
                				 sucu.setDireccion(cliente.getDireccion());
                				 sucu.setUrbanizacion(cliente.getUrbanizacion());
                				 sucu.setPais(cliente.getPais());
                				 sucu.setUbigeo(cliente.getUbigeo());
                				 sucu.setActivo(cliente.isActivo());
                				 res.replace("sucu", sucu);
                			 }
                		 }
                	 }
                } else {
                	res.replace("code",servicio.getResponse().getCode());
                	res.replace("msg",servicio.getResponse().getMessage());
           		 	creaclie = false;
                }
        	}
        	if(creaclie) {
        		servicio = contribuyenteService.insertC(cliente);
        		res.replace("ok",servicio.getResponse().isSuccess());
        		res.replace("code",servicio.getResponse().getCode());
            	res.replace("msg",servicio.getResponse().getMessage());
       		 	res.replace("clie",servicio.getData());
        	}
        }
        return res;
    }
    
    private Boolean isClient(Contribuyente clienteCP, Contribuyente clienteDB, int tipo){
    	if((tipo == 0 || tipo == 1) && clienteCP.getNombreLegal() != null && !clienteCP.getNombreLegal().equals(clienteDB.getNombreLegal()))
    		return false;
    	
    	if((tipo == 0) && clienteCP.getNumeroDocumento() != null && !clienteCP.getNumeroDocumento().equals(clienteDB.getNumeroDocumento()))
    		return false;
    	
    	if((tipo == 0 || tipo == 2) && clienteCP.getNombreComercial() != null && !clienteCP.getNombreComercial().equals(clienteDB.getNombreComercial()))
    		return false;
    	
    	if((tipo == 0) && clienteCP.getTipoDocumentoIdentidad() != null && !clienteCP.getTipoDocumentoIdentidad().equals(clienteDB.getTipoDocumentoIdentidad()))
    		return false;
    	
    	if((tipo == 0 || tipo == 1) && clienteCP.getUbigeo() != null && !clienteCP.getUbigeo().equals(clienteDB.getUbigeo()))
    		return false;
    	
    	return true;
    }
}
