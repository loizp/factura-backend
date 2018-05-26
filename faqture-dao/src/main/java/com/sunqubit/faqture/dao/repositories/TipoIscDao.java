package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.TipoIsc;
import com.sunqubit.faqture.dao.contracts.ITipoIscDao;
import com.sunqubit.faqture.dao.mappers.TipoIscMapper;

@Repository
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de los Tipos de ISC");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de los Tipos de ISC");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean tiscExist(String tiscCodigo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoIscMapper mapper = session.getMapper(TipoIscMapper.class);
        	if(mapper.tipoIscExist(tiscCodigo) > 0)
        		return true;
        	return false;
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en verificación de existencia de los Tipos de ISC");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en verificación de existencia de los Tipos de ISC");
		} finally {
			session.close();
		}
	}

}
