package lk.ijse.apigateway.service;

import lk.ijse.apigateway.dto.AuthResponse;
import lk.ijse.apigateway.dto.LoginRequest;
import lk.ijse.apigateway.dto.RefreshRequest;
import lk.ijse.apigateway.dto.RegisterRequest;
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
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }

    public Mono<AuthResponse> register(RegisterRequest request){

        return webClient.post()
                .uri("/auth/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }

    public Mono<AuthResponse> refreshToken(String refreshToken){

        RefreshRequest req = new RefreshRequest();
        req.setRefreshToken(refreshToken);

        return webClient.post()
                .uri("/auth/refresh")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(AuthResponse.class);
    }
}