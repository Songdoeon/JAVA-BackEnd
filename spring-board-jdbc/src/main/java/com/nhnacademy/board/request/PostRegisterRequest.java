package com.nhnacademy.board.request;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Valid
@Data
public class PostRegisterRequest {

    private String title;
    private String content;


}
