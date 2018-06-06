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

import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.beans.core.Empresa;
import com.sunqubit.faqture.beans.utils.StoreManager;
import com.sunqubit.faqture.dao.contracts.IContribuyenteDao;
import com.sunqubit.faqture.dao.mappers.ContribuyenteMapper;

@Repository
public class ContribuyenteDao implements IContribuyenteDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContribuyenteDao.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public Boolean docIdentidadExist(String numDoc, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numDoc", numDoc);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			if (mapper.docIdentidadExist(hmFind) > 0) 
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del docuIdentidad del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del docuIdentidad del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean contExist(long contId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			if (mapper.contExist(contId) > 0) 
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public long insert(Empresa empresa) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			empresa.setId(mapper.selectKey());
			mapper.insertE(empresa);
			if(StoreManager.store == 1) StoreManager.getDirectorioLocal(1, empresa.getTipoDocumentoIdentidad().getCodigo() + "-" + empresa.getNumeroDocumento(), null);
			return empresa.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la inserción de los datos del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la inserción de los datos del contribuyente");
		} finally {
			session.close();
		}
	}
	
	@Override
	public long insert(Contribuyente contribuyente) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			contribuyente.setId(mapper.selectKey());
			mapper.insertC(contribuyente);
			return contribuyente.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la inserción de los datos del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la inserción de los datos del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Empresa empresa) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			mapper.updateE(empresa);
			if(StoreManager.store == 1) StoreManager.getDirectorioLocal(1, empresa.getTipoDocumentoIdentidad().getCodigo() + "-" + empresa.getNumeroDocumento(), null);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización de los datos del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de los datos del contribuyente");
		} finally {
			session.close();
		}
	}
	
	@Override
	public void update(Contribuyente contribuyente) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			mapper.updateC(contribuyente);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización de los datos del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización de los datos del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public void changeDoc(Contribuyente contribuyente) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			mapper.changeDoc(contribuyente);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la actualización del documento del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la actualización del documento del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public Contribuyente getVerif(long contId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			return mapper.getVerif(contId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtención de verificación del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención de verificación del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public Contribuyente get(long contId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			return mapper.getById(contId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtención por id del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por id del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public Contribuyente get(String numDoc, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numDoc", numDoc);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			return mapper.getByDoc(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la obtención por numDoc del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la obtención por numDoc del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Contribuyente> filterName(String nombre) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			return mapper.filterName(nombre);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el filtrado del contribuyente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la filtrado del contribuyente");
		} finally {
			session.close();
		}
	}

}
