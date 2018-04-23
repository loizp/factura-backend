package com.sunqubit.faqture.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.daos.contracts.IClienteDao;

@Service
public class ClienteService {

	@Autowired
	private IClienteDao clienteDao;
}
