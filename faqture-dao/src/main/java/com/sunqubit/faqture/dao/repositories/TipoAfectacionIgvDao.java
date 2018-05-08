package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.TipoAfectacionIgv;
import com.sunqubit.faqture.dao.contracts.ITipoAfectacionIgvDao;
import com.sunqubit.faqture.dao.mappers.TipoAfectacionIgvMapper;

@Repository
public class TipoAfectacionIgvDao implements ITipoAfectacionIgvDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoAfectacionIgvDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<TipoAfectacionIgv> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoAfectacionIgvMapper mapper = session.getMapper(TipoAfectacionIgvMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de afectacion IGV");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean tiaiExist(String tiaiCodigo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoAfectacionIgvMapper mapper = session.getMapper(TipoAfectacionIgvMapper.class);
        	if (mapper.tipoIgvExist(tiaiCodigo) > 0)
        		return true;
        	return false;
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de afectacion IGV");
		} finally {
			session.close();
		}
	}

}
