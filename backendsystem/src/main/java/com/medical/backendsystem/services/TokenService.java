package com.medical.backendsystem.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.medical.backendsystem.models.entity.AccountEntity;


/**
 * Token
 */
@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateToken(AccountEntity accountEntity) {
        Instant now = Instant.now();
        String authorities = accountEntity.getRole();
        String userName = accountEntity.getEmail();

        logger.debug("authorities: {}", authorities);
        logger.debug("userName: {}", userName);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userName)
                .claim("authorities", authorities)
                .build();
                
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}