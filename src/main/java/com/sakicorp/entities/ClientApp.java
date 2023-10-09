package com.sakicorp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "client")
@Data
public class ClientApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String clientId;
    
    private String clientSecret;

    private String clientAuthenticationMethods;

    private String authorizationGrantTypes;
    
    private String redirectUris;

    private String scopes;
    
    private int durationInMinutes;
    
    private boolean requiredProofKey;

}
