package com.lokman.securityHandsDirtry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lokman.securityHandsDirtry.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<UserDto> findUserDtoByUserName(@Param("username") String username);
}
