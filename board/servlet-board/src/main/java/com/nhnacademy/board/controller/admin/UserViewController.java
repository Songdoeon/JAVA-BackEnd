package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UserViewController implements Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            resp.sendRedirect("/error");
        }
        User user = userRepository.getUser(id);
        req.setAttribute("user",user);
        return "/userView.jsp";
    }
}
