package com.medical.backendsystem.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.TokenService;

@RestController
@RequestMapping("/api")
public class LogoutController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountService accountService;

    // API Logout
    @GetMapping("/logout")
    public ResponseEntity<BaseResponse> deleteToken(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        BaseResponse response = new BaseResponse();

        LOGGER.info("Logout request");
        LOGGER.info("Token: {}", token);

        if (tokenService.deleteToken(token, accountService.findByEmail(email).getId())) {
            response.setMessage("Logout successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }

        response.setMessage("Logout failed");
        response.setData(null);
        return ResponseEntity.status(503).body(response);
    }

}
