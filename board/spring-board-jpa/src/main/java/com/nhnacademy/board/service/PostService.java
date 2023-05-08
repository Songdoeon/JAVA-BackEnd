package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.NotWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final EntityManager entityManager;


    public Long getId(){
        Query query = entityManager.createNativeQuery("select count(*) from Posts");
        Long size = (Long)query.getSingleResult();
        Long id = entityManager.find(Post.class,size).getId();
        return id+1;
    }

    public Post getPost(Long id){
        Post post = entityManager.find(Post.class,id);
        return post;
    }

    public void delete(Long id, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Post beforePost = entityManager.find(Post.class,id);
        if(!beforePost.getWriterUserId().equals(user.getUserId())){
            throw new NotWriter();
        }
        entityManager.remove(beforePost);
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
        entityManager.persist(post);
    }

    public void modify(Long id,Post post){
        Post beforePost = entityManager.find(Post.class,id);
        if(!beforePost.getWriterUserId().equals(post.getWriterUserId())){
            throw new NotWriter();
        }
        entityManager.persist(post);
    }
}
