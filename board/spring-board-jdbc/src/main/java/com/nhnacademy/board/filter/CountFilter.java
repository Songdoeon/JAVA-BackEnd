package com.nhnacademy.board.filter;

import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.service.PostService;
import com.nhnacademy.board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class CountFilter implements Filter {

    PostService postService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        postService = (PostService) context.getBean("postService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String path = req.getRequestURI();
        log.info("count-check-filter-path:{}",path);
        if(req.getRequestURI().startsWith("/post/view")&& Objects.nonNull(req.getQueryString())){
            String postSeq = req.getQueryString().split("=")[1];
            Cookie cookie = CookieUtils.getCookie(req,"VIEW"+postSeq);
            if(Objects.isNull(cookie)){
                Post post = postService.getPost(Long.valueOf(postSeq));
                post.increaseViewCount();
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}