package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.TipoOperacion;
import com.sunqubit.faqture.core.daos.contracts.ITipoOperacionDao;
import com.sunqubit.faqture.core.mappers.TipoOperacionMapper;

public class TipoOperacionDao implements ITipoOperacionDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoOperacionDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<TipoOperacion> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoOperacionMapper mapper = session.getMapper(TipoOperacionMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de operaciones");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean tiopExist(String tiopCodigo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoOperacionMapper mapper = session.getMapper(TipoOperacionMapper.class);
        	if(mapper.tiopExist(tiopCodigo) > 0)
        		return true;
        	return false;
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de operaciones");
		} finally {
			session.close();
		}
	}

}
