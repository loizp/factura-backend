package com.sunqubit.faqture.dao.contracts;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.NotaDC;

public interface IDocumentoDao {
	long insert(ComprobantePago comprobantePago) throws Exception;
	
	long insert(NotaDC notaDC) throws Exception;
	
	void update(ComprobantePago comprobantePago) throws Exception;
	
	void update(NotaDC notaDC) throws Exception;
	
	ComprobantePago getCompPago(long docuId) throws Exception;
	
	NotaDC getNotaDC(long docuId) throws Exception;
	
	ComprobantePago getByNumDocC(long emprId, String tidoc, String numDoc) throws Exception;

	NotaDC getByNumDocN(long emprId, String tidoc, String numDoc) throws Exception;
}
