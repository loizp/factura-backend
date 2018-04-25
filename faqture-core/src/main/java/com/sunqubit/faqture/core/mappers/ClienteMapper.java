package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.Cliente;

import java.util.HashMap;
import java.util.List;

public interface ClienteMapper {
	void insert(Cliente cliente);
	
	void update(Cliente cliente);
	
	Cliente get(long clieId);
	
	Cliente getByDoc(HashMap<String, String> hmFind);
	
	List<Cliente> getFilter(String filtro);

}
