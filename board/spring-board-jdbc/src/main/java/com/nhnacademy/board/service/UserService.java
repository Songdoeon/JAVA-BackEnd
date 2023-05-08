package com.nhnacademy.board.service;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.mapper.UserMapper;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService {
    private final UserMapper userMapper;
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUser(String id){
        User user =  userMapper.getUser(id);
        if(Objects.isNull(user)){
            throw new StudentNotFoundException(id);
        }
        return user;
    }
    public void delete(String id){
        userMapper.remove(id);
    }

    public List<User> getUserList(){
        return userMapper.getUsers();
    }
    public List<User> getPartList(int num){
        return userMapper.getPartUser(num);
    }

    public void register(User user){
//        if(userMapper.existById(user.getId())){
//            throw new DuplicateStudentIdException(user.getId());
//        }
        userMapper.add(user);
    }
    public int getNum(){
        return userMapper.getNum();
    }
    public void modify(User user){
        userMapper.modify(user);
    }
}
