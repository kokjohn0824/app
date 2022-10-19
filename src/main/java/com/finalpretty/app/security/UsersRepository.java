package com.finalpretty.app.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByaccount(String account);

    @Transactional
    @Modifying
    @Query("update Users a " + " SET a.enabled = true where a.email = ?1")
    int enableUser(String email);
}
