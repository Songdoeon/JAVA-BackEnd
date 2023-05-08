package com.nhnacademy.board;

import java.util.List;

public interface IUserRepository {
    void add(User user);
    void modify(User user);
    void remove(String id);

    User getUser(String id);
    List<User> getUsers();
}