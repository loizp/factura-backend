package com.sunqubit.faqture.core.daos;

import com.sunqubit.faqture.core.beans.Cliente;
import com.sunqubit.faqture.core.daos.contracts.IClienteDao;
import com.sunqubit.faqture.core.mappers.ClienteMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDao implements IClienteDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Override
    public void insert(Cliente cliente) {
    	SqlSession session = sqlSessionFactory.openSession();
    	ClienteMapper mapper = session.getMapper(ClienteMapper.class);
    	mapper.insert(cliente);
    	session.close();
    }

    @Override
    public void update(Cliente cliente) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public List<Cliente> getList(){
        List<Cliente> listado = null;
        SqlSession session = sqlSessionFactory.openSession();
        ClienteMapper mapper = session.getMapper(ClienteMapper.class);
        listado= mapper.getList();
        session.close();
        return listado;
    }

	@Override
	public Cliente get(long clieId) {
		Cliente cliente = null;
        SqlSession session = sqlSessionFactory.openSession();
        ClienteMapper mapper = session.getMapper(ClienteMapper.class);
        cliente = mapper.get(clieId);
        session.close();
        return cliente;
	}
    
    

}
