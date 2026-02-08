package com.lokman.securityHandsDirtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lokman.securityHandsDirtry.dto.UserAuthRow;
import com.lokman.securityHandsDirtry.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT new com.lokman.securityHandsDirtry.dto.UserAuthRow(u.name,u.password, u.email, r.name, a.name)"
			   + "FROM User u LEFT JOIN u.roles r  LEFT JOIN r.authorities a WHERE u.email = :email")
			List<UserAuthRow> findUserAuthDataByEmail(@Param("email") String email);
}
