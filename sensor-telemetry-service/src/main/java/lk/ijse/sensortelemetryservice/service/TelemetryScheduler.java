package lk.ijse.sensortelemetryservice.service;

import lk.ijse.sensortelemetryservice.client.AutomationClient;
import lk.ijse.sensortelemetryservice.dto.TelemetryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TelemetryScheduler {

    @Autowired
    private ExternalIoTService iotService;

    @Autowired
    private AutomationClient automationClient; // Feign Client to talk to Port 8083

    // Runs every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void fetchAndPushData() {
        // 1. Get all active device IDs (In a real app, you'd fetch these from the Zone Service)
        // For the exam, you can start with a stored list or a single test ID
        String deviceId = "your-stored-device-id";

        // 2. Fetch live data from External API
        // GET /devices/telemetry/{deviceId}
        TelemetryData data = iotService.getLatestTelemetry(deviceId);

        if (data != null) {
            // 3. PUSH to Automation Service
            automationClient.processTelemetry(data);
            System.out.println("Pushed data for Zone: " + data.getZoneId());
        }
    }
}