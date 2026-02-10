package com.lokman.securityHandsDirtry.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.jwt.JwtConfig;
import com.lokman.securityHandsDirtry.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtilTest {

	private JwtUtil jwtUtil;

	private JwtConfig jwtConfig;

	@BeforeEach
	void setUp() {
		jwtConfig = Mockito.mock(JwtConfig.class);
		when(jwtConfig.getSecret()).thenReturn("2d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0c8b8f1a9e7b3d0");
		when(jwtConfig.getExpiration()).thenReturn(1000L * 60 * 60);
		jwtUtil = new JwtUtil(jwtConfig);
	}

	@Test
	void generateToken() {

		// Arrange
		SecurityUserDTO user = new SecurityUserDTO();
		user.setEmail("lhossianduet094026@gmail.com");
		user.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));

		// act
		String token = jwtUtil.generateToken(user);
		System.out.println(token);

		// assert
		assertNotNull(token);

		Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();

		// assert
		assertEquals("lhossianduet094026@gmail.com", claims.getSubject());
		assertTrue(claims.getExpiration().after(new Date()));
	}
}
