package com.sakicorp.mapper;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import com.sakicorp.entities.ClientApp;

public class ClientAppMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientAppMapper.class);

	public static RegisteredClient toRegisteredClient(ClientApp clientApp) {
		LOGGER.info("MAPPER: " );
		RegisteredClient client = RegisteredClient.withId(clientApp.getClientId())
				.clientId(clientApp.getClientId())
				.clientSecret(clientApp.getClientSecret())
				.clientIdIssuedAt(new Date(System.currentTimeMillis()).toInstant())
				.clientAuthenticationMethods(clientAuthMethods -> {
					String[] authMethods = clientApp.getClientAuthenticationMethods().split(",");
					List<String> listaAuthMethods = Arrays.asList(authMethods);
					LOGGER.info("AUTH METHODS: " + listaAuthMethods.toString());
					listaAuthMethods.stream()
							.map(method -> new ClientAuthenticationMethod(method))
							.forEach(clientAuthMethods::add);
				})
				.authorizationGrantTypes(authGrantTypes -> {
					String[] authoGrantTypes = clientApp.getAuthorizationGrantTypes().split(",");
					List<String> listaAuthGrantTypes = Arrays.asList(authoGrantTypes);
					LOGGER.info("Auth grant types: " + listaAuthGrantTypes.toString());
					listaAuthGrantTypes.stream()
							 .map(grant -> new AuthorizationGrantType(grant))
							 .forEach(authGrantTypes::add);
				})
				.redirectUris(redirectUris -> {
					String[] rUris = clientApp.getRedirectUris().split(",");
					List<String> listaRUris = Arrays.asList(rUris);
					LOGGER.info("redirect uris: " + listaRUris.toString());
					listaRUris.stream().forEach(redirectUris::add);
				})
				.scopes(scopes -> {
					String[] lScopes = clientApp.getScopes().split(",");
					List<String> listaScopes = Arrays.asList(lScopes);
					LOGGER.info("scopes: " + listaScopes.toString());
					listaScopes.stream().forEach(scopes::add);
				})
				.tokenSettings(TokenSettings.builder()
						.accessTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes()))
						.refreshTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes() * 4))
						.build())
				.clientSettings(ClientSettings.builder()
						.requireProofKey(clientApp.isRequiredProofKey()) //si es true en la petición requiere un PKCE -> code_challenge
						.build())
				.build();
		LOGGER.info(client.toString());
		return client;
	}
	
}
