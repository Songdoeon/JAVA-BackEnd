package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class PostDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        Post post = postRepository.getPost(id);
        String writer = (String)session.getAttribute("id");
        if(writer.equals(post.getWriterUserId())){
            postRepository.remove(id);
            return "redirect/board.do";
        }
        else{
            try(PrintWriter print = resp.getWriter()){
                return "redirect:/board.do";
            }
        }
    }
}
