package com.medical.backendsystem.controllers;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.models.PatientModel;
import com.medical.backendsystem.models.request.SignupRequest;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.CryptographyService;
import com.medical.backendsystem.services.PatientService;
import com.medical.backendsystem.services.VerifyService;

@RestController
@RequestMapping("/api")
public class SignupController {

    private final static Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private VerifyService verifyService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private CryptographyService cryptographyRSAService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        BaseResponse response = new BaseResponse();

        logger.info("SignUp request");
        logger.info("Username: " + signupRequest.getFullname());
        //
        try {
            // Verify code
            if (!verifyService.verifyCode(signupRequest.getEmail(), signupRequest.getVerifycode())) {
                response.setMessage("Verify code is incorrect");
                response.setData(null);
                return ResponseEntity.status(400).body(response);
            }
            // Create account
            String encryptPass = BCrypt.hashpw(cryptographyRSAService.Decrypt(signupRequest.getPassword()),
                    BCrypt.gensalt(10));
            AccountModel accountModel = accountService
                    .save(new AccountModel(signupRequest.getEmail(), encryptPass, signupRequest.getRole(), true));
            //
            PatientModel patientModel = patientService.save(new PatientModel(signupRequest.getFullname(),
                    signupRequest.getBirthday(), null,
                    signupRequest.getAddress(), signupRequest.getPhonenumber(), signupRequest.getEmail(), null, null));
            //
            response.setData( new HashMap<String, Object>() {
                {
                    put("account", accountModel);
                    put("patient", patientModel);
                }
            });
        } catch (Exception e) {
            response.setMessage("Internal Server Error");
            response.setData(null);
            return ResponseEntity.status(500).body(response);
        }

        logger.info("Sign up successfully");
        response.setMessage("Sign up successfully\"");
        return ResponseEntity.status(200).body(response);
    }
}
