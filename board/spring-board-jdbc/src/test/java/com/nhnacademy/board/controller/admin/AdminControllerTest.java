package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value= {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class AdminControllerTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    private MockHttpSession session;
    @BeforeEach
    void setUp(){
        session = new MockHttpSession();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .addFilter(new HiddenHttpMethodFilter())
                .build();

    }
    @Test
    void admin() throws Exception {
        User expect = new User("admin","1234","관리자");

        User actual = userService.getUser("admin");

        assertEquals(expect.toString(),actual.toString());
    }

    @Test
    void register() {

    }

    @Test
    void addRegister() {
    }

    @Test
    void views() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUpdate() {
    }

    @Test
    void update() {
    }
}