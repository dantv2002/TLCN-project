package com.medical.backendsystem.controllers;

import java.util.HashMap;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.AccountModel;
import com.medical.backendsystem.models.request.ResetpassRequest;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.AccountService;
import com.medical.backendsystem.services.CryptographyService;
import com.medical.backendsystem.services.VerifyService;

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
    public ResponseEntity<BaseResponse> resetPass(@RequestBody ResetpassRequest resetPassRequest) {
        BaseResponse response = new BaseResponse();
        logger.info("ResetPass request");
        //
        try {
            // Check email exists
            List<AccountModel> accountModel = accountService.findByEmail(resetPassRequest.getEmail());
            if (accountModel.size() == 0) {
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
            accountModel.get(0)
                    .setPassword(BCrypt.hashpw(cryptographyRSAService.Decrypt(resetPassRequest.getPassword()),// Encrypt password string body request changed
                            BCrypt.gensalt(10)));
            AccountModel accountModelResult = accountService.save(accountModel.get(0));
            
            response.setData(new HashMap<String, Object>() {
                {
                    accountModelResult.setPassword(null);
                    put("account", accountModelResult);
                }
            });
            // delete verify code
            verifyService.deleteCode(resetPassRequest.getEmail());
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            response.setMessage("Internal Server Error");
            response.setData(null);
            return ResponseEntity.status(500).body(response);
        }
        logger.info("Update password successfully");
        response.setMessage("Update password successfully");
        return ResponseEntity.status(200).body(response);
    }
}