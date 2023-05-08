package com.nhnacademy.board;

import java.io.File;

public class User implements IUser {

    private String id;
    private String password;
    private String name;
    private File ProfileFileName;
    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public User(String id, String password, String name, File profileFileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.ProfileFileName = profileFileName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public File getProfileFileName() {
        return ProfileFileName;
    }

    @Override
    public void setProfileFileName(File profileFileName) {
        ProfileFileName = profileFileName;
    }


}
