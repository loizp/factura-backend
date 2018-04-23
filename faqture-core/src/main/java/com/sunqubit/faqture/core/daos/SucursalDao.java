package com.sunqubit.faqture.core.daos;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.Sucursal;
import com.sunqubit.faqture.core.daos.contracts.ISucursalDao;
import com.sunqubit.faqture.core.mappers.SucursalMapper;

@Repository
public class SucursalDao implements ISucursalDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SucursalDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Sucursal sucursal) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SucursalMapper mapper = session.getMapper(SucursalMapper.class);
			mapper.insert(sucursal);
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

}
