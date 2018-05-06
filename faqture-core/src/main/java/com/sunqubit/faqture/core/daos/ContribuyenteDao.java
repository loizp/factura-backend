package com.sunqubit.faqture.core.daos;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.daos.contracts.IContribuyenteDao;
import com.sunqubit.faqture.core.mappers.ContribuyenteMapper;

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
			throw new Exception("Ocurrió un error en la verificación de existencia del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean contExist(Long contId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			if (mapper.contExist(contId) > 0) 
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la verificación de existencia del contribuyente");
		} finally {
			session.close();
		}
	}

	@Override
	public void insert(Contribuyente contribuyente, String tico) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			if(tico == "E")
				mapper.insertE(contribuyente);
			else if (tico == "C") {
				mapper.insertC(contribuyente);
			}
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Contribuyente contribuyente, String tico) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ContribuyenteMapper mapper = session.getMapper(ContribuyenteMapper.class);
			if(tico == "E")
				mapper.updateE(contribuyente);
			else if (tico == "C") {
				mapper.updateC(contribuyente);
			}
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
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
			throw new Exception("Ocurrió un error en la actualización de los datos");
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
			throw new Exception("Ocurrió un error en la obtención del contribuyente");
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
			throw new Exception("Ocurrió un error en la obtención del contribuyente");
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
			throw new Exception("Ocurrió un error en la obtención del contribuyente");
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
			throw new Exception("Ocurrió un error en el filtrado del contribuyente");
		} finally {
			session.close();
		}
	}

}
