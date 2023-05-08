package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class UserRepository implements IUserRepository{
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
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean existById(String id) {
        return userMap.containsKey(id);
    }

}
