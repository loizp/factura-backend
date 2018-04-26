package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.beans.DetalleDocumento;
import com.sunqubit.faqture.core.daos.contracts.IDetalleDocumentoDao;
import com.sunqubit.faqture.core.mappers.DetalleDocumentoMapper;

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
	public List<DetalleDocumento> getDetalleDoc(long docId) throws Exception {
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
