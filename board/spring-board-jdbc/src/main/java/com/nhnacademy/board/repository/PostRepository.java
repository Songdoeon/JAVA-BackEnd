package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {
    Map<Long,Post> postMap = new HashMap<>();

    @Override
    public long register(Post post) {
        postMap.put(post.getId(),post);
        return post.getId();
    }
    @Override
    public void modify(Post post) {
        postMap.put(post.getId(),post);
    }
    @Override
    public void remove(Long id) {
        postMap.remove(id);
    }
    @Override
    public Post getPost(Long id) {
        return postMap.get(id);
    }
    @Override
    public List<Post> getPosts() {
        return postMap.values().stream().collect(Collectors.toList());
    }
}