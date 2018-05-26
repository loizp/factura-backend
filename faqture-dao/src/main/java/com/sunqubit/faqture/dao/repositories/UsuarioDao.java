package com.sunqubit.faqture.dao.repositories;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.core.Usuario;
import com.sunqubit.faqture.dao.contracts.IUsuarioDao;
import com.sunqubit.faqture.dao.mappers.UsuarioMapper;

@Repository
public class UsuarioDao implements IUsuarioDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDao.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	

	@Override
	public long insert(Usuario usuario) throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			usuario.setId(mapper.selectKey());
			mapper.insert(usuario);
			return usuario.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la inserción de los datos del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la inserción de los datos del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización de los datos del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de los datos del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtención por login del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por login del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización del password del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización del password del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización del email del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización del email del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización del login del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización del login del Usuario");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtención por id del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por id del Usuario");
		} finally {
			session.close();
		}
	}

	@Override
	public void dateLogin(String loginName) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			mapper.dateLogin(loginName);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización de la fecha de login del Usuario");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de la fecha de login del Usuario");
		} finally {
			session.close();
		}
	}

}
