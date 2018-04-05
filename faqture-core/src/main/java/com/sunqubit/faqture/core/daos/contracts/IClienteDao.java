package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Cliente;

public interface IClienteDao {
	
	void insert(Cliente cliente);
	
	void update(Cliente cliente);
}
