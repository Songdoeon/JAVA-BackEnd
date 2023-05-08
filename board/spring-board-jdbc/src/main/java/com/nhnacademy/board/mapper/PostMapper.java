package com.nhnacademy.board.mapper;

import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PostMapper {

    long register(Post post);
    void modify(Post post);
    void remove(Long id);
    Post getPost(Long id);
    List<Post> getPosts();
}
