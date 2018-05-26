package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;
import com.sunqubit.faqture.dao.contracts.ITipoLeyendaDao;
import com.sunqubit.faqture.dao.mappers.TipoLeyendaMapper;

@Repository
public class TipoLeyendaDao implements ITipoLeyendaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoLeyendaDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<TipoLeyenda> getAll() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoLeyendaMapper mapper = session.getMapper(TipoLeyendaMapper.class);
        	return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de los Tipos de leyendas");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de los Tipos de leyendas");
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean tleyExist(String tleyCodigo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
        try {
        	TipoLeyendaMapper mapper = session.getMapper(TipoLeyendaMapper.class);
        	if(mapper.tipoLeyendaExist(tleyCodigo) > 0)
                return true;
        	return false;
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en la verificación de existencia del Tipo de leyenda");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en la verificación de existencia del Tipo de leyenda");
        } finally {
        	session.close();
        }
	}

}
