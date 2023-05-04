package com.nhnacademy.board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@Slf4j
@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclude-urls",value = "/\n"+"/login.do\n" + "/logout.do\n" + "/login.jsp\n"+ "/locale.do\n"  )
        }
)
public class LoginCheckFilter implements Filter {
    private final List<String> excludeUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.error("exclude-urls:{}",urls);
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        // excludeUrls에 포함되지 않는다면 ..
        if(!excludeUrls.contains(requestUri)){
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if(Objects.isNull(session.getAttribute("id"))){
                ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
                log.info("로그인필터");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
