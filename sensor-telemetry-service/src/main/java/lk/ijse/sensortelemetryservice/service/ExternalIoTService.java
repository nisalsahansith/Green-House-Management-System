package lk.ijse.sensortelemetryservice.service;

import lk.ijse.sensortelemetryservice.dto.TelemetryData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class ExternalIoTService {

    private final WebClient webClient;
    private String cachedToken;

    public ExternalIoTService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://104.211.95.241:8080/api").build();
    }

    private void authenticate() {
        Map<String, String> credentials = Map.of("username", "your_username", "password", "123456");

        Map response = webClient.post()
                .uri("/auth/login")
                .bodyValue(credentials)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response != null && response.containsKey("accessToken")) {
            this.cachedToken = (String) response.get("accessToken");
        }
    }

    public TelemetryData getLatestTelemetry(String deviceId) {
        if (this.cachedToken == null) {
            authenticate();
        }

        try {
            return webClient.get()
                    .uri("/devices/telemetry/" + deviceId)
                    .header("Authorization", "Bearer " + cachedToken)
                    .retrieve()
                    .bodyToMono(TelemetryData.class)
                    .block();
        } catch (Exception e) {
            System.out.println("Error fetching telemetry: " + e.getMessage());
            return null;
        }
    }
}