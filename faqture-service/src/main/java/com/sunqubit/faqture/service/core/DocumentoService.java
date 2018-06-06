package com.sunqubit.faqture.service.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;
import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.Leyenda;
import com.sunqubit.faqture.beans.core.NotaDC;
import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.beans.rest.ServiceResponse;
import com.sunqubit.faqture.dao.contracts.IDocumentoDao;
import com.sunqubit.faqture.dao.validators.ValidatorException;
import com.sunqubit.faqture.service.security.UsuarioService;
import com.sunqubit.faqture.service.utils.LeyendaUtil;
import com.sunqubit.faqture.service.validators.ComprobantePagoValidator;
import com.sunqubit.faqture.service.validators.NotaDCValidator;
import com.sunqubit.faqture.sunat.core.FacturaBoletaElectronica;

@Service
public class DocumentoService {
	
	@Autowired
	private UsuarioService usuarioService;

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
    
    @Autowired
    private LeyendaUtil leyendaUtil;

    public ApiRestFullResponse insertSimple(ComprobantePago compPago) {
        Boolean ok = true;
        int code = 201;
        String msg = "El comprobante de pago fue registrado correctamente";
        Long res = null;
        try {
            compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            compPago.setEstadoProceso("N");
            compPago.setLeyendas(preparaLeyenda(compPago.getLeyendas(), compPago.getTotal(), compPago.getMoneda().getCodigo()));
            comprobantePagoValidator.validaDocuNumero(compPago.getEmpresa(), compPago.getTipoDocumento(), compPago.getNumero());
            comprobantePagoValidator.validaDocBaseSinple(compPago);
            if(usuarioService.allowService("bfis",compPago.getEmpresa().getId(), compPago.getEmprSucursal().getId())) {
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
            } else {
            	ok = false;
                code = 403;
                msg = "El usuario no esta autorizado insertar este documento";
            }
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
        ComprobantePago res = null;
        ApiRestFullResponse servicio;
        try {
        	compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            compPago.setEstadoProceso("N");
            compPago.setLeyendas(preparaLeyenda(compPago.getLeyendas(), compPago.getTotal(), compPago.getMoneda().getCodigo()));
            comprobantePagoValidator.validaDocuNumero(compPago.getEmpresa(), compPago.getTipoDocumento(), compPago.getNumero());
            comprobantePagoValidator.validaDocBaseSinple(compPago);
            long idsucu = 0;
            if(compPago.getEmprSucursal() != null) idsucu = compPago.getEmprSucursal().getId();
            if(usuarioService.allowService("bfif",compPago.getEmpresa().getId(), idsucu)) {
            	servicio = getC(compPago.getEmpresa().getId(), compPago.getTipoDocumento().getCodigo(), compPago.getNumero());
            	ok = servicio.getResponse().isSuccess();
            	if(servicio.getResponse().isSuccess()) {
            		long docuId = 0;
            		ComprobantePago cpdata = (ComprobantePago) servicio.getData();
            		if(cpdata != null) {
            			compPago.setId(cpdata.getId());
            			if(!cpdata.getEstadoProceso().equals("C")) {
            				servicio = updateC(compPago);
            				docuId = compPago.getId();
            				ok = servicio.getResponse().isSuccess();
            				code = servicio.getResponse().getCode();
                            msg = servicio.getResponse().getMessage();
            			} else {
            				ok = false;
            				code = 202;
            				msg = "El documento ya existe y ya completó su entrega a la SUNAT";
            			}
            		} else {
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
			            			} else {
			                            code = servicio.getResponse().getCode();
			                            msg = servicio.getResponse().getMessage();
			            			}
			            		}
			            	}
			            	comprobantePagoValidator.validaDocAutoEmision(compPago.getEmpresa().getId(), compPago.getCliente().getId());
			            } else {
			            	ok = (Boolean) dataC.get("ok");
			                code = (int) dataC.get("code");
			                msg = (String) dataC.get("msg");
			            }
			            if(ok) docuId = documentoDao.insert(compPago);
            		}
            		if(docuId > 0) {
            			res = documentoDao.getCompPago(docuId);
            			ServiceResponse sr = FacturaBoletaElectronica.generarXMLZipiado(res);
            			if(sr.getSuccess()) res = (ComprobantePago) sr.getData();
            			sr = FacturaBoletaElectronica.generarQRPdf417(res);
            			if(sr.getSuccess()) res = (ComprobantePago) sr.getData();
            			FacturaBoletaElectronica.enviarASunat(res);
            		}
            	} else {
            		code = servicio.getResponse().getCode();
                    msg = servicio.getResponse().getMessage();
            	}
            } else {
            	ok = false;
                code = 403;
                msg = "El usuario no esta autorizado insertar este documento";
            }
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
    
    public ApiRestFullResponse updateC(ComprobantePago compPago) {
    	Boolean ok = true;
        int code = 201;
        String msg = "El comprobante de pago fue modificado correctamente";
        try {
        	compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            compPago.setEstadoProceso("M");
            comprobantePagoValidator.validaDocuId(compPago.getId());
            documentoDao.update(compPago);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede modificar el comprobante de pago debido a: " + ve.getMessage();
		} catch (Exception e) {
			ok = false;
            code = 500;
            msg = "No se puede modificar el comprobante de pago debido a: " + e.getMessage();
		}
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
    }
    
    private List<Leyenda> preparaLeyenda(List<Leyenda> leyendas, BigDecimal Number, String moneda) {
    	List<Leyenda> leyendasDoc = new ArrayList<>();
    	switch (moneda) {
			case "USD": moneda = "Dolares Americanos";break;
			default:moneda = "Soles";break;
		}
    	TipoLeyenda tipoLeyenda = new TipoLeyenda();
    	tipoLeyenda.setCodigo("1000");
    	Leyenda leyenda = new Leyenda();
    	leyenda.setTipoLeyenda(tipoLeyenda);;
    	leyenda.setDescripcion(leyendaUtil.numberToLetterES(Number, ".", moneda, 2));
    	
    	if(leyendas == null)
    		leyendasDoc.add(leyenda);
    	else {
    		Boolean existe = false;
    		for (Leyenda leye : leyendas) {
    			leyendasDoc.add(leye);
				if(leye.getTipoLeyenda().getCodigo().equals("1000")) existe = true;
			}
    		if(!existe) leyendasDoc.add(leyenda);
    	}
    	return leyendasDoc;
    }

    public ApiRestFullResponse insert(NotaDC notaDC) {
        Boolean ok = true;
        int code = 201;
        String msg = "La nota fue registrado correctamente";
        Long res = null;
        try {
            notaDC.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            notaDC.setEstadoProceso("N");
            notaDC.setLeyendas(preparaLeyenda(notaDC.getLeyendas(), notaDC.getTotal(), notaDC.getMoneda().getCodigo()));
            notaDCValidator.validaDocuNumero(notaDC.getEmpresa(), notaDC.getTipoDocumento(), notaDC.getNumero());
            notaDCValidator.validaDocBaseSinple(notaDC);
            if(usuarioService.allowService("ndci",notaDC.getEmpresa().getId(), notaDC.getEmprSucursal().getId())) {
	            notaDCValidator.validaDocuTipoNota(notaDC.getTipoDocumento(), notaDC.getTipoNota());
	            notaDCValidator.validaDocuSustentoNota(notaDC.getSustentoNota());
	            res = documentoDao.insert(notaDC);
            } else {
            	ok = false;
                code = 403;
                msg = "El usuario no esta autorizado insertar este documento";
            }
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
    
    public ApiRestFullResponse updateN(NotaDC notaDC) {
    	Boolean ok = true;
        int code = 201;
        String msg = "La nota fue modificado correctamente";
        try {
        	notaDC.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        	notaDC.setEstadoProceso("M");
            comprobantePagoValidator.validaDocuId(notaDC.getId());
            documentoDao.update(notaDC);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede modificar la nota debido a: " + ve.getMessage();
		} catch (Exception e) {
			ok = false;
            code = 500;
            msg = "No se puede modificar la nota debido a: " + e.getMessage();
		}
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
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
    
    public ApiRestFullResponse getC(long emprId, String tidoc, String numDoc) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega del documento solicitado";
        ComprobantePago res = null;
        try {
            res = documentoDao.getByNumDocC(emprId, tidoc, numDoc);
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
            msg = "No se puede obtener la nota debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse getN(long emprId, String tidoc, String numDoc) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega del documento solicitado";
        NotaDC res = null;
        try {
            res = documentoDao.getByNumDocN(emprId, tidoc, numDoc);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener la nota debido a: " + ex.getMessage();
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
                		 if (isClient(cliente, clie, 1)) {
                			 res.replace("clie", clie.getId());
                		 } else {                				 
                			 sucu.setContribuyente(clie);
                			 sucu.setNombreLegal(cliente.getNombreLegal());
                			 sucu.setDireccion(cliente.getDireccion());
                			 sucu.setUrbanizacion(cliente.getUrbanizacion());
                			 sucu.setPais(cliente.getPais());
                			 sucu.setUbigeo(cliente.getUbigeo());
                			 res.replace("sucu", sucu);
                		 }
                		 creaclie = false;
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
    	
    	if((tipo == 0) && clienteCP.getNombreComercial() != null && !clienteCP.getNombreComercial().equals(clienteDB.getNombreComercial()))
    		return false;
    	
    	if((tipo == 0 && tipo == 1) && clienteCP.getDireccion() != null && !clienteCP.getDireccion().equals(clienteDB.getDireccion()))
    		return false;
    	
    	if((tipo == 0) && clienteCP.getTipoDocumentoIdentidad() != null && !clienteCP.getTipoDocumentoIdentidad().equals(clienteDB.getTipoDocumentoIdentidad()))
    		return false;
    	
    	if((tipo == 0 || tipo == 1) && clienteCP.getUbigeo() != null) {
    		if(clienteCP.getUbigeo().getCodigo() != null) {
    			if(!clienteCP.getUbigeo().getCodigo().equals(clienteDB.getUbigeo().getCodigo()))
    				return false;
    		} else {
    			if(clienteCP.getUbigeo().getId() != clienteDB.getUbigeo().getId())
    				return false;
    		}
    	}
    	return true;
    }
}
