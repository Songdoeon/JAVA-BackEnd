package com.nhnacademy.board.service;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.DuplicateStudentIdException;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id){
        User user =  userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new StudentNotFoundException(id);
        }
        return user;
    }
    public void delete(String id){
        userRepository.remove(id);
    }

    public List<User> getUserList(){
        return userRepository.getUsers();
    }
    public List<User> getPartList(int num){
        num = (num-1)*10;
        int size = 10;
        List<User> userList = new ArrayList<>();
        List<User> list = userRepository.getUsers();
        if(list.size()<num+10){
            size=list.size()-num;
        }
        for(int i=0;i<10;i++){
            userList.add(list.get(num));
            num++;
        }
        return userList;
    }

    public void register(User user){
        if(userRepository.existById(user.getId())){
            throw new DuplicateStudentIdException(user.getId());
        }
        userRepository.add(user);
    }
    public void modify(User user){
        userRepository.modify(user);
    }
}
