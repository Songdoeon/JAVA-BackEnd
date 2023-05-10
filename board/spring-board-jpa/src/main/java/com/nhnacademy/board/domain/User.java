package com.nhnacademy.board.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Table(name="Users")
@Entity
@Getter @Setter
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String name;
    private String profileFileName;


    public User(String id, String password, String name, String fileName) {
    }
}
