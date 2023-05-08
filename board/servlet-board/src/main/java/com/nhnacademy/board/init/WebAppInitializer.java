package com.nhnacademy.board.init;

import com.nhnacademy.board.Post;
import com.nhnacademy.board.PostRepository;
import com.nhnacademy.board.User;
import com.nhnacademy.board.UserRepository;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpSession;
import java.util.Set;

@HandlesTypes(value = {
        javax.servlet.http.HttpServlet.class,
        javax.servlet.Filter.class,
        javax.servlet.ServletContextListener.class,
        javax.servlet.http.HttpSessionListener.class
})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("adminId","admin");
        servletContext.setInitParameter("adminPassword","12345");

        servletContext.setInitParameter("userId","marco");
        servletContext.setInitParameter("userPassword","12345");


        servletContext.setAttribute("countUser",0);
    }
}