package lk.ijse.zonemanagementservice.controller;

import lk.ijse.zonemanagementservice.entity.Zone;
import lk.ijse.zonemanagementservice.repository.ZoneRepository;
import lk.ijse.zonemanagementservice.service.ExternalIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneRepository repository;

    @Autowired
    private ExternalIoTService iotService; // Your custom integration service

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        // 1. Ask the external API to register a device for this zone
        String externalId = iotService.registerDeviceAtHardwareLevel(zone.getName());

        // 2. Save that ID in your local Zone record
        zone.setExternalDeviceId(externalId);

        // 3. Save to your local DB (H2, MySQL, etc.)
        return ResponseEntity.ok(repository.save(zone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zoneDetails) {
        return repository.findById(id).map(zone -> {
            zone.setMinTemp(zoneDetails.getMinTemp());
            zone.setMaxTemp(zoneDetails.getMaxTemp());
            return ResponseEntity.ok(repository.save(zone));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable Long id) {
        return repository.findById(id).map(zone -> {
            repository.delete(zone);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}