package com.sakicorp.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakicorp.entities.ClientApp;

public interface IClientAppRepository extends JpaRepository<ClientApp, String>{
	
	Optional<ClientApp> findById(String clientId);
}
