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

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.daos.contracts.IEmpresaDao;
import com.sunqubit.faqture.core.mappers.EmpresaMapper;

@Repository
public class EmpresaDao implements IEmpresaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaDao.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Empresa empresa) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			mapper.insert(empresa);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la insercion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Empresa empresa) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			mapper.update(empresa);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}
	
	@Override
	public void changeRuc(Empresa empresa) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			mapper.changeRuc(empresa);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Empresa get(long emprId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			return mapper.getById(emprId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de la empresa");
		} finally {
			session.close();
		}
	}

	@Override
	public Empresa get(String numRuc, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numRuc", numRuc);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			return mapper.getByRuc(hmFind);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de la empresa");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Empresa> filterName(String nombre) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			return mapper.filterName(nombre);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en el filtrado de la empresa");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean rucExist(String numRuc, String tDocIdent) throws Exception {
		HashMap<String, String> hmFind = new HashMap<>();
		hmFind.put("numRuc", numRuc);
		hmFind.put("tDocIdent", tDocIdent);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			if (mapper.rucExist(hmFind) > 0) 
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la verificación de existencia del RUC");
		} finally {
			session.close();
		}
	}

	@Override
	public Empresa getSucursales(long emprId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
			return mapper.getSucursales(emprId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de la empresa");
		} finally {
			session.close();
		}
	}

}
