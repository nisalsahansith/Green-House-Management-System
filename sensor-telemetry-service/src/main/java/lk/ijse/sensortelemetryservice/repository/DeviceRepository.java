package lk.ijse.sensortelemetryservice.repository;

import lk.ijse.sensortelemetryservice.model.Device;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DeviceRepository extends ReactiveMongoRepository<Device, String> {
    Flux<Device> findAllByCreatedByUserId(String userId);
}