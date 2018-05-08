package com.sunqubit.faqture.dao.contracts;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.NotaDC;

public interface IDocumentoDao {
	Long insert(ComprobantePago comprobantePago) throws Exception;
	
	Long insert(NotaDC notaDC) throws Exception;
	
	void update(ComprobantePago comprobantePago) throws Exception;
	
	void update(NotaDC notaDC) throws Exception;
	
	ComprobantePago getCompPago(Long docuId) throws Exception;
	
	NotaDC getNotaDC(Long docuId) throws Exception;
	
	ComprobantePago getByNumDocC(Contribuyente contribuyente, ComprobantePago comprobantePago) throws Exception;

	NotaDC getByNumDocN(Contribuyente contribuyente, NotaDC notaDC) throws Exception;
}
