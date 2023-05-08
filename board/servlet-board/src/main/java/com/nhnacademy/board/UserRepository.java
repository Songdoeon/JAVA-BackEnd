package com.nhnacademy.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository implements IUserRepository{
    List<User> userList = new ArrayList<>();
    Map<String,User> userMap = new HashMap<>();
    @Override
    public void add(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public void modify(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public void remove(String id) {
        userMap.remove(id);
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        userList = new ArrayList<>(userMap.values());
        return userList;
    }


}
