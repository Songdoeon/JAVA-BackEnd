package com.nhnacademy.nhnboard.config;

import com.nhnacademy.nhnboard.user.domain.User;
import com.nhnacademy.nhnboard.user.repository.UserRepository;
import com.nhnacademy.nhnboard.user.repository.impl.MemoryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = {com.nhnacademy.nhnboard.Base.class}, excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public UserRepository userRepository(){
        UserRepository userRepository = new  MemoryUserRepository();
        userRepository.add(User.createAdmin("admin","1234","마르코",null));
        RandomDataGenerator generator = new RandomDataGenerator();
        for(int i=1; i<=100; i++){
            userRepository.add(User.createUser("user"+i, "1234", "유저" +i, null));
        }
        return userRepository;
    }

}
