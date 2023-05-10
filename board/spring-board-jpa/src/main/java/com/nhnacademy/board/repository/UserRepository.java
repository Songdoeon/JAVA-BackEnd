package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUserId(String id);

    List<User> findByName(String name);

    Page<User> getUsersBy(Pageable pageable);
}
