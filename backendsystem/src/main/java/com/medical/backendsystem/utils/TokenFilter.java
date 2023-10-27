package com.medical.backendsystem.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.medical.backendsystem.services.TokenService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TokenFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        logger.info("TokenFilter doFilter");
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (token == null) {
            logger.info("Token is null");
            chain.doFilter(request, response);
            return;
        }
        token = token.substring(7);
        logger.info("Token: {}", token);
        if (!this.tokenService.isExistsByToken(token)) {
            logger.info("Token is not exists");
            throw new ServletException(new AccessDeniedException("Access Denied"));
        }
        chain.doFilter(request, response);
    }

}
