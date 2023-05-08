
package com.nhnacademy.board.controller.user;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class PostViewController implements Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Long id = Long.parseLong(req.getParameter("id"));
        Post post = postRepository.getPost(id);
        req.setAttribute("post",post);
        return "/postView.jsp";
    }
}
