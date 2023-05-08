package com.nhnacademy.board.advice;

import com.nhnacademy.board.exception.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CommonAdvice {

    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentNotFound(StudentNotFoundException studentNotFoundException){
        log.info("error:{}",studentNotFoundException.getMessage(),studentNotFoundException);
        return "error/studentNotFound";
    }
    @ExceptionHandler(DuplicateStudentIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String duplicateStudentId(DuplicateStudentIdException duplicateStudentIdException){
        return "error/duplicateStudentId";
    }
    @ExceptionHandler(NoHandlerFoundException.class )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound(){
        log.info("404 not found");
        return "error/404";
    }
    @ExceptionHandler(NotWriter.class )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String Writer(){
        return "error/notWriter";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServlerError(Exception e, Model model, HttpServletRequest request){
        model.addAttribute("exception",e.getMessage());
        log.info("internal server error");
        return "error/500";
    }
}
