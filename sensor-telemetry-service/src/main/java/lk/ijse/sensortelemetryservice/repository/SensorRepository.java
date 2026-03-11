package lk.ijse.sensortelemetryservice.repository;

import lk.ijse.sensortelemetryservice.model.Sensor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SensorRepository extends ReactiveMongoRepository<Sensor, String> {
    Flux<Sensor> findByDeviceId(String deviceId);
}