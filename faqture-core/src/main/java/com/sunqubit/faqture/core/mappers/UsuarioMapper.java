package com.sunqubit.faqture.core.mappers;

import com.sunqubit.faqture.core.beans.Usuario;

public interface UsuarioMapper {
	
	void insert(Usuario usuario);
	
	void update(Usuario usuario);
	
	void changePassword(Usuario usuario);
	
	void changeEmail(Usuario usuario);
	
	void changeLoginName (Usuario usuario);
	
	Usuario login(String loginName);
	
	Long loginNameExist(String loginName);
	
	void dateLogin (String loginName);
	
	Usuario getById(long id);
}
