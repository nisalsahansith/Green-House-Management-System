package lk.ijse.sensortelemetryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "lk.ijse.sensortelemetryservice.repository")
public class SensorTelemetryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorTelemetryServiceApplication.class, args);
	}

}
