package com.sunqubit.faqture.dao.repositories;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.TipoNota;
import com.sunqubit.faqture.dao.contracts.ITipoNotaDao;
import com.sunqubit.faqture.dao.mappers.TipoNotaMapper;

@Repository
public class TipoNotaDao implements ITipoNotaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoNotaDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<TipoNota> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoNotaMapper mapper = session.getMapper(TipoNotaMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de los Tipos de Notas");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de los Tipos de Notas");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean tinoExist(String tidoCodigo, Long tinoId) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("tidoCodigo", tidoCodigo);
		hmFind.put("tinoId", tinoId);
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoNotaMapper mapper = session.getMapper(TipoNotaMapper.class);
        	if(mapper.tinoExist(hmFind) > 0)
        		return true;
        	return false;
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del Tipo de Nota");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del Tipo de Nota");
		} finally {
			session.close();
		}
	}

}
