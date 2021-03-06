package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.Ubigeo;
import com.sunqubit.faqture.dao.contracts.IUbigeoDao;
import com.sunqubit.faqture.dao.mappers.UbigeoMapper;

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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el filtrado de ubigeos");
	    } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del Ubigeo");
	    } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del Ubigeo");
		} finally {
			session.close();
		}
	}

	@Override
	public Ubigeo get(String codigo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
	    try {
	    	UbigeoMapper mapper = session.getMapper(UbigeoMapper.class);
	    	return mapper.get(codigo);
	    } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtencion por codigo del Ubigeo");
	    } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtencion por codigo del Ubigeo");
		} finally {
			session.close();
		}
	}
}
