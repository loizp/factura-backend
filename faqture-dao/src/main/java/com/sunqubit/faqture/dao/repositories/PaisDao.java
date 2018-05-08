package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.Pais;
import com.sunqubit.faqture.dao.contracts.IPaisDao;
import com.sunqubit.faqture.dao.mappers.PaisMapper;

@Repository
public class PaisDao implements IPaisDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaisDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Pais> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	PaisMapper mapper = session.getMapper(PaisMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los paises");
		} finally {
			session.close();
		}
	}

}
