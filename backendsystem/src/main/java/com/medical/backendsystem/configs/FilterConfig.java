package com.medical.backendsystem.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.medical.backendsystem.utils.TokenFilter;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tokenFilterBean());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    @Bean
    public TokenFilter tokenFilterBean() {
        return new TokenFilter();
    }
}
