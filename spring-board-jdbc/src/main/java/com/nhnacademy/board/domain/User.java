package com.nhnacademy.board.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@NoArgsConstructor
@Data
public class User implements IUser {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String profileFileName;

    public User(String userId, String password, String name, String profileFileName) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
    }

    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profileFileName='" + profileFileName + '\'' +
                '}';
    }
}
