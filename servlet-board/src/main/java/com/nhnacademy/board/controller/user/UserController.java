package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class UserController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        List<Post> postList = postRepository.getPosts();
        req.setAttribute("postList",postList);

        return "/board.jsp";
    }
}
