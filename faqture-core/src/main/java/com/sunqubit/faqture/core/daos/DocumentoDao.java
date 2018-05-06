package com.sunqubit.faqture.core.daos;

import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.beans.NotaDC;
import com.sunqubit.faqture.core.daos.contracts.IDocumentoDao;
import com.sunqubit.faqture.core.mappers.DocumentoMapper;

@Repository
public class DocumentoDao implements IDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(ComprobantePago comprobantePago) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.insertCompPago(comprobantePago);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}
	
	@Override
	public void insert(NotaDC notaDC) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.insertNotaDC(notaDC);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(ComprobantePago comprobantePago) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.updateCompPago(comprobantePago);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}
	
	@Override
	public void update(NotaDC notaDC) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			mapper.updateNotaDC(notaDC);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public ComprobantePago getCompPago(long docuId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getCompPago(docuId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}
	
	@Override
	public NotaDC getNotaDC(long docuId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getNotaDC(docuId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public ComprobantePago getByNumDocC(Contribuyente empresa, ComprobantePago comprobantePago) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("empresa", empresa);
		hmFind.put("documento", comprobantePago);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getByNumDocC(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}
	
	@Override
	public NotaDC getByNumDocN(Contribuyente empresa, NotaDC notaDC) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("empresa", empresa);
		hmFind.put("documento", notaDC);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getByNumDocN(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}
}
