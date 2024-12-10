package com.hms_fe.controller;

import com.hms_fe.dto.ApiResponse;
import com.hms_fe.model.Doctor;
import com.hms_fe.service.DoctorApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class DoctorController {

    @Autowired
    private final DoctorApiService apiService;

    public DoctorController(DoctorApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/createDoctor")
    public String createDoctor(Model model) {
        model.addAttribute("showStatus", false);
        model.addAttribute("doctorMdl", new Doctor());
        model.addAttribute("title", "Create Doctor");
        model.addAttribute("content", "createDoctorFragment");
        return "layout";
    }

    @GetMapping("/editDoctor/{id}")
    public Mono<String> editDoctor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("showStatus", false);
        model.addAttribute("doctorMdl", new Doctor());
        model.addAttribute("title", "Edit Doctor");
        model.addAttribute("content", "createDoctorFragment");

        return this.apiService.getDoctor(id)
                .doOnSuccess(doctor -> {
                    model.addAttribute("doctorMdl", doctor);
                })
                .thenReturn("layout");
    }

    @PostMapping("/saveDoctor")
    public Mono<String> saveDoctor(@ModelAttribute Doctor doctorMdl, Model model) {

        model.addAttribute("showStatus", true);
        model.addAttribute("doctorMdl", doctorMdl);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Update failed"));
        model.addAttribute("content", "createDoctorFragment");

        Mono<ApiResponse> apiResponseMono;

        if( doctorMdl.getId() != null ) {
            apiResponseMono = this.apiService.updateDoctor(doctorMdl);
        }
        else {
            apiResponseMono = this.apiService.createDoctor(doctorMdl);
        }

        return apiResponseMono.doOnSuccess(apiResponse -> {
            model.addAttribute("apiResponse", apiResponse);
        })
        .thenReturn("layout");
    }

    @GetMapping(value={"/showDoctors"})
    public Mono<String> showDoctors(Model model) {

        model.addAttribute("doctors", null);
        model.addAttribute("content", "showDoctorsFragment");

        return this.apiService.getDoctors()
                .doOnSuccess(doctors -> {
                    model.addAttribute("doctors", doctors);
                })
                .thenReturn("layout");
    }

    @GetMapping("/deleteDoctor/{id}")
    public Mono<String> deleteDoctor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Deletion failed"));
        model.addAttribute("content", "deleteDoctorFragment");

        return this.apiService.deleteDoctor(id)
                .doOnSuccess(apiResponse -> {
                    model.addAttribute("apiResponse", apiResponse);
                })
                .thenReturn("layout");
    }

}
