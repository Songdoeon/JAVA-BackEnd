//package com.nhnacademy.board.service;
//
//import com.nhnacademy.board.config.RootConfig;
//import com.nhnacademy.board.config.WebConfig;
//import com.nhnacademy.board.controller.login.LoginController;
//import com.nhnacademy.board.domain.User;
//import com.nhnacademy.board.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextHierarchy(value= {
//        @ContextConfiguration(classes = {RootConfig.class}),
//        @ContextConfiguration(classes = {WebConfig.class})
//})
//class LoginServiceTest {
//
//    private MockMvc mockMvc;
//    private UserRepository userRepository;
//
//    private MockHttpSession session;
//    @BeforeEach
//    void setup(){
//        userRepository = mock(UserRepository.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new LoginService(userRepository)).build();
//        session = new MockHttpSession();
//    }
//    @Test
//    void match() {
//        User user = new
//    }
//
//    @Test
//    void adminMatch() {
//
//    }
//}