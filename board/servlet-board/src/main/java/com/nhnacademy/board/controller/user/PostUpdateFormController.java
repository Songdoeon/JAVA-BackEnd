package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PostUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        String writer = (String)session.getAttribute("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if(Objects.isNull(title)||Objects.isNull(content)){
            return "redirect:/error.do";
        }
        postRepository.modify(new Post(id,title,content,writer));
        return "redirect:/board.do";
    }
}
