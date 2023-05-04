package com.nhnacademy.board.util;

import javax.servlet.ServletContext;
import java.util.Optional;

public final class UserCounterUtils {
    private UserCounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("userCount")).orElse(0l);
        counter = counter+1;
        servletContext.setAttribute("userCount",counter);
    }
}
