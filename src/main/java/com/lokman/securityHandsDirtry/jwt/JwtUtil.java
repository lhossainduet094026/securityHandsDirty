package com.lokman.securityHandsDirtry.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final JwtConfig jwtConfig;

	@Autowired
	public JwtUtil(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public String generateToken(SecurityUserDTO user) {

		Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        
        return Jwts.builder().setClaims(claims)
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
        .signWith(jwtConfig.getSecret(), SignatureAlgorithm.HS512)
        .compact();
		
	}
}
