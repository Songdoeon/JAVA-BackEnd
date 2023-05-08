package com.nhnacademy.board;

import java.util.List;

public interface IPostRepository {
    long register(Post post);
    void modify(Post post);
    void remove(Long id);

    Post getPost(Long id);
    List<Post> getPosts();
}