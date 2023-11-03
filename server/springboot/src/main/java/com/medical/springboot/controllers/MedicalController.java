package com.medical.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.springboot.models.entity.MedicalEntity;
import com.medical.springboot.models.request.CreateMedicalRequest;
import com.medical.springboot.models.request.UpdateMedicalRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.MedicalService;
import com.medical.springboot.utils.IAuthenticationFacade;

// MedicalController
@RestController
@RequestMapping("/api/medical")
public class MedicalController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
    @Autowired
    private MedicalService medicalService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    // Doctor create for patient
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> create(@RequestBody CreateMedicalRequest request) {
        String doctorId = authenticationFacade.getAuthentication().getName();
        BaseResponse response = new BaseResponse();
        LOGGER.info("Create medical request");
        LOGGER.info("Medical of patient: {}", request.getPatientId());
        MedicalEntity medicalResult = medicalService.create(new MedicalEntity(request.getDepartment(),
                request.getClinics(), request.getDate(), doctorId, request.getClinicalDiagnosis(), null,
                request.getDiagnosis(), request.getPatientId()));
        if (medicalResult != null) {
            response.setMessage("Create medical success for patient: " + request.getPatientId());
            response.setData(request);
            return ResponseEntity.status(200).body(response);

        }
        response.setMessage("Create medical failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    // Read medicals
    @GetMapping("/read/me")
    public ResponseEntity<BaseResponse> read() {
        String patientId = authenticationFacade.getAuthentication().getName();
        LOGGER.info("Read medicals for patient request");
        LOGGER.info("Patient id: {}", patientId);
        return readMedical(patientId);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<BaseResponse> read(@PathVariable("id") String patientId) {
        LOGGER.info("Read medicals for doctor request");
        return readMedical(patientId);
    }

    // Read medicals of patientId
    private ResponseEntity<BaseResponse> readMedical(String patientId) {
        BaseResponse response = new BaseResponse();
        LOGGER.info("Read medicals request");
        LOGGER.info("Patient id: {}", patientId);
        response.setMessage("Read medicals success");
        response.setData(new HashMap<>() {
            {
                put("medicals", medicalService.readAllByPatientId(patientId));
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    // Read medicals detail
    @PostMapping("/read/detail/{id}")
    public ResponseEntity<BaseResponse> readDetail(@PathVariable("id") String medicalId) {
        BaseResponse response = new BaseResponse();
        LOGGER.info("Read medicals detail request");
        LOGGER.info("Medical id: {}", medicalId);
        MedicalEntity medical = medicalService.findById(medicalId)
                .orElseThrow(() -> new RuntimeException("Medical not found"));
        response.setMessage("Read medicals detail success");
        response.setData(new HashMap<>() {
            {
                put("medical", medical);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    // Update medical for doctor
    @PutMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateMedicalRequest request) {
        String doctorId = authenticationFacade.getAuthentication().getName();
        BaseResponse response = new BaseResponse();
        LOGGER.info("Update medical of patient request");
        LOGGER.info("Medical of patient: {}", request.getPatientId());
        MedicalEntity medicalResult = medicalService.findById(request.getId()).map(medical -> {
            medical.setDepartment(request.getDepartment());
            medical.setClinics(request.getClinics());
            medical.setDate(request.getDate());
            medical.setDoctorId(doctorId);
            medical.setClinicalDiagnosis(request.getClinicalDiagnosis());
            medical.setDiagnosis(request.getDiagnosis());
            medical.setPatientId(request.getPatientId());
            return medicalService.update(medical);
        }).orElseThrow(() -> new RuntimeException("Medical not found"));
        if (medicalResult != null) {
            response.setMessage("Update medical success for patient: " + request.getPatientId());
            response.setData(new HashMap<>() {
                {
                    put("medical", medicalResult);
                }
            });
            return ResponseEntity.status(200).body(response);

        }
        response.setMessage("Update medical failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    // Delete medical
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String medicalId) {
        BaseResponse response = new BaseResponse();
        LOGGER.info("Delete medical request");
        LOGGER.info("Medical id: {}", medicalId);

        if (medicalService.delete(medicalId)) {
            response.setMessage("Delete medical success");
            response.setData(null);
            return ResponseEntity.status(200).body(response);
        }

        response.setMessage("Delete medical failed");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }
    // Image diagnosis

}
