package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.daos.contracts.IEmpresaDao;
import com.sunqubit.faqture.core.mappers.EmpresaMapper;

@Component
public class EmpresaDao implements IEmpresaDao {
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insert(Empresa empresa) {
		SqlSession session = sqlSessionFactory.openSession();
    	EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
    	mapper.insert(empresa);
    	session.close();
	}

	@Override
	public void update(Empresa empresa) {
		SqlSession session = sqlSessionFactory.openSession();
    	EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
    	mapper.update(empresa);
    	session.close();
	}

	@Override
	public Empresa get(long emprId) {
		Empresa empresa = null;
        SqlSession session = sqlSessionFactory.openSession();
        EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
        empresa = mapper.getForId(emprId);
        session.close();
        return empresa;
	}

	@Override
	public Empresa get(String emprRuc) {
		Empresa empresa = null;
        SqlSession session = sqlSessionFactory.openSession();
        EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
        empresa = mapper.getForRuc(emprRuc);
        session.close();
        return empresa;
	}

	@Override
	public List<Empresa> filterName(String nombre) {
		List<Empresa> listado = null;
        SqlSession session = sqlSessionFactory.openSession();
        EmpresaMapper mapper = session.getMapper(EmpresaMapper.class);
        listado= mapper.filterName(nombre);
        session.close();
        return listado;
	}

}
