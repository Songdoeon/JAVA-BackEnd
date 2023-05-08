package com.nhnacademy.board.exception;

public class DuplicateStudentIdException extends RuntimeException {
    public DuplicateStudentIdException(String id){
        super("아이디 중복 : " + id);
    }
}
