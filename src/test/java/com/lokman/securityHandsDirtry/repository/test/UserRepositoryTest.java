package com.lokman.securityHandsDirtry.repository.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.lokman.securityHandsDirtry.dto.UserDTO;
import com.lokman.securityHandsDirtry.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use real DB
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
    @DisplayName("Should fetch user DTO by username from real DB (read-only)")
    void testFindUserDTOByUsername() {
        // 🔹 Suppose you already have a record in DB:
        // username = 'lokman', password = 'secret', role = 'ADMIN'

        Optional<UserDTO> result = userRepository.findUserDtoByUserName("lokman");
        System.out.println(result);
        UserDTO dto = result.get();
        assertTrue(result.isPresent());

        
    }
	
}
