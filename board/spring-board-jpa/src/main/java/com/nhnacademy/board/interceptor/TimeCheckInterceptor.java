package com.nhnacademy.board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class TimeCheckInterceptor implements HandlerInterceptor {
    private static ThreadLocal<Long> runTime = new ThreadLocal<>();

    //Controller 호출(전)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        runTime.set(System.currentTimeMillis());
        return true;
    }

    //Controller 호출(후)
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long start = runTime.get();
        long result = System.currentTimeMillis() - start;
        HttpSession session = request.getSession();
        session.setAttribute("time",result+" ms");
        log.info("runtime:{}",result);
        runTime.remove();
    }

}
