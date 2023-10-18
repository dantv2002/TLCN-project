package com.medical.backendsystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.models.PatientModel;
import com.medical.backendsystem.models.request.EmailRequest;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.EmailService;
import com.medical.backendsystem.services.PatientService;
import com.medical.backendsystem.services.VerifyService;

/**
 * ResetpassController
 */
public class ResetpassController {

    private static final Logger logger = LoggerFactory.getLogger(ResetpassController.class);
    
    @Autowired
    private AccountService accountService;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PatientService patientService;

    private String domainName = "http://localhost:8080";

    public ResponseEntity<?> resetPass(@RequestBody String email) {
        Map<String, Object> responseData = new HashMap<>();
        logger.info("ResetPass request");
        logger.info("Email: " + email);
        //
        List<AccountModel> accountList = accountService.findByEmail(email);
        if (accountList.size() == 0) {
            responseData.put("message", "Email does not exist");
            responseData.put("data", null);
            return ResponseEntity.status(400).body(responseData);
        }
        //
        PatientModel patient = patientService.findByEmail(email).get(0);
        RestTemplate restTemplate = new RestTemplate();
        String uri = domainName + "/api/sendEmail";
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setToemail(patient.getEmail());
        emailRequest.setUsername(patient.getFullName());
        BaseResponse result = restTemplate.postForObject(uri, emailRequest, BaseResponse.class);
        return null;
    }
    
}