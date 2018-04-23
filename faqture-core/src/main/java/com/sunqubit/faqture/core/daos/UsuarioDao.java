package com.sunqubit.faqture.core.daos;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.Usuario;
import com.sunqubit.faqture.core.daos.contracts.IUsuarioDao;
import com.sunqubit.faqture.core.mappers.UsuarioMapper;

@Repository
public class UsuarioDao implements IUsuarioDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDao.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	

	@Override
	public void insert(Usuario usuario) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.insert(usuario);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la inserción de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.update(usuario);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Usuario login(String loginName) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			return mapper.login(loginName);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la autenticación del usuario");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean loginNameExist(String loginName) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			if (mapper.loginNameExist(loginName) > 0)
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la verificación de existencia del usuario");
		} finally {
			session.close();
		}
	}

	@Override
	public void changePassword(Usuario usuario) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.changePassword(usuario);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void changeEmail(Usuario usuario) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.changeEmail(usuario);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public void changeLoginName(Usuario usuario) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.changeLoginName(usuario);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
		
	}

	@Override
	public Usuario getById(long id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			return mapper.getById(id);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la identificación del usuario");
		} finally {
			session.close();
		}
	}

}
