package com.sunqubit.faqture.dao.repositories;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.beans.catalogs.TipoDocumentoIdentidad;
import com.sunqubit.faqture.dao.contracts.ITipoDocumentoIdentidadDao;
import com.sunqubit.faqture.dao.mappers.TipoDocumentoIdentidadMapper;

@Repository
public class TipoDocumentoIdentidadDao implements ITipoDocumentoIdentidadDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TipoDocumentoIdentidadDao.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<TipoDocumentoIdentidad> getAll() throws Exception {
    	SqlSession session = sqlSessionFactory.openSession();
        try {
            TipoDocumentoIdentidadMapper mapper = session.getMapper(TipoDocumentoIdentidadMapper.class);
            return mapper.getAll();
        } catch (PersistenceException pe) {
			LOGGER.info(pe.getMessage());
			pe.printStackTrace();
			throw new Exception("Ocurrió un error de persistencia en el listado de los Tipos de documentos de identidad");
        } catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrió un error en el listado de los Tipos de documentos de identidad");
		} finally {
			session.close();
		}
    }
}
