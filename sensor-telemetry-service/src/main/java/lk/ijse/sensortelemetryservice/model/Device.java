package lk.ijse.sensortelemetryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "devices")
public class Device {

    @Id
    private String id;
    private String name;
    private String zoneId;
    private String createdByUserId;

    // Constructors
    public Device() {}

    public Device(String name, String zoneId, String createdByUserId) {
        this.name = name;
        this.zoneId = zoneId;
        this.createdByUserId = createdByUserId;
    }

    public Device(String name, String zoneId) {
        this.name = name;
        this.zoneId = zoneId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getZoneId() { return zoneId; }
    public void setZoneId(String zoneId) { this.zoneId = zoneId; }

    public String getCreatedByUserId() { return createdByUserId; }
    public void setCreatedByUserId(String createdByUserId) { this.createdByUserId = createdByUserId; }
}