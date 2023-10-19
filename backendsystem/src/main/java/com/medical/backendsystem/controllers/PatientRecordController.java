package com.medical.backendsystem.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.backendsystem.models.PatientModel;
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
        List<PatientModel> patientModel = patientService.findByEmail(email);
        if (patientModel.size() == 0) {
            response.setMessage("Email does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = false
        patientModel.get(0).setIsDeleted(false);
        PatientModel patientResult = patientService.save(patientModel.get(0));
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
        List<PatientModel> patientModel = patientService.findByEmailAndIsDeleted(email, false);
        if (patientModel.size() == 0) {
            response.setMessage("Patient record of " + email + " does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        response.setMessage("Read patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientModel.get(0));
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody PatientRequest patientRequest){
        BaseResponse response = new BaseResponse();
        logger.info("Update patient record request");
        // Check email exist
        List<PatientModel> patientModel = patientService.findByEmail(patientRequest.getEmail());
        if (patientModel.size() == 0) {
            response.setMessage("Email does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update patient record
        patientModel.get(0).setFullName(patientRequest.getFullname());
        patientModel.get(0).setBirthday(patientRequest.getBirthday());
        patientModel.get(0).setGender(patientRequest.getGender());
        patientModel.get(0).setAddress(patientRequest.getAddress());
        patientModel.get(0).setPhoneNumber(patientRequest.getPhonenumber());
        patientModel.get(0).setEmail(patientRequest.getEmail());
        patientModel.get(0).setIdentificationCard(patientRequest.getIdentificationCard());
        patientModel.get(0).setAllergy(patientRequest.getAllergy());
        PatientModel patientResult = patientService.save(patientModel.get(0));
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
        List<PatientModel> patientModel = patientService.findByEmail(email);
        if (patientModel.size() == 0) {
            response.setMessage("Email does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // update isDeleted = true
        patientModel.get(0).setIsDeleted(true);
        PatientModel patientResult = patientService.save(patientModel.get(0));
        response.setMessage("Delete patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patientResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}