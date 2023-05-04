package com.nhnacademy.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

public class ErrorController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("status_code",req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type",req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("message",req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception",req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("request_uri",req.getAttribute(ERROR_STATUS_CODE));
        return "/error.jsp";
    }
}