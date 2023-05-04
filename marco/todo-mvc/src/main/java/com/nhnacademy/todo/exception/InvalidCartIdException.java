package com.nhnacademy.todo.exception;

public class InvalidCartIdException extends RuntimeException {
    public InvalidCartIdException(){
        super("Invalid Cart id");
    }
}
