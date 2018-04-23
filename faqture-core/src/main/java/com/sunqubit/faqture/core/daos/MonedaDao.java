package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.Moneda;
import com.sunqubit.faqture.core.daos.contracts.IMonedaDao;
import com.sunqubit.faqture.core.mappers.MonedaMapper;

public class MonedaDao implements IMonedaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonedaDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Moneda> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	MonedaMapper mapper = session.getMapper(MonedaMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de monedas");
		} finally {
			session.close();
		}
	}

}
