package com.nhnacademy.board.request;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.validation.Valid;

@Valid
@Data
public class UserRegisterRequest {

    private String id;
    private String password;
    private String name;
    @Getter
    private MultipartFile profileFileName;

    @Override
    public String toString() {
        return "StudentRegisterRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
