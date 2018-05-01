package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.NotaDC;

public interface IDocumentoDao {
	void insert(ComprobantePago comprobantePago) throws Exception;
	
	void insert(NotaDC notaDC) throws Exception;
	
	void update(ComprobantePago comprobantePago) throws Exception;
	
	void update(NotaDC notaDC) throws Exception;
	
	ComprobantePago getCompPago(long docuId) throws Exception;
	
	NotaDC getNotaDC(long docuId) throws Exception;
	
	ComprobantePago getByNumDocC(Contribuyente contribuyente, ComprobantePago comprobantePago) throws Exception;

	NotaDC getByNumDocN(Contribuyente contribuyente, NotaDC notaDC) throws Exception;
}
