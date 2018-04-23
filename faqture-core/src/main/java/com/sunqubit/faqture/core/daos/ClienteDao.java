package com.sunqubit.faqture.core.daos;

import com.sunqubit.faqture.core.beans.Cliente;
import com.sunqubit.faqture.core.daos.contracts.IClienteDao;
import com.sunqubit.faqture.core.mappers.ClienteMapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDao implements IClienteDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteDao.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Cliente cliente) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			mapper.insert(cliente);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Cliente cliente) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			mapper.update(cliente);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Cliente get(long clieId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			return mapper.get(clieId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Cliente get(String numero, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numero", numero);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			return mapper.get(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean docIdentidadExist(String numero, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numero", numero);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			if (mapper.docIdentidadExist(hmFind) > 0) 
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la verificación de existencia del cliente");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Cliente> getFilter(String filtro) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClienteMapper mapper = session.getMapper(ClienteMapper.class);
			return mapper.getFilter(filtro);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}
}
