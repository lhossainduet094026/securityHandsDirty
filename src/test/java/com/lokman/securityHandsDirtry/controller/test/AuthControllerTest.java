package com.lokman.securityHandsDirtry.controller.test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.lokman.securityHandsDirtry.controller.AuthController;
import com.lokman.securityHandsDirtry.dto.SecurityUserDTO;
import com.lokman.securityHandsDirtry.jwt.JwtUtil;
import com.lokman.securityHandsDirtry.service.UserService;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
    private JwtUtil jwtUtil;
	
	@MockitoBean
	private UserService userService;
	
	@MockitoBean
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void shouldGenerateJwtTokenTest() throws Exception {
		
		//arrange
		
		SecurityUserDTO userDTO = new SecurityUserDTO();
        userDTO.setEmail("lhossianduet094026@gmail.com");
        userDTO.setPassword("$2a$10$encodedPassword");
        
        when(userService.findUserAuthDataByEmail("lhossianduet094026@gmail.com")).thenReturn(userDTO);
        when(passwordEncoder.matches("password123", userDTO.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(userDTO)).thenReturn("mocked.jwt.token");
        
		//act
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"lhossianduet094026@gmail.com\",\"password\":\"password123\"}"))
        //assert
        .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("mocked.jwt.token"))
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("token generated successful"));
                		
		
	}
}
