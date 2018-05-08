package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.Moneda;
import com.sunqubit.faqture.dao.contracts.IMonedaDao;
import com.sunqubit.faqture.dao.mappers.MonedaMapper;

@Repository
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
