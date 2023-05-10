package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.NotWriter;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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
    public List<Post> getPartList(int num){
        int end = (num*10)-10;
        TypedQuery<Post> query = entityManager.createQuery("SELECT p from Post p",Post.class).setFirstResult(end).setMaxResults(10);
        List<Post> postList = query.getResultList();
        return postList;
    }

    public List<Post> getPostList(){
        Query query = entityManager.createNativeQuery("select * from Posts");
        List<Post> postList = query.getResultList();
        return postList;
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
