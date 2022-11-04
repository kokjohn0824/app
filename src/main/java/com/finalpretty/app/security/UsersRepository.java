package com.finalpretty.app.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finalpretty.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByaccount(String account);

    @Transactional
    @Modifying
    @Query("update Users a " + " SET a.enabled = true where a.email = ?1")
    int enableUser(String email);

    @Transactional
    @Modifying
    @Query("update Users SET locked =:locked where users_id =:users_id")
    void updateLocked(@Param("locked") Boolean locked, @Param("users_id") Integer users_id);

}
