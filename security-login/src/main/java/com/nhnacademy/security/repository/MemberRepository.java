package com.nhnacademy.security.repository;

import com.nhnacademy.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {



}
