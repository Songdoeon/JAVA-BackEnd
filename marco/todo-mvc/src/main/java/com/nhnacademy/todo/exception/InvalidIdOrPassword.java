package com.nhnacademy.todo.exception;

public class InvalidIdOrPassword extends RuntimeException {
    public InvalidIdOrPassword(){
        super("invalid id or  password");
    }
}
