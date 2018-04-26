package com.sunqubit.faqture.core.daos;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.TipoNota;
import com.sunqubit.faqture.core.daos.contracts.ITipoNotaDao;
import com.sunqubit.faqture.core.mappers.TipoNotaMapper;

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
			throw new Exception("Ocurrió un error en el listado de los tipos de Notas");
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
			throw new Exception("Ocurrió un error en el verificación de existencia del tipo de Nota");
		} finally {
			session.close();
		}
	}

}
