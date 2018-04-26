package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;
import com.sunqubit.faqture.core.mappers.UbigeoMapper;

@Repository
public class UbigeoDao implements IUbigeoDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UbigeoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Ubigeo> filter(String filtro) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
	    try {
	    	UbigeoMapper mapper = session.getMapper(UbigeoMapper.class);
	    	return mapper.filter(filtro);
	    } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el filtrado de ubigeos");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean ubigeoExist(Long ubigId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
	    try {
	    	UbigeoMapper mapper = session.getMapper(UbigeoMapper.class);
	    	if(mapper.ubigeoExist(ubigId) > 0)
	    		return true;
	    	return false;
	    } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el filtrado de ubigeos");
		} finally {
			session.close();
		}
	}
}
