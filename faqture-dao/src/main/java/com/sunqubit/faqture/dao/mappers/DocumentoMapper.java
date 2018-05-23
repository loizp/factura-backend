package com.sunqubit.faqture.dao.mappers;

import java.util.HashMap;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.NotaDC;


public interface DocumentoMapper {
	void insertCompPago(ComprobantePago comprobantePago);
	
	void insertNotaDC(NotaDC notaDC);
	
	void updateCompPago(ComprobantePago comprobantePago);
	
	void updateNotaDC(NotaDC notaDC);
	
	ComprobantePago getCompPago(long docuId);
	
	NotaDC getNotaDC(long docuId);
	
	ComprobantePago getByNumDocC(HashMap<String, Object> hmFind);

	NotaDC getByNumDocN(HashMap<String, Object> hmFind);
	
	long selectKey();
}
