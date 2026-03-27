//package lk.ijse.sensortelemetryservice.service;
//
//import lk.ijse.sensortelemetryservice.model.Device;
//import lk.ijse.sensortelemetryservice.repository.DeviceRepository;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//public class DeviceService {
//
//    private final DeviceRepository repository;
//
//    public DeviceService(DeviceRepository repository) {
//        this.repository = repository;
//    }
//
//    public Mono<Device> createDevice(Device device) {
//        return repository.save(device);
//    }
//
//    public Flux<Device> getDevicesByUserId(String userId) {
//        return repository.findAllByCreatedByUserId(userId);
//    }
//}userId