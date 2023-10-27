package com.medical.backendsystem.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.TokenService;

@RestController
@RequestMapping("/api")
public class LogoutController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
    @Autowired
    private TokenService tokenService;

    // API Logout
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> deleteToken(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        BaseResponse response = new BaseResponse();

        LOGGER.info("Logout request");
        LOGGER.info("Token: {}", token);

        if (tokenService.deleteToken(token)) {
            response.setMessage("Logout successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }

        response.setMessage("Logout failed");
        response.setData(null);
        return ResponseEntity.status(503).body(response);
    }

}
