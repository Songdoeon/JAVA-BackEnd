package com.nhnacademy.nhnboard.user.repository;

import com.nhnacademy.nhnboard.common.pagenation.Page;
import com.nhnacademy.nhnboard.user.domain.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);
    User getUser(String id);
    List<User> getUsers();

    int getTotalCount();
    Page<User> getPagedList(int page, int size);
    boolean existById(String id);
}
