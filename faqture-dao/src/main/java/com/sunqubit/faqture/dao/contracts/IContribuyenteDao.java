package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.Contribuyente;

public interface IContribuyenteDao {

    Long insert(Contribuyente contribuyente, String tico) throws Exception;

    void update(Contribuyente contribuyente, String tico) throws Exception;

    void changeDoc(Contribuyente contribuyente) throws Exception;

    Contribuyente getVerif(Long contId) throws Exception;

    Contribuyente get(Long contId) throws Exception;

    Contribuyente get(String numDoc, String tDocIdent) throws Exception;

    List<Contribuyente> filterName(String nombre) throws Exception;

    Boolean docIdentidadExist(String numero, String tDocIdent) throws Exception;

    Boolean contExist(Long contId) throws Exception;
}
