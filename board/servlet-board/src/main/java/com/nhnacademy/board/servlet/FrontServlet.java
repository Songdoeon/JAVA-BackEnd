package com.nhnacademy.board.servlet;

import com.nhnacademy.board.controller.*;
import com.nhnacademy.board.controller.admin.*;

import com.nhnacademy.board.controller.user.*;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try{
            Command command = resolveServlet(req.getServletPath(),req.getMethod());
            String view = command.execute(req,resp);
            log.info("view :{}",view);
            if (view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring((REDIRECT_PREFIX.length()+1)));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch(Exception ex){
            log.error("", ex);
            req.setAttribute("exception", ex);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }
    private Command resolveServlet(String servletPath, String method){
        //todo 실행할 servlet 결정하기
        Command command = null;
        if("/logout.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LogoutController();
        }else if("/login.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new LoginController();
        }else if("/login.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new LoginFormController();
        }else if("/admin.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)) {
            command = new AdminController();
        }else if("/userUpdate.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new UserUpdateController();
        }else if("/userAdd.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new UserAddController();
        }else if("/userDelete.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new UserDeleteController();
        }else if("/board.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new UserController();
        }else if("/postUpdate.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new PostUpdateController();
        }else if("/addPost.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new PostAddController();
        }else if("/postDelete.do".equals(servletPath)&& "POST".equalsIgnoreCase(method)){
            command = new PostDeleteController();
        }else if("/userView.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new UserViewController();
        }else if("/postView.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new PostViewController();
        }else if("/postUpdate.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new PostUpdateFormController();
        }else if("/error.do".equals(servletPath)&& "GET".equalsIgnoreCase(method)){
            command = new ErrorController();
        }else if("/locale.do".equals(servletPath)&& "post".equalsIgnoreCase(method)){
            command = new LocaleController();
        }else if ("/images.do".equals(servletPath)) {
            command = new ImageController();
        }
        return command;
    }
}
