package com.lokman.securityHandsDirtry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lokman.securityHandsDirtry.dto.UserDTO;
import com.lokman.securityHandsDirtry.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select new com.lokman.securityHandsDirtry.dto.UserDTO(u.username, u.password, u.role) from User u where u.username =:username")
	Optional<UserDTO> findUserDtoByUserName(@Param("username") String username);
}
