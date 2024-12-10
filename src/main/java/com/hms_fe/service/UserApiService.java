package com.hms_fe.service;

import com.hms_fe.dto.ApiResponse;
import com.hms_fe.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
public class UserApiService extends ApiService {

    public UserApiService(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }

    // list User
    public Mono<List<User>> getUsers() {
        try {
            return webClient.get()
                    .uri("/user") // Append endpoint to base URL
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(new ParameterizedTypeReference<List<User>>() {}); // You can convert to any DTO here
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // load User
    public Mono<User> getUser(Long id) {
        try {
            return webClient.get()
                    .uri("/user/" + id.toString())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(User.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // create User
    public Mono<User> createUser(User User) {
        try {
            return webClient.post()
                    .uri("/user")
                    .bodyValue(User)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(User.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // update User
    public Mono<User> updateUser(User User) {
        try {
            return webClient.put()
                    .uri("/user")
                    .bodyValue(User)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> Mono.error(new RuntimeException("API call failed!")))
                    .bodyToMono(User.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // delete User
    public Mono<ApiResponse> deleteUser(Long id) {
        try {
            return webClient.delete()
                    .uri("/user/" + id.toString())
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
