package com.sunqubit.faqture.core.mappers;

import java.util.HashMap;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.NotaDC;

public interface DocumentoMapper {
	void insertCompPago(ComprobantePago comprobantePago);
	
	void insertNotaDC(NotaDC notaDC);
	
	void updateCompPago(ComprobantePago comprobantePago);
	
	void updateNotaDC(NotaDC notaDC);
	
	ComprobantePago getCompPago(long docuId);
	
	NotaDC getNotaDC(long docuId);
	
	ComprobantePago getByNumDocC(HashMap<String, Object> hmFind);

	NotaDC getByNumDocN(HashMap<String, Object> hmFind);
}
