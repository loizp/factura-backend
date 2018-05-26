package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.UnidadMedida;
import com.sunqubit.faqture.dao.contracts.IUnidadMedidaDao;
import com.sunqubit.faqture.dao.mappers.UnidadMedidaMapper;

@Repository
public class UnidadMedidaDao implements IUnidadMedidaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UnidadMedidaDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<UnidadMedida> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	UnidadMedidaMapper mapper = session.getMapper(UnidadMedidaMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de las Unidades de medida");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de las Unidades de medida");
		} finally {
			session.close();
		}
	}

}
