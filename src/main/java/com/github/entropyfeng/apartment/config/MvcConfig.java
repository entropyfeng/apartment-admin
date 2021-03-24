package com.github.entropyfeng.apartment.config;

import com.github.entropyfeng.apartment.config.intecepror.AuthGlobalFilter;
import com.github.entropyfeng.apartment.config.intecepror.CurrentUserHandlerMethodArgResolver;
import com.github.entropyfeng.apartment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    CurrentUserHandlerMethodArgResolver currentUserHandlerMethodArgResolver(){
        return new CurrentUserHandlerMethodArgResolver();
    }


    @Autowired
    AuthGlobalFilter authGlobalFilter;

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authGlobalFilter);

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserHandlerMethodArgResolver());
    }
}