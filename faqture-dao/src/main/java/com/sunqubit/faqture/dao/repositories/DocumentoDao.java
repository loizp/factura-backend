package com.sunqubit.faqture.dao.repositories;

import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.NotaDC;
import com.sunqubit.faqture.dao.contracts.IDocumentoDao;
import com.sunqubit.faqture.dao.mappers.DocumentoMapper;

@Repository
public class DocumentoDao implements IDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public long insert(ComprobantePago comprobantePago) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			comprobantePago.setId(mapper.selectKey());
			System.out.println(comprobantePago);
			mapper.insertCompPago(comprobantePago);
			return comprobantePago.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la inserción de los datos del Comprobante de Pago");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la inserción de los datos del Comprobante de Pago");
		} finally {
			session.close();
		}
	}
	
	@Override
	public long insert(NotaDC notaDC) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			notaDC.setId(mapper.selectKey());
			mapper.insertNotaDC(notaDC);
			return notaDC.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la inserción de los datos de la Nota de crédito o Débito");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la inserción de los datos de la Nota de crédito o Débito");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la actualización de los datos del Comprobante de Pago");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de los datos del Comprobante de Pago");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la actualización de los datos de la Nota de crédito o Débito");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de los datos de la Nota de crédito o Débito");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la obtención por id de los datos del Comprobante de Pago");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por id de los datos del Comprobante de Pago");
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
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la obtención por id de los datos de la Nota de Crédito o Débito");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por id de los datos de la Nota de Crédito o Débito");
		} finally {
			session.close();
		}
	}

	@Override
	public ComprobantePago getByNumDocC(long emprId, String tidoc, String numDoc) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("emprId", emprId);
		hmFind.put("tidoc", tidoc);
		hmFind.put("numDoc", numDoc);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getByNumDocC(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la obtención por numDoc de los datos del Comprobante de Pago");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por numDoc de los datos del Comprobante de Pago");
		} finally {
			session.close();
		}
	}
	
	@Override
	public NotaDC getByNumDocN(long emprId, String tidoc, String NumDoc) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("emprId", emprId);
		hmFind.put("tidoc", tidoc);
		hmFind.put("NumDoc", NumDoc);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
			return mapper.getByNumDocN(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de percistencia en la obtención por numDoc de los datos de la Nota de Crédito o Débito");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por numDoc de los datos de la Nota de Crédito o Débito");
		} finally {
			session.close();
		}
	}
}
