package com.medical.backendsystem.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.entity.PatientEntity;
import com.medical.backendsystem.models.request.PatientRequest;
import com.medical.backendsystem.models.response.BaseResponse;
import com.medical.backendsystem.services.PatientService;

/**
 * PapatientRecordController
 */
@RestController
@RequestMapping("/api/PatientRecord")
public class PatientRecordController {

    private static final Logger logger = LoggerFactory.getLogger(PatientRecordController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/create")
    public ResponseEntity<BaseResponse> create(@RequestParam String email) {
        BaseResponse response = new BaseResponse();
        logger.info("Create patient record request");
        // Check email exist
        if (!patientService.isexistsByEmail(email)) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = false
        PatientEntity patientResult = patientService.create(email);
        // response
        response.setMessage("Create patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/read")
    public ResponseEntity<BaseResponse> read(@RequestParam String email) {
        BaseResponse response = new BaseResponse();
        logger.info("Read patient record request");
        // Check email exist
        if (!patientService.isexistsByEmailAndIsDeleted(email, false)) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        PatientEntity patient = patientService.findByEmailAndIsDeleted(email, false);
        // response
        response.setMessage("Read patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patient);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody PatientRequest patientRequest) {
        BaseResponse response = new BaseResponse();
        logger.info("Update patient record request");
        // Check email exist
        if (!patientService.isexistsByEmail(patientRequest.getEmail())) {
            response.setMessage("Patient record of " + patientRequest.getEmail() + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update patient record
        PatientEntity patientResult = patientService.update(patientRequest.getFullname(), patientRequest.getBirthday(),
                patientRequest.getGender(), patientRequest.getAddress(), patientRequest.getPhonenumber(),
                patientRequest.getEmail(), patientRequest.getIdentificationCard(), patientRequest.getAllergy());
        response.setMessage("Update patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam String email) {
        BaseResponse response = new BaseResponse();
        logger.info("Delete patient record request");
        // Check email exist
        if (!patientService.isexistsByEmail(email)) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = true
        PatientEntity patientResult = patientService.delete(email);
        response.setMessage("Delete patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}