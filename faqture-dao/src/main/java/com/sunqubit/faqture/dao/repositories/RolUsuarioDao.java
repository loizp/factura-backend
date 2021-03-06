package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.core.RolUsuario;
import com.sunqubit.faqture.dao.contracts.IRolUsuarioDao;
import com.sunqubit.faqture.dao.mappers.RolUsuarioMapper;

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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de los Roles del sistema");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de los Roles del sistema");
		} finally {
			session.close();
		}
	}

}
