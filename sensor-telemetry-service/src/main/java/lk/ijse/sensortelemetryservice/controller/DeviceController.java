package lk.ijse.sensortelemetryservice.controller;

import lk.ijse.sensortelemetryservice.model.Device;
import lk.ijse.sensortelemetryservice.service.DeviceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Create device
    @PostMapping
    public Mono<Device> createDevice(@RequestBody Device device,
                                     @RequestHeader("X-User-Id") String userId) {
        device.setCreatedByUserId(userId);
        return deviceService.createDevice(device);
    }

    // Get all devices for the user
    @GetMapping
    public Flux<Device> getDevices(@RequestHeader("X-User-Id") String userId) {
        return deviceService.getDevicesByUserId(userId);
    }
}