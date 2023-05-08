package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class PostUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        Post post = postRepository.getPost(id);
        String writer = (String)session.getAttribute("id");
        if(writer.equals(post.getWriterUserId())){
            req.setAttribute("post",post);
            return "/postAdd.jsp";
        }
        else{
            try(PrintWriter print = resp.getWriter()){
                return "redirect:/board.do";
            }
        }
    }
}
