package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Cliente;
import java.util.List;

public interface IClienteDao {

    void insert(Cliente cliente);

    void update(Cliente cliente);
    
    Cliente get(long clieId);
    
    public List<Cliente> getList();
    
}
