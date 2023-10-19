package com.medical.backendsystem.controllers;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.entity.PatientEntity;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.EmailService;
import com.medical.backendsystem.services.PatientService;
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
    @Autowired
    private PatientService patientService;

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    // API Send Email Verify Code for Sign Up
    @GetMapping("/sendEmailSignUp")
    public ResponseEntity<BaseResponse> sendEmailSignUp(@RequestParam String toemail, @RequestParam String username) {
        BaseResponse response = new BaseResponse();
        //
        if (accountService.isexistsByEmail(toemail)) {
            response.setMessage("Email already exists");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        //
        return sendEmail(toemail, username);
    }

    // API Send Email Verify Code for Reset Password
    @GetMapping("/sendEmailResetPass")
    public ResponseEntity<BaseResponse> sendEmailResetPass(@RequestParam String email) {
        BaseResponse response = new BaseResponse();
        logger.info("ResetPass request");
        logger.info("Email: " + email);
        //
        try {
            if (!accountService.isexistsByEmail(email)) {
                response.setMessage("Email does not exist");
                response.setData(null);
                return ResponseEntity.status(400).body(response);
            }
            //
            PatientEntity patient = patientService.findByEmail(email); // result empty exception
            return sendEmail(patient.getEmail(), patient.getFullName());
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            response.setMessage("Internal Server Error");
            response.setData(null);
            return ResponseEntity.status(500).body(response);
        }
    }

    // Method Send Email Verify Code
    public ResponseEntity<BaseResponse> sendEmail(String toemail, String username) {
        //
        BaseResponse response = new BaseResponse();
        logger.info("Method SendEmail");
        logger.info("To email: " + toemail);
        logger.info("User name: " + username);
        //
        try {
            emailService.sendEmail(toemail, username,
                    verifyService.create(toemail));
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            response.setMessage("Internal Server Error");
            response.setData(null);
            return ResponseEntity.status(500).body(response);
        }
        //
        logger.info("Email sent successfully");
        response.setMessage("Email sent successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("email", toemail);
                put("username", username);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}
