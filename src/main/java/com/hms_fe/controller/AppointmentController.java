package com.hms_fe.controller;

import com.hms_fe.model.Appointment;
import com.hms_fe.model.AppointmentForm;
import com.hms_fe.service.AppointmentApiService;
import org.springframework.stereotype.Controller;
import com.hms_fe.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class AppointmentController {

    @Autowired
    private final AppointmentApiService appointmentApiService;
    
    public AppointmentController(AppointmentApiService appointmentApiService) {
        this.appointmentApiService = appointmentApiService;
    }

    @GetMapping("/createAppointment")
    public Mono<String> createAppointment(Model model) {
        model.addAttribute("showStatus", false);
        model.addAttribute("appointmentMdl", new Appointment());
        model.addAttribute("appointmentForm", new AppointmentForm());
        model.addAttribute("content", "createAppointmentFragment");

        return this.appointmentApiService.getAppointmentForm()
                .doOnSuccess(appointmentForm -> {
                    model.addAttribute("appointmentForm", appointmentForm);
                })

                .thenReturn("layout");
    }

    @GetMapping("/editAppointment/{id}")
    public Mono<String> editAppointment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("content", "createAppointmentFragment");
        return this.appointmentApiService.getAppointment(id)
                .doOnSuccess(appointment -> {
                    model.addAttribute("appointmentMdl", appointment);
                })
                .thenReturn("layout");
    }

    @PostMapping("/saveAppointment")
    public Mono<String> saveAppointment(@ModelAttribute Appointment appointmentMdl, Model model) {

        model.addAttribute("showStatus", true);
        model.addAttribute("appointmentMdl", appointmentMdl);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Update failed"));
        model.addAttribute("content", "createAppointmentFragment");
        Mono<ApiResponse> apiResponseMono;

        if( appointmentMdl.getId() != null ) {
            apiResponseMono = this.appointmentApiService.updateAppointment(appointmentMdl);
        }
        else {
            apiResponseMono = this.appointmentApiService.createAppointment(appointmentMdl);
        }

        return apiResponseMono.doOnSuccess(apiResponse -> {
                    model.addAttribute("apiResponse", apiResponse);
                })
                .thenReturn("layout");
    }

    @GetMapping(value={"/showAppointments"})
    public Mono<String> showAppointments(Model model) {

        model.addAttribute("appointments", null);
        model.addAttribute("count", 0);
        model.addAttribute("content", "showAppointmentsFragment");

        return this.appointmentApiService.getAppointments()
                .doOnSuccess(appointments -> {
                    model.addAttribute("appointments", appointments);
                    model.addAttribute("count", appointments.size());
                })
                .thenReturn("layout");
    }

    @GetMapping("/deleteAppointment/{id}")
    public Mono<String> deleteAppointment(@PathVariable("id") Long id, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("apiResponse", new ApiResponse("fail", "Deletion failed"));
        model.addAttribute("content", "deleteAppointmentFragment");

        return this.appointmentApiService.deleteAppointment(id)
                .doOnSuccess(apiResponse -> {
                    model.addAttribute("apiResponse", apiResponse);
                })
                .thenReturn("layout");
    } 
    
}
