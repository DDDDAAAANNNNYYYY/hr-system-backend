package com.hr.hrserver.config;


import com.hr.hrserver.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Value("${services.auth}")
    private String authService;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/spring-rest/protected/account");

        return registrationBean;
    }
}
