package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.beans.Sucursal;
import com.sunqubit.faqture.core.daos.contracts.ISucursalDao;
import com.sunqubit.faqture.core.mappers.SucursalMapper;

@Component
public class SucursalDao implements ISucursalDao {
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insert(Sucursal sucursal) {
		SqlSession session = sqlSessionFactory.openSession();
		SucursalMapper mapper = session.getMapper(SucursalMapper.class);
		mapper.insert(sucursal);
		session.close();
	}

	@Override
	public List<Sucursal> getList(Empresa empresa) {
		List<Sucursal> listado = null;
		SqlSession session = sqlSessionFactory.openSession();
		SucursalMapper mapper = session.getMapper(SucursalMapper.class);
		listado = mapper.getList(empresa);
		session.close();
		return listado;
	}

}
