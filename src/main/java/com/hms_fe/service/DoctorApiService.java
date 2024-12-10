package com.hms_fe.service;

import com.hms_fe.dto.ApiResponse;
import com.hms_fe.model.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
public class DoctorApiService extends ApiService {

    public DoctorApiService(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }

    // list doctors
    public Mono<List<Doctor>> getDoctors() {
        try {
            return webClient.get()
                    .uri("/doctor/") // Append endpoint to base URL
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(new ParameterizedTypeReference<List<Doctor>>() {}); // You can convert to any DTO here
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // load doctor
    public Mono<Doctor> getDoctor(Long id) {
        try {
            return webClient.get()
                    .uri("/doctor/" + id.toString())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(Doctor.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create doctor
    public Mono<ApiResponse> createDoctor(Doctor doctor) {
        try {
            return webClient.post()
                    .uri("/doctor")
                    .bodyValue(doctor)
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

    // update doctor
    public Mono<ApiResponse> updateDoctor(Doctor doctor) {
        try {
            return webClient.put()
                    .uri("/doctor")
                    .bodyValue(doctor)
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

    // delete doctor
    public Mono<ApiResponse> deleteDoctor(Long id) {
        try {
            return webClient.delete()
                    .uri("/doctor/" + id.toString())
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
