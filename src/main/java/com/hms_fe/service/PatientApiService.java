package com.hms_fe.service;

import com.hms_fe.dto.ApiResponse;
import com.hms_fe.model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
public class PatientApiService extends ApiService {

    public PatientApiService(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }


    // list patient
    public Mono<List<Patient>> getPatients() {
        try {
            return webClient.get()
                    .uri("/patient/") // Append endpoint to base URL
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(new ParameterizedTypeReference<List<Patient>>() {}); // You can convert to any DTO here
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // load patient
    public Mono<Patient> getPatient(Long id) {
        try {
            return webClient.get()
                    .uri("/patient/" + id.toString())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(Patient.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create patient
    public Mono<ApiResponse> createPatient(Patient patient) {
        try {
            return webClient.post()
                    .uri("/patient")
                    .bodyValue(patient)
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

    // update patient
    public Mono<ApiResponse> updatePatient(Patient patient) {
        try {
            return webClient.put()
                    .uri("/patient")
                    .bodyValue(patient)
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

    // delete patient
    public Mono<ApiResponse> deletePatient(Long id) {
        try {
            return webClient.delete()
                    .uri("/patient/" + id.toString())
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
