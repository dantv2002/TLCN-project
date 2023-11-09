package com.medical.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.springboot.models.entity.DiagnosticImageEntity;
import com.medical.springboot.models.entity.MedicalEntity;
import com.medical.springboot.models.request.DiagnosticImageRequest;
import com.medical.springboot.models.response.BaseResponse;
import com.medical.springboot.services.DoctorService;
import com.medical.springboot.services.MedicalService;
import com.medical.springboot.utils.IAuthenticationFacade;

@RestController
@RequestMapping("/api/auth/diagnosisimage")
public class DiagnosisImageController {
    private static final Logger logger = LoggerFactory.getLogger(DiagnosisImageController.class);
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MedicalService medicalService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @PostMapping("/predict")
    public ResponseEntity<BaseResponse> predict(@RequestBody Map<String, String> request) {
        String urlImage = request.get("image");
        BaseResponse response = new BaseResponse();
        logger.info("Predict image");
        //
        String result = doctorService.diagnosisImage(urlImage);
        //
        response.setMessage("Predict image successfully");
        response.setData(new HashMap<>() {
            {
                put("result", result);
            }
        });
        return ResponseEntity.status(200).body(response);
    }

    // Save result to database
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @PostMapping("/save/{id}")
    public ResponseEntity<BaseResponse> save(@RequestBody DiagnosticImageRequest request,
            @PathVariable("id") String id) {
        String doctorId = authenticationFacade.getAuthentication().getName().split(",")[0];
        BaseResponse response = new BaseResponse();
        logger.info("Save result predict image");
        // Create object diagnosis image
        DiagnosticImageEntity diagnosticImage = new DiagnosticImageEntity(request.getMethod(), request.getContent(),
                doctorId, request.getUrlImage(), request.getConclude());
        // Save to database
        // find medical by id
        MedicalEntity medicalResult = medicalService.findById(id).map(medical -> {
            // set diagnosis image
            medical.setDiagnosticImages(diagnosticImage);
            // return medical
            return medicalService.update(medical);
        }).orElseThrow(() -> new RuntimeException("Medical not found"));
        // Return response
        response.setMessage("Save result successfully");
        response.setData(new HashMap<>() {
            {
                put("medical", medicalResult);
            }
        });
        return ResponseEntity.status(200).body(response);
    }
}
