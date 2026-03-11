package lk.ijse.sensortelemetryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensors")
public class Sensor {

    @Id
    private String id;

    private String sensorType;
    private String deviceId;
    private String unit;
    private String status;

    public Sensor() {
    }

    public Sensor(String id, String sensorType, String deviceId, String unit, String status) {
        this.id = id;
        this.sensorType = sensorType;
        this.deviceId = deviceId;
        this.unit = unit;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}