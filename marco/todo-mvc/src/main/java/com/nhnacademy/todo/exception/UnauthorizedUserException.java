package com.nhnacademy.todo.exception;

public class UnauthorizedUserException extends RuntimeException {
    private static final String message = "X-USER-ID를 찾을 수 없습니다.";
    public UnauthorizedUserException(){
        super(message);
    }
}
