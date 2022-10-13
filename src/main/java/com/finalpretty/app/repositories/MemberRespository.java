package com.finalpretty.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalpretty.app.model.Member;

public interface MemberRespository extends JpaRepository<Member, Long> {

}
