package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.User;

import java.util.List;

public interface IUserRepository {
    void add(User user);
    void modify(User user);
    void remove(String id);

    User getUser(String id);
    List<User> getUsers();
    boolean existById(String id);
}