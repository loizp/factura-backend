package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.Empresa;

public interface IContribuyenteDao {

    long insert(Empresa contribuyente) throws Exception;
    
    long insert(Contribuyente contribuyente) throws Exception;

    void update(Empresa contribuyente) throws Exception;
    
    void update(Contribuyente contribuyente) throws Exception;

    void changeDoc(Contribuyente contribuyente) throws Exception;

    Contribuyente getVerif(long contId) throws Exception;

    Contribuyente get(long contId) throws Exception;

    Contribuyente get(String numDoc, String tDocIdent) throws Exception;

    List<Contribuyente> filterName(String nombre) throws Exception;

    Boolean docIdentidadExist(String numero, String tDocIdent) throws Exception;

    Boolean contExist(long contId) throws Exception;
}
