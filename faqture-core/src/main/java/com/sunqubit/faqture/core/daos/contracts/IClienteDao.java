package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Cliente;
import java.util.List;

public interface IClienteDao {

    void insert(Cliente cliente) throws Exception;

    void update(Cliente cliente) throws Exception;
    
    Cliente get(long clieId) throws Exception;
    
    Cliente get(String numero, String tDocIdent) throws Exception;
    
    List<Cliente> getFilter(String filtro) throws Exception;
}
