package com.nhnacademy.familycertification.advice;

import com.nhnacademy.familycertification.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class CommonAdvice {

    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(NotFoundResidentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundResidentException(NotFoundResidentException notFoundResidentException){
        log.info("error:{}",notFoundResidentException.getMessage(),notFoundResidentException);
        return "error/error";
    }
//    @ExceptionHandler(NotFoundResidentException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String notExistCertificateException(NotExistCertificateException notExistCertificateException){
//        log.info("error:{}",notExistCertificateException.getMessage(),notExistCertificateException);
//        return "error/error";
//    }

}
