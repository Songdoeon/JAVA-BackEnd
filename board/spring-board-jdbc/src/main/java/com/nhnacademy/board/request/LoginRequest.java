package com.nhnacademy.board.request;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Data
public class LoginRequest {

    @NotBlank(message = "userId is empty")
    private String userId;
    @NotBlank(message ="userPassword  is empty!")
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
