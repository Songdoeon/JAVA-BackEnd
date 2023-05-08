package com.nhnacademy.board.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Table(name="Users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileFileName() {
        return profileFileName;
    }

    public void setProfileFileName(String profileFileName) {
        this.profileFileName = profileFileName;
    }
}
