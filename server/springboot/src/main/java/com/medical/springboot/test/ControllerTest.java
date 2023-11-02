package com.medical.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.medical.springboot.services.CryptographyService;

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
            String DecryptPass = cryptographyRSAService.Decrypt(
                    "PpZn3p/kNzK35gDBSr7jqcFE74jQxdrAMRVO2dzW2B0VQ6BwX+QM1ah20ShJmAgAmCsNjxUmF4FTJ4F/9YBq7M7IJgXcnPup+sl4HVQcCuZaAxJtPBXlgHsNYTW275oTyKj0zrOGpjkOtVO5YYCF0YuhsOTqpz7qLyounziObUR42bns2Gs/Q7kFY2cuQk6aCvHs7sK4AArdR8CX0t8pSdGUG/gs6Xe2yqqSyhF1EZ6GpN9VIki1Gx3jtw3/wrvlJAIGMnvBsmcm8v6SjcyXCqK3x1HNALrMD/yS6wsgrWuHoXRlWTIEWiq3NzSydwog5wjU7e9jYfdC7OKMvAbeJg==");
            System.out.println("KQ: " + DecryptPass);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("Error: {}", e.getMessage());
        }
        return "RSA";
    }
}
