package com.sunqubit.faqture.dao.contracts;

import java.util.List;

import com.sunqubit.faqture.beans.core.RolUsuario;

public interface IRolUsuarioDao {
	List<RolUsuario> getAll() throws Exception;
}
