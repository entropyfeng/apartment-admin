package com.github.entropyfeng.apartment.config;

import com.github.entropyfeng.apartment.config.intecepror.AuthGlobalFilter;
import com.github.entropyfeng.apartment.config.intecepror.CurrentUserHandlerMethodArgResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    public MvcConfig(AuthGlobalFilter authGlobalFilter) {
        this.authGlobalFilter = authGlobalFilter;
    }

    @Bean
    CurrentUserHandlerMethodArgResolver currentUserHandlerMethodArgResolver(){
        return new CurrentUserHandlerMethodArgResolver();
    }


    final
    AuthGlobalFilter authGlobalFilter;

    private static final Logger logger= LoggerFactory.getLogger(MvcConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authGlobalFilter).excludePathPatterns("/account/login")
                .excludePathPatterns("/swagger-ui.html")
                .addPathPatterns("/webjars/**")
                .excludePathPatterns("/v2/api-docs");

    }

/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
                 registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
                 registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserHandlerMethodArgResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")    //允许任何头
                .allowedOrigins("*")    //允许任何域名
                .allowedMethods("*");   //允许任何方法

        logger.info("add cros mapping");
    }
}
