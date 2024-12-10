package com.hms_fe.service;

import com.hms_fe.dto.ApiResponse;
import com.hms_fe.dto.AppointmentDto;
import com.hms_fe.model.Appointment;
import com.hms_fe.model.AppointmentForm;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
public class AppointmentApiService extends  ApiService {

    public AppointmentApiService(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }

    // get AppointmentForm Options
    public Mono<AppointmentForm> getAppointmentForm() {
        try {
            return webClient.get()
                    .uri("/appointment/options")
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(AppointmentForm.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // list Appointment
    public Mono<List<AppointmentDto>> getAppointments() {
        try {
            return webClient.get()
                    .uri("/appointment/") // Append endpoint to base URL
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(new ParameterizedTypeReference<List<AppointmentDto>>() {}); // You can convert to any DTO here
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // load Appointment
    public Mono<Appointment> getAppointment(Long id) {
        try {
            return webClient.get()
                    .uri("/appointment/" + id.toString())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(Appointment.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create Appointment
    public Mono<ApiResponse> createAppointment(Appointment Appointment) {
        try {
            return webClient.post()
                    .uri("/appointment")
                    .bodyValue(Appointment)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(ApiResponse.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // update Appointment
    public Mono<ApiResponse> updateAppointment(Appointment Appointment) {
        try {
            return webClient.put()
                    .uri("/appointment")
                    .bodyValue(Appointment)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(ApiResponse.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // delete Appointment
    public Mono<ApiResponse> deleteAppointment(Long id) {
        try {
            return webClient.delete()
                    .uri("/appointment/" + id.toString())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(ApiResponse.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
