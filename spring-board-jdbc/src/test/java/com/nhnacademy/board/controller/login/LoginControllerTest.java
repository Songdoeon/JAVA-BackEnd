package com.nhnacademy.board.controller.login;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.request.LoginRequest;
import com.nhnacademy.board.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value= {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class LoginControllerTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    private LoginService loginService;
    private MockHttpSession session;
    @BeforeEach
    void setUp(){
        loginService = mock(LoginService.class);
        session = new MockHttpSession();


        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .addFilter(new HiddenHttpMethodFilter())
                .build();
    }

    @Test
    @DisplayName("관리자 로그인")
    void getLogin() throws Exception {
        User user = new User("admin","1234","관리자");

        MultiValueMap<String, String> loginRequest = new LinkedMultiValueMap<>();
        loginRequest.add("userId", "admin");
        loginRequest.add("userPassword", "1234");

        //when
        when(loginService.adminMatch(user)).thenReturn(true);
        //then
        mockMvc.perform(post("/login")
                        .params(loginRequest)
                        .session(session)
                ).andDo(print())
                .andExpect(redirectedUrl("/user?page=1"));
    }
    @Test
    @DisplayName("사용자 로그인")
    void loginForm() throws Exception {
        User user = new User("student1","1q","아카데미1");
        MultiValueMap<String, String> loginRequest = new LinkedMultiValueMap<>();
        loginRequest.add("userId", "student1");
        loginRequest.add("userPassword", "1q");

        LoginRequest loginRequest2 = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest2,"userId","admin");
        ReflectionTestUtils.setField(loginRequest2,"userPassword","1234");
        //when
        when(loginService.match(user,loginRequest2)).thenReturn(true);
        //then
        mockMvc.perform(post("/login")
                        .params(loginRequest)
                        .session(session)
                ).andDo(print())
                .andExpect(redirectedUrl("/post?page=1"));
    }
    @Test
    @DisplayName("잘못된 로그인")
    void loginFail() throws Exception{
        User user = new User("12","1q","아카데미1");
        MultiValueMap<String, String> loginRequest = new LinkedMultiValueMap<>();
        loginRequest.add("userId", "admin5");
        loginRequest.add("userPassword", "12345");

        LoginRequest loginRequest2 = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest2,"userId","admin5");
        ReflectionTestUtils.setField(loginRequest2,"userPassword","12345");
        //when
        when(loginService.match(user,loginRequest2)).thenReturn(true);
        when(loginService.adminMatch(user)).thenReturn(true);
        //then
        mockMvc.perform(post("/login")
                        .params(loginRequest)
                        .session(session)
                ).andDo(print())
                .andExpect(redirectedUrl("/login"));
    }
    @Test
    void logout() {

    }
}