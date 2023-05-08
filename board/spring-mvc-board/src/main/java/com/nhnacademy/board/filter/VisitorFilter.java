package com.nhnacademy.board.filter;

import com.nhnacademy.board.config.PropertiesConfig;
import com.nhnacademy.board.domain.Visitor;
import com.nhnacademy.board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class VisitorFilter implements Filter {

    Visitor visitor;
    private Set<String> excludeUrls = new HashSet<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        visitor = (Visitor) context.getBean("visitor");
        PropertiesConfig propertiesConfig = (PropertiesConfig) context.getBean("propertiesConfig");
        excludeUrls = propertiesConfig.getExcludeUrls();
    }

    private boolean urlCheck(String path){
        for (String excludeUrl : excludeUrls) {
            if(path.contains(excludeUrl)){
                return false;
            }
        }
        return true;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();
        log.info("visitor-check-filter-path:{}",path);
        if(path.startsWith("/user") || path.startsWith("/post")){
            if(Objects.isNull(CookieUtils.getCookie(req,"visitor"))){
                visitor.increaseViewCount();
                Cookie cookie = new Cookie("visitor","visit");
                cookie.setMaxAge(60*60);
                cookie.setPath("/");
                log.info("create visitor22");
                rep.addCookie(cookie);
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}