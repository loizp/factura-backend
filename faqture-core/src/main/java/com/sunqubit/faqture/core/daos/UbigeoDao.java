package com.sunqubit.faqture.core.daos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;
import com.sunqubit.faqture.core.mappers.UbigeoMapper;

@Component
public class UbigeoDao implements IUbigeoDao{
	
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Ubigeo> filter(String filtro) {
		List<Ubigeo> listado = null;
		SqlSession session = sqlSessionFactory.openSession();
		UbigeoMapper mapper = session.getMapper(UbigeoMapper.class);
		listado = mapper.filter(filtro);
		session.close();
	    return listado;
	}
}
