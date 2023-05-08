package com.nhnacademy.board.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IUser {

    String getPassword();
    void setPassword(String password);

    String getName();
    void setName(String name);

    String getProfileFileName();
    void setProfileFileName(String profileFileName);
}