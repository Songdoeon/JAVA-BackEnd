package com.nhnacademy.board.listener;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        UserRepository userRepository = new UserRepository();
        userRepository.add(new User("marco", "1234", "marco"));
        context.setAttribute("userRepository", userRepository);
        PostRepository postRepository = new PostRepository();
        postRepository.register(new Post(Long.valueOf(1), "가입인사", "반가워요", "marco"));
        context.setAttribute("postRepository", postRepository);
    }
}
