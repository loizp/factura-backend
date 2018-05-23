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

import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.dao.contracts.ISucursalDao;
import com.sunqubit.faqture.dao.mappers.SucursalMapper;

@Repository
public class SucursalDao implements ISucursalDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SucursalDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public long insert(Sucursal sucursal) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SucursalMapper mapper = session.getMapper(SucursalMapper.class);
			Sucursal sucu = mapper.getExist(sucursal);
			if(sucu != null)
				return sucu.getId();
			sucursal.setId(mapper.selectKey());
			mapper.insert(sucursal);
			return sucursal.getId();
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la inserción de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Sucursal sucursal) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SucursalMapper mapper = session.getMapper(SucursalMapper.class);
			mapper.update(sucursal);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualizacion de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean sucuExist(long sucuId, long contId) throws Exception {
		HashMap<String, Object> hmFind = new HashMap<>();
		hmFind.put("sucuId", sucuId);
		hmFind.put("contId", contId);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SucursalMapper mapper = session.getMapper(SucursalMapper.class);
			if(mapper.sucuExist(hmFind) > 0)
				return true;
			return false;
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la actualización de los datos");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Sucursal> getList(long contId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SucursalMapper mapper = session.getMapper(SucursalMapper.class);
			return mapper.getList(contId);
		} catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			throw new Exception("Ocurrió un error en la obtención de los datos");
		} finally {
			session.close();
		}
	}

}
