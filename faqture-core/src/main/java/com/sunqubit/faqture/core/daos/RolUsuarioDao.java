package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.RolUsuario;
import com.sunqubit.faqture.core.daos.contracts.IRolUsuarioDao;
import com.sunqubit.faqture.core.mappers.RolUsuarioMapper;

@Repository
public class RolUsuarioDao implements IRolUsuarioDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RolUsuarioDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<RolUsuario> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	RolUsuarioMapper mapper = session.getMapper(RolUsuarioMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los roles del sistema");
		} finally {
			session.close();
		}
	}

}
