package com.nhnacademy.board.mapper;

import com.nhnacademy.board.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void add(User user);
    void modify(User user) ;
    void remove(String id);
    User getUser(String id);
    List<User> getPartUser(int num);
    List<User> getUsers();
    int getNum();

}
