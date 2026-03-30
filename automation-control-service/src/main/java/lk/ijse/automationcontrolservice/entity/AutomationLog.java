package lk.ijse.automationcontrolservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "automation_logs")
public class AutomationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zoneId;
    private Double currentTemp;
    private String action; // TURN_FAN_ON, TURN_HEATER_ON, STABLE
    private LocalDateTime timestamp;

    // Constructors, Getters and Setters
    public AutomationLog() {}
    public AutomationLog(String zoneId, Double currentTemp, String action, LocalDateTime timestamp) {
        this.zoneId = zoneId;
        this.currentTemp = currentTemp;
        this.action = action;
        this.timestamp = timestamp;
    }
}