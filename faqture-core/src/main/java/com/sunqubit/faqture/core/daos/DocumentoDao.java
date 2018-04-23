package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunqubit.faqture.core.beans.DetalleDocumento;
import com.sunqubit.faqture.core.beans.Documento;
import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.daos.contracts.IDocumentoDao;
import com.sunqubit.faqture.core.mappers.DetalleDocumentoMapper;
import com.sunqubit.faqture.core.mappers.DocumentoMapper;

@Repository
public class DocumentoDao implements IDocumentoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoDao.class);
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Documento documento) {
		SqlSession session = sqlSessionFactory.openSession();
		DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
		mapper.insert(documento);
		session.close();
		this.insertDetails(documento);
	}

	@Override
	public void update(Documento documento) {
		SqlSession session = sqlSessionFactory.openSession();
		DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
		mapper.update(documento);
		session.close();		
	}

	@Override
	public Documento get(long docuId) {
		Documento documento = null;
		SqlSession session = sqlSessionFactory.openSession();
		DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
		documento = mapper.get(docuId);
		session.close();
		return documento;
	}

	@Override
	public List<Documento> getList(Empresa empresa) {
		List<Documento> listado = null;
		SqlSession session = sqlSessionFactory.openSession();
		DocumentoMapper mapper = session.getMapper(DocumentoMapper.class);
		mapper.getListE(empresa);
		session.close();
		return listado;
	}

	@Override
	public void insertDetails(Documento documento) {
		SqlSession session = sqlSessionFactory.openSession();
		DetalleDocumentoMapper mapper = session.getMapper(DetalleDocumentoMapper.class);
		for (DetalleDocumento detalleDocumento : documento.getDetallesDocumento()) {
			mapper.insert(detalleDocumento);
		}
		session.close();
		
	}	
}
