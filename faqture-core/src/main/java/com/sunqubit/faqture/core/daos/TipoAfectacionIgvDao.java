package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.TipoAfectacionIgv;
import com.sunqubit.faqture.core.daos.contracts.ITipoAfectacionIgvDao;
import com.sunqubit.faqture.core.mappers.TipoAfectacionIgvMapper;

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

}
