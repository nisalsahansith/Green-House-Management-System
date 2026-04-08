package lk.ijse.sensortelemetryservice.client;

import lk.ijse.sensortelemetryservice.dto.TelemetryData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "automation-service") // Eureka service name
public interface AutomationClient {
    @PostMapping("/api/automation/process")
    void processTelemetry(@RequestBody TelemetryData data);
}