package com.medical.backendsystem.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.medical.backendsystem.services.CryptographyService;


@RestController
@RequestMapping("/api")
public class ControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    @Autowired
    private CryptographyService cryptographyRSAService;

    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/login")
    public String homepage() {
        
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/callhello")
    public String callhello() {
        String url = "http://localhost:8080/api/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/RSA")
    public String RSA() {
        try {
            String DecryptPass = cryptographyRSAService.Encrypt("456");
            System.out.println("KQ: " + DecryptPass);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("Error: {}", e.getMessage());
        }
        return "RSA";
    }
}
    