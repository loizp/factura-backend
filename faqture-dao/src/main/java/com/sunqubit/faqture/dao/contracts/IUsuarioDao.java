package com.sunqubit.faqture.dao.contracts;

import com.sunqubit.faqture.beans.core.Usuario;

public interface IUsuarioDao {

    long insert(Usuario usuario) throws Exception;

    void update(Usuario usuario) throws Exception;

    void changePassword(Usuario usuario) throws Exception;

    void changeEmail(Usuario usuario) throws Exception;

    void changeLoginName(Usuario usuario) throws Exception;

    void dateLogin(String loginName) throws Exception;

    Usuario login(String loginName) throws Exception;

    Boolean loginNameExist(String loginName) throws Exception;

    Usuario getById(long id) throws Exception;
}
