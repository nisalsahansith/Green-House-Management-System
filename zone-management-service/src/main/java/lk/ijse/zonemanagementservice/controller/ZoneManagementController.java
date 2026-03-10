package lk.ijse.zonemanagementservice.controller;

import lk.ijse.zonemanagementservice.dto.ZoneRequest;
import lk.ijse.zonemanagementservice.dto.ZoneResponse;
import lk.ijse.zonemanagementservice.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneManagementController {

    private final ZoneService service;

    public ZoneManagementController(ZoneService service) {
        this.service = service;
    }

    @PostMapping
    public ZoneResponse createZone(@RequestBody ZoneRequest request) {
        return service.createZone(request);
    }

    @GetMapping
    public List<ZoneResponse> getAllZones() {
        return service.getAllZones();
    }

    @GetMapping("/{id}")
    public ZoneResponse getZoneById(@PathVariable Long id) {
        return service.getZoneById(id);
    }

    @PutMapping("/{id}")
    public ZoneResponse updateZone(@PathVariable Long id, @RequestBody ZoneRequest request) {
        return service.updateZone(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteZone(@PathVariable Long id) {
        service.deleteZone(id);
    }
}