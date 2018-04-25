package com.sunqubit.faqture.core.daos.contracts;

public interface IContribuyenteDao {
	Boolean docIdentidadExist(String numero, String tDocIdent) throws Exception;
}
