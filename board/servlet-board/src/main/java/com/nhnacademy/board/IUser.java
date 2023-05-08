package com.nhnacademy.board;

import java.io.File;

public interface IUser {
    String getId();
    void setId(String id);

    String getPassword();
    void setPassword(String password);

    String getName();
    void setName(String name);

    File getProfileFileName();
    void setProfileFileName(File profileFileName);
}