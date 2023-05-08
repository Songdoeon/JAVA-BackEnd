package com.nhnacademy.board.service;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final EntityManager entityManager;

    public User getUser(String id){
        User user =  entityManager.find(User.class,id);
        if(Objects.isNull(user)){
            throw new StudentNotFoundException(id);
        }
        return user;
    }
    @Transactional
    public void delete(String id){
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    public List<User> getUserList(){
        Query query = entityManager.createNativeQuery("select * from Users");
        List<User> userList = query.getResultList();
        return userList;
    }
    public List<User> getPartList(int num){
        int end = (num*10)-10;
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u", User.class).setFirstResult(end).setMaxResults(10);
        List<User> userList = query.getResultList();
        return userList;
    }
    @Transactional
    public void register(User user){
        entityManager.persist(user);
        entityManager.flush();
    }
    @Transactional
    public void modify(User user){
        User user1 = entityManager.find(User.class,user);
        entityManager.persist(user1);
    }
}
