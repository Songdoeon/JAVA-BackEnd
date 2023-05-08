package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.request.LoginRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@NoArgsConstructor
@Transactional
public class LoginService {

    public boolean match(User user, LoginRequest loginRequest){
        if(user.getUserId().equals(loginRequest.getUserId()) && user.getPassword().equals(loginRequest.getUserPassword())){
            return true;
        }else {
            return false;
        }
    }

    public boolean adminMatch(User user) {
        if (user.getUserId().equals("admin") && user.getPassword().equals("1234")) {
            return true;
        } else {
            return false;
        }
    }

}
