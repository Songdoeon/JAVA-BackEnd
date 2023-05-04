package com.nhnacademy.nhnboard.login.controller;

import com.nhnacademy.nhnboard.config.RootConfig;
import com.nhnacademy.nhnboard.config.WebConfig;
import com.nhnacademy.nhnboard.login.dto.LoginRequest;
import com.nhnacademy.nhnboard.login.service.LoginService;
import com.nhnacademy.nhnboard.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;


import javax.xml.validation.Validator;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    @DisplayName("관리자 - 로그인 성공")
    void loginAction() throws Exception {
        //given
        User user = User.createAdmin("admin","1234","관리자",null);

        MultiValueMap<String, String> loginRequest = new LinkedMultiValueMap<>();
        loginRequest.add("id", "admin");
        loginRequest.add("password", "1234");


        //when
        when(loginService.doLogin(anyString(),anyString())).thenReturn(user);

        //then
        mockMvc.perform(
                    post("/login")
                            .params(loginRequest)
                            .session(session)
                ).andDo(print())
                .andExpect(redirectedUrl("/admin/users/"));
    }

    @Test
    void logoutAction() {
    }
}