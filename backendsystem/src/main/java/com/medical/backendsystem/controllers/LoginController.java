package com.medical.backendsystem.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.entity.AccountEntity;
import com.medical.backendsystem.models.entity.PatientEntity;
import com.medical.backendsystem.models.request.LoginRequest;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.CryptographyService;
import com.medical.backendsystem.services.PatientService;
import com.medical.backendsystem.services.TokenService;

/**
 * AuthController
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CryptographyService cryptographyRSAService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PatientService patientService;

    // API Login
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> generateToken(@RequestBody LoginRequest loginRequest) {
        BaseResponse response = new BaseResponse();
        logger.info("Login request");

        if (accountService.isExistsByEmail(loginRequest.getEmail())) {
            try {
                AccountEntity account = accountService.findByEmail(loginRequest.getEmail());
                String DecryptPass = cryptographyRSAService.Decrypt(loginRequest.getPassword()); // Encrypt password string body request changed
                if (!BCrypt.checkpw(DecryptPass, account.getPassword())) {
                    logger.info("Password not match");
                    response.setMessage("The Username or Password is Incorrect");
                    response.setData(null);
                    return ResponseEntity.status(401).body(response);
                }
                //
                String token = tokenService.generateToken(account);
                logger.info("Token generated: {}", token);
                logger.info("For Username: {}", loginRequest.getEmail());
                //
                PatientEntity patient = patientService.findByEmail(loginRequest.getEmail());
                response.setMessage("Create token successfully");
                response.setData(new HashMap<String, Object>() {
                    {
                        put("token", token);
                        put("fullname", patient.getFullName());
                        put("email", loginRequest.getEmail());
                    }
                });
                //
                return ResponseEntity.status(200).body(response);
            } catch (Exception e) {
                response.setMessage("Internal Server Error");
                response.setData(null);
                return ResponseEntity.status(500).body(response);
            }
        }
        logger.info("Email not match");
        response.setMessage("The Username or Password is Incorrect");
        response.setData(null);
        return ResponseEntity.status(401).body(response);
    }
}