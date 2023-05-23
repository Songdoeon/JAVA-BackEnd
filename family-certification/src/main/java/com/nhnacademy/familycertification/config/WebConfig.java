package com.nhnacademy.familycertification.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.familycertification.controller.ControllerBase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

@EnableWebMvc
@EnableSpringDataWebSupport
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = ControllerBase.class)
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware, MessageSourceAware {
    private ApplicationContext applicationContext;
    private MessageSource messageSource;
    private final ObjectMapper objectMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 기본으로 등록되는 MappingJackson2HttpMessageConverter 제거함
        converters.removeIf(o->o instanceof MappingJackson2HttpMessageConverter);
        // bean으로 등록해놓은 objectMapper를 주입하여MappingJackson2HttpMessageConverter 생성
        HttpMessageConverter converter =  new MappingJackson2HttpMessageConverter(objectMapper);
        //추가
        converters.add(converter);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setViewNames(new String[]{"*"});
        return thymeleafViewResolver;
    }
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(springResourceTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource);
        //권한
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setCacheable(false);
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return springResourceTemplateResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // registry.jsp("/WEB-INF/views/",".jsp");
        registry.viewResolver(thymeleafViewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addRedirectViewController("/redirect-index", "/login");
    }

    // TODO#3: 컨텐츠 협상 전략 설정
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .parameterName("format")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
