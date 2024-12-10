package com.hms_fe.controller;

import com.hms_fe.model.Patient;
import com.hms_fe.service.PatientApiService;
import org.springframework.stereotype.Controller;
import com.hms_fe.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class PatientController {

    @Autowired
    private final PatientApiService patientApiService;
    
    public PatientController(PatientApiService patientApiService) {
        this.patientApiService = patientApiService;
    }

    @GetMapping("/createPatient")
    public String createPatient(Model model) {
        model.addAttribute("showStatus", false);
        model.addAttribute("patientMdl", new Patient());
        model.addAttribute("content", "createPatientFragment");
        return "layout";
    }

    @GetMapping("/editPatient/{id}")
    public Mono<String> editPatient(@PathVariable("id") Long id, Model model) {

        model.addAttribute("content", "createPatientFragment");

        return this.patientApiService.getPatient(id)
                .doOnSuccess(patient -> {
                    model.addAttribute("patientMdl", patient);
                })
                .thenReturn("layout");
    }

    @PostMapping("/savePatient")
    public Mono<String> savePatient(@ModelAttribute Patient patientMdl, Model model) {

        model.addAttribute("showStatus", true);
        model.addAttribute("patientMdl", patientMdl);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Update failed"));
        model.addAttribute("content", "createPatientFragment");

        Mono<ApiResponse> apiResponseMono;

        if( patientMdl.getId() != null ) {
            apiResponseMono = this.patientApiService.updatePatient(patientMdl);
        }
        else {
            apiResponseMono = this.patientApiService.createPatient(patientMdl);
        }

        return apiResponseMono.doOnSuccess(apiResponse -> {
                    model.addAttribute("apiResponse", apiResponse);
                })
                .thenReturn("layout");
    }

    @GetMapping(value={"/showPatients"})
    public Mono<String> showPatients(Model model) {

        model.addAttribute("patients", null);
        model.addAttribute("content", "showPatientsFragment");

        return this.patientApiService.getPatients()
                .doOnSuccess(patients -> {
                    model.addAttribute("patients", patients);
                })
                .thenReturn("layout");
    }

    @GetMapping("/deletePatient/{id}")
    public Mono<String> deletePatient(@PathVariable("id") Long id, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Deletion failed"));
        model.addAttribute("content", "deletePatientFragment");

        return this.patientApiService.deletePatient(id)
                .doOnSuccess(apiResponse -> {
                    model.addAttribute("apiResponse", apiResponse);
                })
                .thenReturn("layout");
    }

    



}
