package com.medical.backendsystem.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.request.EmailRequest;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.EmailService;
import com.medical.backendsystem.services.VerifyService;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    // API Send Email Verify Code
    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        Map<String, Object> responseData = new HashMap<>();
        logger.info("SendEmail request");

        logger.info("To email: " + emailRequest.getToemail());
        logger.info("User name: " + emailRequest.getUsername());
        //
        if (accountService.findByEmail(emailRequest.getToemail()).size() > 0) {
            responseData.put("message", "Email already exists");
            responseData.put("data", null);
            return ResponseEntity.status(400).body(responseData);
        }
        //
        try {
            emailService.sendEmail(emailRequest.getToemail(), emailRequest.getUsername(),
                    verifyService.createCode(emailRequest.getToemail()));
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            responseData.put("message", "Internal Server Error");
            responseData.put("data", null);
            return ResponseEntity.status(500).body(responseData);
        }
        logger.info("Email sent successfully");
        responseData.put("message", "Email sent successfully");
        responseData.put("data", emailRequest);
        return ResponseEntity.status(200).body(responseData);
    }

}
