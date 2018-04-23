package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Empresa;
import java.util.List;

public interface IEmpresaDao {

    void insert(Empresa empresa) throws Exception;

    void update(Empresa empresa) throws Exception;
    
    void changeRuc(Empresa empresa) throws Exception;

    Empresa get(long emprId) throws Exception;

    Empresa get(String numRuc, String tDocIdent) throws Exception;
    
    Empresa getSucursales(long emprId) throws Exception;
    
    Boolean rucExist(String numRuc, String tDocIdent) throws Exception;

    List<Empresa> filterName(String nombre) throws Exception;
}
