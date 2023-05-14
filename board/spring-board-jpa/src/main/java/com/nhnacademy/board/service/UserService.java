package com.nhnacademy.board.service;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public User getUser(String id){
        User user =  userRepository.findById(id).get();
        if(Objects.isNull(user)){
            throw new StudentNotFoundException(id);
        }
        return user;
    }
    public void delete(String id){
        User user =  userRepository.findById(id).get();
        userRepository.delete(user);
    }
    public void register(User user){
        userRepository.saveAndFlush(user);
    }
    public void modify(User user){
        userRepository.saveAndFlush(user);
    }

    public Page paging(Pageable pageable){
        return userRepository.getUsersBy(pageable);
    }
}
