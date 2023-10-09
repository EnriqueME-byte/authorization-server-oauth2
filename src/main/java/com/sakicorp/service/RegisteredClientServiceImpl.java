package com.sakicorp.service;

import com.sakicorp.entities.ClientApp;
import com.sakicorp.mapper.ClientAppMapper;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import com.sakicorp.persistence.IClientAppRepository;

@Service
public class RegisteredClientServiceImpl implements RegisteredClientRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredClientServiceImpl.class);
	@Autowired
	private IClientAppRepository clientAppRepository;
	
	@Override
	public void save(RegisteredClient registeredClient) {
		
	}

	@Override
	public RegisteredClient findById(String id) {
		LOGGER.info("ID CLIENT = " + id );
		ClientApp clientApp = clientAppRepository.findById(id).orElseThrow();
		return ClientAppMapper.toRegisteredClient(clientApp);
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		LOGGER.info("por client id : " + clientId);
		return this.findById(clientId);
	}

	
	
	
}
