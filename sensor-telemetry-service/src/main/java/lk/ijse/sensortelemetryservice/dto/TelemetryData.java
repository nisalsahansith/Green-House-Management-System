package lk.ijse.sensortelemetryservice.dto;

public class TelemetryData {
    private String deviceId;
    private String zoneId;
    private Value value;
    private String capturedAt;

    public static class Value {
        private Double temperature;
        private Double humidity;

        public Double getTemperature() { return temperature; }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        public Double getHumidity() {
            return humidity;
        }
    }

    // Getters & Setters
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getZoneId() { return zoneId; }
    public void setZoneId(String zoneId) { this.zoneId = zoneId; }

    public Value getValue() { return value; }
    public void setValue(Value value) { this.value = value; }

    public String getCapturedAt() { return capturedAt; }
    public void setCapturedAt(String capturedAt) { this.capturedAt = capturedAt;}
}