package com.medical.springboot.controllers;

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

import com.medical.springboot.models.entity.AccountEntity;
import com.medical.springboot.models.entity.PatientEntity;
import com.medical.springboot.models.request.SignupRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.AccountService;
import com.medical.springboot.services.CryptographyService;
import com.medical.springboot.services.PatientService;
import com.medical.springboot.services.VerifyService;

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
    public ResponseEntity<BaseResponse> signUp(@RequestBody SignupRequest signupRequest) throws Exception {
        BaseResponse response = new BaseResponse();

        logger.info("SignUp request");
        logger.info("Username: " + signupRequest.getFullname());
        //
        // Verify code
        if (!verifyService.verifyCode(signupRequest.getEmail(), signupRequest.getVerifycode())) {
            response.setMessage("Verify code is incorrect");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // Create account
        String encryptPass = BCrypt.hashpw(cryptographyRSAService.Decrypt(signupRequest.getPassword()), // Encrypt
                                                                                                        // password
                                                                                                        // string body
                                                                                                        // request
                                                                                                        // changed
                BCrypt.gensalt(10));
        AccountEntity account = accountService
                .create(new AccountEntity(signupRequest.getEmail(), encryptPass, "PATIENT", true));
        //
        PatientEntity patient = patientService
                .create(new PatientEntity(signupRequest.getFullname(), signupRequest.getBirthday(), null,
                        signupRequest.getAddress(), signupRequest.getPhonenumber(), signupRequest.getEmail(), null,
                        null, null, true, null));
        //
        response.setData(new HashMap<String, Object>() {
            {
                account.setPassword(null);
                put("account", account);
                put("patient", patient);
            }
        });
        // delete verify code
        verifyService.delete(signupRequest.getEmail());

        logger.info("Sign up successfully");
        response.setMessage("Sign up successfully");
        return ResponseEntity.status(200).body(response);
    }
}
