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
    private final TokenService tokenService;
    private final CryptographyService cryptographyRSAService;
    private final AccountService accountService;


    public LoginController(@Autowired TokenService tokenService, @Autowired CryptographyService cryptographyRSAService, @Autowired AccountService accountService){
        this.tokenService = tokenService;
        this.cryptographyRSAService = cryptographyRSAService;
        this.accountService = accountService;
    }
    //Create Account
    
    //API Login
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest){
        Map<String, Object> responseData = new HashMap<>();
        logger.info("Login request");
        
        List<AccountModel> account = accountService.findByEmail(loginRequest.getEmail());
        if(account.size() > 0)
        {
            try {
                boolean valuatePass = false;
                try {
                    String DecryptPass = cryptographyRSAService.Decrypt(loginRequest.getPassword());
                    valuatePass = BCrypt.checkpw(DecryptPass, account.get(0).getPassword());
                } catch (Exception e) {
                    // TODO: handle exception
                    logger.debug("Password not match");
                    responseData.put("message", "The Username or Password is Incorrect");
                    return ResponseEntity.status(401).body(responseData);
                }
                if(!valuatePass)
                {
                    logger.debug("Password not match");
                    responseData.put("message", "The Username or Password is Incorrect");
                    return ResponseEntity.status(401).body(responseData);
                }
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(account.get(0).getRole()));
                try{
                    String token = tokenService.generateToken(authorities, loginRequest.getEmail());
                    logger.debug("Token generated: {}", token);
                    logger.debug("For Username: {}", loginRequest.getEmail());
                    //
                        responseData.put("Token", token);
                    //
                    return ResponseEntity.status(200).body(responseData);
                }
                catch(Exception e){
                    logger.error("Error: {}", e.getMessage());
                    responseData.put("message", "Internal Server Error");
                    return ResponseEntity.status(500).body(responseData);
                }
            } catch (Exception e) {
                // TODO: handle exception
                responseData.put("message", "Internal Server Error");
                return ResponseEntity.status(500).body(responseData);
            }
        }
        logger.debug("Email not match");
        responseData.put("message", "The Username or Password is Incorrect");
        return ResponseEntity.status(401).body(responseData);
    }
    @GetMapping("/RSA")
    public String RSA(){
        try {
            String DecryptPass = cryptographyRSAService.Encrypt("mongodb+srv://MedicalDB:MedicalDB123321@medicalcluster.3xnzgcp.mongodb.net");
            System.out.println("KQ: "+DecryptPass);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("Error: {}", e.getMessage());
        }
        return "RSA";        
    }
}