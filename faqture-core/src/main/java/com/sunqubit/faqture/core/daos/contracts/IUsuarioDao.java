package com.sunqubit.faqture.core.daos.contracts;

import com.sunqubit.faqture.core.beans.Usuario;

public interface IUsuarioDao {
	void insert(Usuario usuario) throws Exception;
	
	void update(Usuario usuario) throws Exception;
	
	void changePassword(Usuario usuario) throws Exception;
	
	void changeEmail(Usuario usuario) throws Exception;
	
	void changeLoginName (Usuario usuario) throws Exception;
	
	Usuario login(String loginName) throws Exception;
	
	Boolean loginNameExist(String loginName) throws Exception;
	
	Usuario getById(long id) throws Exception;
}
