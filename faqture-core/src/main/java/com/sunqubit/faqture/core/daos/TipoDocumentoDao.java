package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.TipoDocumento;
import com.sunqubit.faqture.core.daos.contracts.ItipoDocumentoDao;
import com.sunqubit.faqture.core.mappers.TipoDocumentoMapper;

@Repository
public class TipoDocumentoDao implements ItipoDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoDocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<TipoDocumento> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoDocumentoMapper  mapper = session.getMapper(TipoDocumentoMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el listado de los tipos de documentos");
		} finally {
			session.close();
		}
	}

}
