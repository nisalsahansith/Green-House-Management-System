package lk.ijse.apigateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class TokenVerificationService {

    private final WebClient webClient;

    public TokenVerificationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Map> verifyToken(String token) {
        return webClient.post()
                .uri("/introspect") // depends on the IdP endpoint
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("token=" + token)
                .retrieve()
                .bodyToMono(Map.class); // JSON response from introspection endpoint
    }
}