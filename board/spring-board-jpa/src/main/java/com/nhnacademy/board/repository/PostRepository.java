package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser_UserId(String id);

    List<Post> findByTitle(String title);

    Page<Post> getPostsBy(Pageable pageable);
}
