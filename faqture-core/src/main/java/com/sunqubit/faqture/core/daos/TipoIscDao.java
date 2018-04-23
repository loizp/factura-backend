package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.TipoIsc;
import com.sunqubit.faqture.core.daos.contracts.ITipoIscDao;
import com.sunqubit.faqture.core.mappers.TipoIscMapper;

public class TipoIscDao implements ITipoIscDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoIscDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<TipoIsc> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoIscMapper mapper = session.getMapper(TipoIscMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de ISC");
		} finally {
			session.close();
		}
	}

}
