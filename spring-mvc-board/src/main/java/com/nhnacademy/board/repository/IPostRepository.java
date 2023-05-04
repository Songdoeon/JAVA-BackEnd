package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.Post;

import java.util.List;

public interface IPostRepository {
    long register(Post post);
    void modify(Post post);
    void remove(Long id);

    Post getPost(Long id);
    List<Post> getPosts();
}