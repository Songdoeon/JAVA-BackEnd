package com.nhnacademy.board.controller;

import com.nhnacademy.board.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LogoutController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if(Objects.nonNull(session)) {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
        if(Objects.nonNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
//        resp.sendRedirect("/login.jsp");
        return "redirect:/login.do";
    }
}
