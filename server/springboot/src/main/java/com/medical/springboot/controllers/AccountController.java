package com.medical.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.springboot.models.entity.AccountEntity;
import com.medical.springboot.models.entity.DoctorEntity;
import com.medical.springboot.models.request.DoctorRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.AccountService;
import com.medical.springboot.services.DoctorService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private DoctorService doctorService;

    // Create account
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> doctorCreate(@RequestBody DoctorRequest request) throws Exception {
        logger.info("doctorCreate");
        BaseResponse response = new BaseResponse();
        // Check email exists
        if (accountService.isExistsByEmail(request.getEmail())) {
            logger.info("Email already exists");
            response.setMessage("Email already exists");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // Encrypt password
        String encryptPass = BCrypt.hashpw(request.getPassword(),
                BCrypt.gensalt(10));
        // Create account
        accountService.create(new AccountEntity(request.getEmail(), encryptPass, "DOCTOR", true));
        response.setMessage("Create account successfully");
        // Create doctor
        doctorService.create(new DoctorEntity(request.getFullName(), request.getBirthday(), request.getGender(),
                request.getAddress(), request.getPhoneNumber(), request.getEmail(), request.getIdentificationCard(),
                request.getDepartment(), request.getTitle()));
        response.setData(null);
        return ResponseEntity.status(201).body(response);
    }

    // Delete account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        if (accountService.delete(id)) {
            response.setMessage("Delete account successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }
        response.setMessage("Delete account failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    // Set active account
    @GetMapping("/active/{id}")
    public ResponseEntity<BaseResponse> setActive(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        if (accountService.activeAccount(id)) {
            response.setMessage("Set active account successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }
        response.setMessage("Set active account failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    // Set deactive account
    @GetMapping("/deactive/{id}")
    public ResponseEntity<BaseResponse> setDeactive(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        if (accountService.deactiveAccount(id)) {
            response.setMessage("Set deactivate account successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }
        response.setMessage("Set deactivate account failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    // Reset password
    @PutMapping("password/reset/{id}")
    public ResponseEntity<BaseResponse> resetPassword(@PathVariable("id") String id,
            @RequestBody Map<String, String> request) throws Exception {
        BaseResponse response = new BaseResponse();
        String password = request.get("password");
        // Check account exists
        if (!accountService.isExistsById(id)) {
            logger.info("Account does not exist");
            response.setMessage("Account does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }

        // Reset password
        AccountEntity accountModelResult = accountService.findById(id).map(account -> {
            try {
                account.setPassword(
                        BCrypt.hashpw(password,
                                BCrypt.gensalt(10)));
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new RuntimeException("Error: RSA decrypt password");
            }
            return accountService.update(account);
        }).orElseThrow(() -> new Exception("Account not found"));
        response.setMessage("Reset password successfully");
        response.setData(new HashMap<String, Object>() {
            {
                accountModelResult.setPassword(null);
                put("account", accountModelResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}
