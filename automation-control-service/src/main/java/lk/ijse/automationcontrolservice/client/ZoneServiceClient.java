package lk.ijse.automationcontrolservice.client;

import lk.ijse.automationcontrolservice.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zone-management-service")
public interface ZoneServiceClient {
    @GetMapping("/api/zones/{id}")
    ZoneDTO getZoneDetails(@PathVariable("id") Long id);
}