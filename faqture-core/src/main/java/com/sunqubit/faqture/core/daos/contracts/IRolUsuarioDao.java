package com.sunqubit.faqture.core.daos.contracts;

import java.util.List;

import com.sunqubit.faqture.core.beans.RolUsuario;

public interface IRolUsuarioDao {
	List<RolUsuario> getAll() throws Exception;
}
