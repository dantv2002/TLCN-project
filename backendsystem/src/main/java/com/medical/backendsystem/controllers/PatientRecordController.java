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
        List<PatientEntity> patient = patientService.findByEmail(email);
        if (patient.size() == 0) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = false
        patient.get(0).setIsDeleted(false);
        PatientEntity patientResult = patientService.save(patient.get(0));
        response.setMessage("Create patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/read")
    public ResponseEntity<BaseResponse> read(@RequestParam String email){
        BaseResponse response = new BaseResponse();
        logger.info("Read patient record request");
        // Check email exist
        List<PatientEntity> patient = patientService.findByEmailAndIsDeleted(email, false);
        if (patient.size() == 0) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        response.setMessage("Read patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patient.get(0));
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody PatientRequest patientRequest){
        BaseResponse response = new BaseResponse();
        logger.info("Update patient record request");
        // Check email exist
        List<PatientEntity> patient = patientService.findByEmail(patientRequest.getEmail());
        if (patient.size() == 0) {
            response.setMessage("Patient record of " + patientRequest.getEmail() + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update patient record
        patient.get(0).setFullName(patientRequest.getFullname());
        patient.get(0).setBirthday(patientRequest.getBirthday());
        patient.get(0).setGender(patientRequest.getGender());
        patient.get(0).setAddress(patientRequest.getAddress());
        patient.get(0).setPhoneNumber(patientRequest.getPhonenumber());
        patient.get(0).setEmail(patientRequest.getEmail());
        patient.get(0).setIdentificationCard(patientRequest.getIdentificationCard());
        patient.get(0).setAllergy(patientRequest.getAllergy());
        PatientEntity patientResult = patientService.save(patient.get(0));
        response.setMessage("Update patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping("/delete")
    public ResponseEntity<BaseResponse> delete(@RequestParam String email){
        BaseResponse response = new BaseResponse();
        logger.info("Delete patient record request");
        // Check email exist
        List<PatientEntity> patient = patientService.findByEmail(email);
        if (patient.size() == 0) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = true
        patient.get(0).setIsDeleted(true);
        PatientEntity patientResult = patientService.save(patient.get(0));
        response.setMessage("Delete patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}