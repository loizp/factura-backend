package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;
import com.sunqubit.faqture.core.mappers.TipoDocumentoIdentidadMapper;

@Component
public class TipoDocumentoIdentidadDao implements ITipoDocumentoIdentidadDao {
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<TipoDocumentoIdentidad> getAll() {
		List<TipoDocumentoIdentidad> listado = null;
		SqlSession session = sqlSessionFactory.openSession();
		TipoDocumentoIdentidadMapper mapper = session.getMapper(TipoDocumentoIdentidadMapper.class);
		listado = mapper.getAll();
		session.close();
		return listado;
	}

	@Override
	public TipoDocumentoIdentidad get(String tiidCodigo) {
		TipoDocumentoIdentidad tipoDocumentoIdentidad = null;
		SqlSession session = sqlSessionFactory.openSession();
		TipoDocumentoIdentidadMapper mapper = session.getMapper(TipoDocumentoIdentidadMapper.class);
		tipoDocumentoIdentidad = mapper.get(tiidCodigo);
		return tipoDocumentoIdentidad;
	}

}
