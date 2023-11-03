package com.medical.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.springboot.models.entity.PatientEntity;
import com.medical.springboot.models.request.PatientRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.PatientService;

/**
 * PapatientRecordController
 */
@RestController
@RequestMapping("/api/auth/PatientRecord")
public class PatientRecordController {

    private static final Logger logger = LoggerFactory.getLogger(PatientRecordController.class);

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> create(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        BaseResponse response = new BaseResponse();
        logger.info("Create patient record request");
        // Check email exist
        if (!patientService.isexistsByEmail(email)) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = false
        PatientEntity patientResult = patientService.activate(email);
        // response
        response.setMessage("Create patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/read")
    public ResponseEntity<BaseResponse> read(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
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
        PatientEntity patientResult = patientService.findByEmail(patientRequest.getEmail()).map(patient -> {
            patient.setFullName(patientRequest.getFullname());
            patient.setBirthday(patientRequest.getBirthday());
            patient.setGender(patientRequest.getGender());
            patient.setAddress(patientRequest.getAddress());
            patient.setPhoneNumber(patientRequest.getPhonenumber());
            patient.setIdentificationCard(patientRequest.getIdentificationCard());
            patient.setAllergy(patientRequest.getAllergy());
            patient.setHealthInsurance(patientRequest.getHealthinsurance());
            return patientService.update(patient);
        }).orElseThrow(() -> new RuntimeException("Error: Patient not found"));

        response.setMessage("Update patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id) {
    //     String email = requestBody.get("email");
    //     BaseResponse response = new BaseResponse();
    //     logger.info("Delete patient record request");
    //     // Check email exist
    //     if (!patientService.isexistsByEmail(email)) {
    //         response.setMessage("Patient record of " + email + " does not exist");
    //         response.setData(null);
    //         return ResponseEntity.status(400).body(response);
    //     }
    //     // update isDeleted = true
    //     if (patientService.delete(email)) {
    //         response.setMessage("Delete patient record successfully");
    //         response.setData(null);
    //         return ResponseEntity.status(200).body(response);
    //     }
    //     response.setMessage("Delete patient record failed");
    //     response.setData(null);
    //     return ResponseEntity.status(400).body(response);
    // }
}