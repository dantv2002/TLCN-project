package com.medical.backendsystem.controllers;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.entity.AccountEntity;
import com.medical.backendsystem.models.entity.PatientEntity;
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
    public ResponseEntity<BaseResponse> signUp(@RequestBody SignupRequest signupRequest) {
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
            String encryptPass = BCrypt.hashpw(cryptographyRSAService.Decrypt(signupRequest.getPassword()), // Encrypt password string body request changed
                    BCrypt.gensalt(10));
            AccountEntity account = accountService
                    .save(new AccountEntity(signupRequest.getEmail(), encryptPass, "PATIENT", true));
            //
            PatientEntity patient = patientService.save(new PatientEntity(signupRequest.getFullname(),
                    signupRequest.getBirthday(), null,
                    signupRequest.getAddress(), signupRequest.getPhonenumber(), signupRequest.getEmail(), null, null, true));
            //
            response.setData( new HashMap<String, Object>() {
                {
                    account.setPassword(null);
                    put("account", account);
                    put("patient", patient);
                }
            });
            //delete verify code
            verifyService.deleteCode(signupRequest.getEmail());
        } catch (Exception e) {
            response.setMessage("Internal Server Error");
            response.setData(null);
            return ResponseEntity.status(500).body(response);
        }

        logger.info("Sign up successfully");
        response.setMessage("Sign up successfully");
        return ResponseEntity.status(200).body(response);
    }
}
