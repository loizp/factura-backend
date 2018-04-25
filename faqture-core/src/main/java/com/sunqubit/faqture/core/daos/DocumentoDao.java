package com.sunqubit.faqture.core.daos;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.Documento;
import com.sunqubit.faqture.core.daos.contracts.IDocumentoDao;
import com.sunqubit.faqture.core.mappers.DocumentoMapper;

@Repository
public class DocumentoDao implements IDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Documento documento) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.insert(documento);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Documento documento) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.update(documento);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Documento get(long docuId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.get(docuId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}
	
}
