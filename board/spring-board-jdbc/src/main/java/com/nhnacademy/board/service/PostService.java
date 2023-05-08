package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.NotWriter;
import com.nhnacademy.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PostService {
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long getId(){
        List<Post> postList = postRepository.getPosts();
        int size = postList.size()-1;
        Long id = Long.valueOf(postList.get(size).getId());
        return id+1;
    }

    public Post getPost(Long id){
        Post post =  postRepository.getPost(id);
        return post;
    }

    public void delete(Long id, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Post beforePost = postRepository.getPost(id);
        if(!beforePost.getWriterUserId().equals(user.getUserId())){
            throw new NotWriter();
        }
        postRepository.remove(id);
    }
    public List<Post> getPartList(int num){
        num = (num-1)*10;
        int size=10;
        List<Post> postList = new ArrayList<>();
        List<Post> list = postRepository.getPosts();
        if(list.size()<num+10){
            size=list.size()-num;
        }
        for(int i=0;i<size;i++){
            postList.add(list.get(num));
            num++;
        }
        return postList;
    }

    public List<Post> getPostList(){
        return postRepository.getPosts();
    }

    public void register(Post post){
        postRepository.register(post);
    }

    public void modify(Long id,Post post){
        Post beforePost = postRepository.getPost(id);
        if(!beforePost.getWriterUserId().equals(post.getWriterUserId())){
            throw new NotWriter();
        }
        postRepository.modify(post);
    }
}
