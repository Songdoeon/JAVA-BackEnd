package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.NotWriter;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final EntityManager entityManager;

    private final PostRepository postRepository;

    public Long getId(){
        Query query = entityManager.createNativeQuery("select count(*) from Posts");
        Long size = (Long)query.getSingleResult();
        Long id = entityManager.find(Post.class,size).getId();
        return id+1;
    }

    public Post getPost(Long id){
        Post post = postRepository.findById(id).get();
        return post;
    }

    public void delete(Long id, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Post beforePost = postRepository.findById(id).get();
        if(!beforePost.getWriterUserId().equals(user.getUserId())){
            throw new NotWriter();
        }
        postRepository.delete(beforePost);
    }
    public Page paging(Pageable pageable){
        return postRepository.getPostsBy(pageable);
    }

    public void register(Post post){
        postRepository.saveAndFlush(post);
    }

    public void modify(Long id,Post post){
        Post beforePost = postRepository.findById(id).get();
        if(!beforePost.getWriterUserId().equals(post.getWriterUserId())){
            throw new NotWriter();
        }
        postRepository.saveAndFlush(post);
    }
}
