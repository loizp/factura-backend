package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.core.DetalleDocumento;
import com.sunqubit.faqture.dao.contracts.IDetalleDocumentoDao;
import com.sunqubit.faqture.dao.mappers.DetalleDocumentoMapper;

@Repository
public class DetalleDocumentoDao implements IDetalleDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleDocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insert(DetalleDocumento detalleDocumento) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DetalleDocumentoMapper mapper = session.getMapper(DetalleDocumentoMapper.class);
			mapper.insert(detalleDocumento);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(DetalleDocumento detalleDocumento) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DetalleDocumento detalleDocumento) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DetalleDocumento> getDetalleDoc(Long docId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DetalleDocumentoMapper mapper = session.getMapper(DetalleDocumentoMapper.class);
			return mapper.getDetalleDoc(docId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}

}
