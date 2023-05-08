package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, String> {


}
