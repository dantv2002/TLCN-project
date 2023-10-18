package com.medical.backendsystem.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.response.BaseResponse;

/**
 * ResetpassController
 */
@RestController
@RequestMapping("/api")
public class ResetpassController {

    private static final Logger logger = LoggerFactory.getLogger(ResetpassController.class);

    // API Reset Password
    @GetMapping("/resetPass")
    public ResponseEntity<BaseResponse> resetPass() {
        BaseResponse response = new BaseResponse();
        logger.info("ResetPass request");
        return null;
    }
}