package lk.ijse.apigateway.service;

import lk.ijse.apigateway.dto.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class IotAuthService {

    private final WebClient webClient;

    public IotAuthService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<AuthResponse> login(LoginRequest request){

        return webClient.post()
                .uri("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }

    public Mono<AuthResponse> register(RegisterRequest request){

        return webClient.post()
                .uri("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }

    public Mono<AuthResponse> refreshToken(String refreshToken){

        RefreshRequest req = new RefreshRequest();
        req.setRefreshToken(refreshToken);

        return webClient.post()
                .uri("/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }

    public Mono<UserInfo> validateToken(String token) {
        return webClient.get()
                .uri("/auth/validate") // your auth service validation endpoint
                .header(HttpHeaders.AUTHORIZATION, token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserInfo.class);
    }
}