package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import com.nhnacademy.board.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AdminController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        List<User> userList = userRepository.getUsers();
        req.setAttribute("userList",userList);

        return "/admin.jsp";
    }
}
