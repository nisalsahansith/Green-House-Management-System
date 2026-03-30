package lk.ijse.automationcontrolservice.controller;

import lk.ijse.automationcontrolservice.client.ZoneServiceClient;
import lk.ijse.automationcontrolservice.dto.ZoneDTO;
import lk.ijse.automationcontrolservice.entity.AutomationLog;
import lk.ijse.automationcontrolservice.entity.TelemetryData;
import lk.ijse.automationcontrolservice.repository.AutomationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    @Autowired
    private ZoneServiceClient zoneClient;

    @Autowired
    private AutomationLogRepository logRepository; // Repository for saving logs

    @PostMapping("/process")
    public void processData(@RequestBody TelemetryData data) {
        // 1. Get thresholds from Zone Service
        ZoneDTO zone = zoneClient.getZoneDetails(Long.parseLong(data.getZoneId()));

        Double currentTemp = data.getValue().getTemperature();
        String action = "STABLE";

        // 2. Rule Engine Logic
        if (currentTemp > zone.getMaxTemp()) {
            action = "TURN_FAN_ON";
        } else if (currentTemp < zone.getMinTemp()) {
            action = "TURN_HEATER_ON";
        }

        // 3. Save to Database
        AutomationLog log = new AutomationLog(data.getZoneId(), currentTemp, action, LocalDateTime.now());
        logRepository.save(log);

        System.out.println("Zone: " + data.getZoneId() + " | Temp: " + currentTemp + " | Action: " + action);
    }
}