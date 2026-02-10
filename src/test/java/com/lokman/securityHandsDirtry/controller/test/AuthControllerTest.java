package com.lokman.securityHandsDirtry.controller.test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.lokman.securityHandsDirtry.controller.AuthController;
import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.jwt.JwtUtil;
import com.lokman.securityHandsDirtry.service.UserService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
    private JwtUtil jwtUtil;
	
	@MockitoBean
	private UserService userService;
	
	@Test
	public void shouldGenerateJwtTokenTest() {
		
		//arrange
		
		SecurityUserDTO userDTO = new SecurityUserDTO();
        userDTO.setEmail("lhossianduet094026@gmail.com");
        userDTO.setPassword("$2a$10$encodedPassword");
        
        when(userService.findUserAuthDataByEmail("lhossianduet094026@gmail.com")).thenReturn(userDTO);
        
		//act
		
		//assert
	}
}
