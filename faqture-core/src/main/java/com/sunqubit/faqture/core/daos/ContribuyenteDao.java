package com.sunqubit.faqture.core.daos;

import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunqubit.faqture.core.daos.contracts.IContribuyenteDao;
import com.sunqubit.faqture.core.mappers.ContribuyenteMapper;

public class ContribuyenteDao implements IContribuyenteDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteDao.class);

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

}
