package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.Cliente;
import java.util.List;

public interface ClienteMapper {
	
	void insert(Cliente cliente);
	Cliente get(long clieId);
    public List<Cliente> getList();

}
