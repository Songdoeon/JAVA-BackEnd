package com.nhnacademy.todo.exception;


public class ExistUserIdException extends RuntimeException{
    public ExistUserIdException(){
        super("exist user id");
    }
}
