package com.nhnacademy.board.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class LoginFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ){
            log.info("음");
            return "/login.jsp";
        }else if(Objects.isNull(session) || Objects.isNull(session.getAttribute("admin"))){
            log.info("음");
            return "/login.jsp";
        }else{
            return "/index.jsp";
        }
    }
}
