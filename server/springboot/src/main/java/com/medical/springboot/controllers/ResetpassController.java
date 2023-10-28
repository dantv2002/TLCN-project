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
import com.medical.springboot.models.request.ChangePasswordRequest;
import com.medical.springboot.models.request.ResetpassRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.AccountService;
import com.medical.springboot.services.CryptographyService;
import com.medical.springboot.services.VerifyService;

/**
 * ResetpassController
 */
@RestController
@RequestMapping("/api")
public class ResetpassController {

    private static final Logger logger = LoggerFactory.getLogger(ResetpassController.class);

    @Autowired
    private VerifyService verifyService;
    @Autowired
    private CryptographyService cryptographyRSAService;
    @Autowired
    private AccountService accountService;

    // API Reset Password
    @PostMapping("/resetPass")
    public ResponseEntity<BaseResponse> resetPass(@RequestBody ResetpassRequest resetPassRequest) throws Exception {
        BaseResponse response = new BaseResponse();
        logger.info("ResetPass request");
        // // Check email exists
        if (!accountService.isExistsByEmail(resetPassRequest.getEmail())) {
            logger.info("Email not exists");
            response.setMessage("Email not exists");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // Verify code
        if (!verifyService.verifyCode(resetPassRequest.getEmail(), resetPassRequest.getVerifycode())) {
            logger.info("Verify code is incorrect");
            response.setMessage("Verify code is incorrect");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // Update password
        // decrypt RSA and encrypt password
        AccountEntity accountModelResult = accountService.update(resetPassRequest.getEmail(),
                BCrypt.hashpw(cryptographyRSAService.Decrypt(resetPassRequest.getPassword()), // Encrypt password string
                                                                                              // body request changed
                        BCrypt.gensalt(10)));
        response.setData(new HashMap<String, Object>() {
            {
                accountModelResult.setPassword(null);
                put("account", accountModelResult);
            }
        });
        // delete verify code
        verifyService.delete(resetPassRequest.getEmail());

        logger.info("Update password successfully");
        response.setMessage("Update password successfully");
        return ResponseEntity.status(200).body(response);
    }

    // API Change Password auth
    @PostMapping("/auth/changePass")
    public ResponseEntity<BaseResponse> changePass(@RequestBody ChangePasswordRequest changePasswordRequest)
            throws Exception {
        BaseResponse response = new BaseResponse();
        logger.info("ChangePass request");
        //
        // Check email exists
        if (!accountService.isExistsByEmail(changePasswordRequest.getEmail())) {
            logger.info("Email not exists");
            response.setMessage("Email not exists");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // Verify password old
        String passOld = accountService.findByEmail(changePasswordRequest.getEmail()).getPassword();
        String DecryptPassOld = cryptographyRSAService.Decrypt(changePasswordRequest.getPasswordOld()); // Encrypt
                                                                                                        // password
                                                                                                        // string
                                                                                                        // body
                                                                                                        // request
                                                                                                        // changed
        if (!BCrypt.checkpw(DecryptPassOld, passOld)) {
            logger.info("Password not match");
            response.setMessage("Password is Incorrect");
            response.setData(null);
            return ResponseEntity.status(401).body(response);
        }
        // Update password new
        // decrypt RSA and encrypt password new
        AccountEntity accountModelResult = accountService.update(changePasswordRequest.getEmail(),
                BCrypt.hashpw(cryptographyRSAService.Decrypt(changePasswordRequest.getPasswordNew()), // Encrypt
                                                                                                      // password
                                                                                                      // string body
                                                                                                      // request
                                                                                                      // changed
                        BCrypt.gensalt(10)));
        response.setData(new HashMap<String, Object>() {
            {
                accountModelResult.setPassword(null);
                put("account", accountModelResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}