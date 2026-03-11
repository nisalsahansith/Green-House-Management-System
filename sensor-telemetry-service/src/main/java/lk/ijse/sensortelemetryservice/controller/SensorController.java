package lk.ijse.sensortelemetryservice.controller;

import lk.ijse.sensortelemetryservice.model.Sensor;
import lk.ijse.sensortelemetryservice.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

//    @PostMapping
//    public Sensor createSensor(@RequestBody Sensor sensor){
//        return service.createSensor(sensor);
//    }
//
//    @GetMapping
//    public List<Sensor> getSensors(){
//        return service.getAllSensors();
//    }
//
//    @GetMapping("/device/{deviceId}")
//    public List<Sensor> getSensorsByDevice(@PathVariable String deviceId){
//        return service.getSensorsByDevice(deviceId);
//    }
}