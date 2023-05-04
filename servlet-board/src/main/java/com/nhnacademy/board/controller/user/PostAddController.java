package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PostAddController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        List<Post> postList = postRepository.getPosts();
        int size = 0;
        Long id =Long.valueOf(0);
        if(postList.size()!=0){
            size = postList.size()-1;
            id = Long.valueOf(postList.get(size).getId());
        }

        HttpSession session = req.getSession();
        String writer = (String)session.getAttribute("id");
//        String id = (Integer)postList.get(size).getId();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if(Objects.isNull(id)||Objects.isNull(title)||Objects.isNull(content)){
            return "redirect:/error.do";
        }
        postRepository.register(new Post(id+1,title,content,writer));
        return "redirect/board.do";
    }
}
