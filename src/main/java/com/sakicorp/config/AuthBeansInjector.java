package com.sakicorp.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.sakicorp.service.UserDetailsImpl;

@Configuration
public class AuthBeansInjector {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthBeansInjector.class);
	
    @Autowired
    private UserDetailsService userDetailsService;
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(){
        LOGGER.info("AGREGAR CLAIMS A JWT");
        return context -> {
            Authentication principal = context.getPrincipal();
            UserDetailsImpl userDetails = (UserDetailsImpl) principal.getPrincipal();
            if(context.getTokenType().getValue().equals("access_token")){
                context.getClaims().claim("token_type", "access token");
                List<String> roles = principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
                context.getClaims().claim("roles", roles);
                context.getClaims().claim("username", principal.getName());
                context.getClaims().claim("apellido", userDetails.getApellido());
                context.getClaims().claim("nombre", userDetails.getNombre());
            }
        };
    }

}
