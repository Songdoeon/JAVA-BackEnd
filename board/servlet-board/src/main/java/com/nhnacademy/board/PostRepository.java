package com.nhnacademy.board;

import com.nhnacademy.board.IPostRepository;
import com.nhnacademy.board.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository implements IPostRepository {
    List<Post> postList = new ArrayList<>();
    Map<Long,Post> postMap = new HashMap<>();
    @Override
    public long register(Post post) {
        postMap.put(post.getId(),post);
        return 0;
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
        postList = new ArrayList<>(postMap.values());
        return postList;
    }
}