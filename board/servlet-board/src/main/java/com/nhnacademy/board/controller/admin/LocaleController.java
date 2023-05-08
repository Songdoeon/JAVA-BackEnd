package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String locale = "en";

        if (req.getParameter("locale") != null) {
            locale = req.getParameter("locale");
        }

        HttpSession session = req.getSession();
        session.setAttribute("locale", locale);
        return "redirect:/login.do";
    }
}

