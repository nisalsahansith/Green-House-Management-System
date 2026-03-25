package lk.ijse.zonemanagementservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ExternalIoTService {

    private final WebClient webClient;
    private String cachedToken; // In a real app, use Redis or a secure cache

    public ExternalIoTService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://104.211.95.241:8080/api").build();
    }

    // 1. Get the JWT Token
    public String login() {
        Map<String, String> credentials = Map.of("username", "your_username", "password", "123456");

        Map<String, Object> response = webClient.post()
                .uri("/auth/login")
                .bodyValue(credentials)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // Use .block() for synchronous flow in a non-reactive service

        this.cachedToken = (String) response.get("accessToken");
        return this.cachedToken;
    }

    // 2. Register a new device (Used when a Zone is created)
    public String registerDeviceAtHardwareLevel(String zoneName) {
        if (cachedToken == null) login();

        Map<String, String> deviceRequest = Map.of(
                "name", zoneName + "-Sensor",
                "zoneId", zoneName
        );

        Map<String, Object> response = webClient.post()
                .uri("/devices")
                .header("Authorization", "Bearer " + cachedToken)
                .bodyValue(deviceRequest)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // This returns the 'deviceId' provided by the external system
        return (String) response.get("deviceId");
    }
}