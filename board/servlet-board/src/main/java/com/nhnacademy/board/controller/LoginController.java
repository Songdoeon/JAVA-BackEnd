package com.nhnacademy.board.controller;

import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
public class LoginController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String adminId = req.getServletContext().getInitParameter("adminId");
        String adminPassword = req.getServletContext().getInitParameter("adminPassword");
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");
        if (adminId.equals(id) && adminPassword.equals(pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);

            return "redirect:/admin.do";
        }
        List<User> userList = userRepository.getUsers();
        String initParamId = userRepository.getUser(id).getId();
        String initParamPwd = userRepository.getUser(id).getPassword();
        if (initParamId.equals(id) && initParamPwd.equals(pwd)) {
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            return "redirect:/board.do";
        }
        else
            log.error("아이디/패스워드가 일치하지 않습니다.");
            return "/login.jsp";
    }
}
