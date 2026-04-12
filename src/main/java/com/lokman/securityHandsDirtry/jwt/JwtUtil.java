package com.lokman.securityHandsDirtry.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;

import io.jsonwebtoken.Claims;
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
        
        return Jwts.builder().setClaims(claims)
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
        .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
        .compact();
	}
	
	public String extractUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String userName = getClaims(token).getSubject();
		return userName.equals(userDetails.getUsername())
				&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return getClaims(token)
				.getExpiration()
				.before(new Date());

	}
}
