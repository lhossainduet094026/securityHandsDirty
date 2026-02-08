package com.lokman.securityHandsDirtry.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lokman.securityHandsDirtry.dto.UserAuthRow;
import com.lokman.securityHandsDirtry.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use real DB
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
    @DisplayName("FETCH USER WITH ROLE AND AUTHORITIES")
    void findUserAuthDataByEmailTest() {

        String email = "lhossianduet094026@gmail.com";
        List<UserAuthRow> users = userRepository.findUserAuthDataByEmail(email);
       
        assertFalse(users.isEmpty());
        assertEquals("lhossianduet094026@gmail.com", users.get(0).getEmail());
        System.out.println(users);
    }
	
}
