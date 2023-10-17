package com.medical.backendsystem.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.models.request.LoginRequest;
import com.medical.backendsystem.repositories.AccountRepository;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.CryptographyService;
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

    // API Login
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> responseData = new HashMap<>();
        logger.info("Login request");

        List<AccountModel> account = accountService.findByEmail(loginRequest.getEmail());
        if (account.size() > 0) {
            try {
                String DecryptPass = cryptographyRSAService.Decrypt(loginRequest.getPassword());
                if (!BCrypt.checkpw(DecryptPass, account.get(0).getPassword())) {
                    logger.info("Password not match");
                    responseData.put("message", "The Username or Password is Incorrect");
                    responseData.put("data", null);
                    return ResponseEntity.status(400).body(responseData);
                }
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(account.get(0).getRole()));
                String token = tokenService.generateToken(authorities, loginRequest.getEmail());
                logger.info("Token generated: {}", token);
                logger.info("For Username: {}", loginRequest.getEmail());
                //
                responseData.put("message", "Create token successfully");
                responseData.put("data", new HashMap<String, Object>() {
                    {
                        put("token", token);
                    }
                });
                //
                return ResponseEntity.status(200).body(responseData);
            } catch (Exception e) {
                responseData.put("message", "Internal Server Error");
                responseData.put("data", null);
                return ResponseEntity.status(500).body(responseData);
            }
        }
        logger.info("Email not match");
        responseData.put("message", "The Username or Password is Incorrect");
        responseData.put("data", null);
        return ResponseEntity.status(400).body(responseData);
    }
    // @GetMapping("/RSA")
    // public String RSA(){
    // try {
    // String DecryptPass = cryptographyRSAService.Encrypt("lasznhffgywdikzt");
    // System.out.println("KQ: "+DecryptPass);
    // } catch (Exception e) {
    // // TODO: handle exception
    // logger.error("Error: {}", e.getMessage());
    // }
    // return "RSA";
    // }
}