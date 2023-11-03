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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.springboot.models.entity.PatientEntity;
import com.medical.springboot.models.request.PatientRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.PatientService;
import com.medical.springboot.utils.IAuthenticationFacade;

/**
 * PapatientRecordController
 */
@RestController
@RequestMapping("/api/auth/PatientRecord")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

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

    // API Read patient record
    @GetMapping("/read/me")
    public ResponseEntity<BaseResponse> read() {
        String personId = authenticationFacade.getAuthentication().getName();
        return readPatient(personId);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<BaseResponse> read(@PathVariable("id") String personId) {
        return readPatient(personId);
    }

    private ResponseEntity<BaseResponse> readPatient(String personId) {
        BaseResponse response = new BaseResponse();
        logger.info("Read patient record request");
        logger.info("Patient id: {}", personId);
        // Check patient record exists
        if (!patientService.isexistsByIdAndIsDeleted(personId, false)) {
            response.setMessage(
                    "Patient record of " + personId + " does not exist. " + "Please create new patient record");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        PatientEntity patient = patientService.findFirstById(personId);
        // response
        response.setMessage("Read patient record successfully");
        response.setData(new HashMap<String, Object>() {
            {
                put("patient", patient);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    // API Update patient record
    @PutMapping("/update/me")
    public ResponseEntity<BaseResponse> update(@RequestBody PatientRequest patientRequest) {
        String personId = authenticationFacade.getAuthentication().getName();
        return updatePatient(personId, patientRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable("id") String personId,
            @RequestBody PatientRequest patientRequest) {
        return updatePatient(personId, patientRequest);
    }

    private ResponseEntity<BaseResponse> updatePatient(String id, PatientRequest patientRequest) {
        BaseResponse response = new BaseResponse();
        logger.info("Update patient record request");
        logger.info("Patient id: {}", id);
        // Check patient record exists
        if (!patientService.isexistsByIdAndIsDeleted(id, false)) {
            response.setMessage("Patient record of " + patientRequest.getEmail() + " does not exist. "
                    + "Please create new patient record");
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        logger.info("Delete patient record request");
        // Check Patient record exists
        if (!patientService.isexistsById(id)) {
            response.setMessage("Patient record does not exist");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
        // delete patient record
        if (patientService.delete(id)) {
            response.setMessage("Delete patient record successfully");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }
        response.setMessage("Delete patient record failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }
}