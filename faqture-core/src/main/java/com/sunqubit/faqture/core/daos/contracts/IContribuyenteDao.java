package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.Contribuyente;

public interface IContribuyenteDao {

    void insert(Contribuyente contribuyente, String tico) throws Exception;

    void update(Contribuyente contribuyente, String tico) throws Exception;

    void changeDoc(Contribuyente contribuyente) throws Exception;

    Contribuyente getVerif(long contId) throws Exception;

    Contribuyente get(long contId) throws Exception;

    Contribuyente get(String numDoc, String tDocIdent) throws Exception;

    List<Contribuyente> filterName(String nombre) throws Exception;

    Boolean docIdentidadExist(String numero, String tDocIdent) throws Exception;

    Boolean contExist(Long contId) throws Exception;
}
