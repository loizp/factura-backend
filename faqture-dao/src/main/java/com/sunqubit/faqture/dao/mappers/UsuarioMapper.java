package com.sunqubit.faqture.dao.mappers;

import com.sunqubit.faqture.beans.core.Usuario;

public interface UsuarioMapper {
	
	void insert(Usuario usuario);
	
	void update(Usuario usuario);
	
	void changePassword(Usuario usuario);
	
	void changeEmail(Usuario usuario);
	
	void changeLoginName (Usuario usuario);
	
	Usuario login(String loginName);
	
	long loginNameExist(String loginName);
	
	long selectKey();
	
	void dateLogin(String loginName);
	
	Usuario getById(long id);
}
