package lk.ijse.zonemanagementservice.service;

import lk.ijse.zonemanagementservice.dto.ZoneRequest;
import lk.ijse.zonemanagementservice.dto.ZoneResponse;
import lk.ijse.zonemanagementservice.entity.Zone;
import lk.ijse.zonemanagementservice.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    private final ZoneRepository repository;

    public ZoneService(ZoneRepository repository) {
        this.repository = repository;
    }

    public ZoneResponse createZone(ZoneRequest request) {
        Zone zone = new Zone();
        zone.setName(request.getName());
        zone.setDescription(request.getDescription());

        Zone saved = repository.save(zone);

        ZoneResponse response = new ZoneResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());

        return response;
    }

    public List<ZoneResponse> getAllZones() {
        return repository.findAll().stream().map(zone -> {
            ZoneResponse resp = new ZoneResponse();
            resp.setId(zone.getId());
            resp.setName(zone.getName());
            resp.setDescription(zone.getDescription());
            return resp;
        }).collect(Collectors.toList());
    }

    public ZoneResponse getZoneById(Long id) {
        Zone zone = repository.findById(id).orElseThrow(() -> new RuntimeException("Zone not found"));
        ZoneResponse resp = new ZoneResponse();
        resp.setId(zone.getId());
        resp.setName(zone.getName());
        resp.setDescription(zone.getDescription());
        return resp;
    }

    public ZoneResponse updateZone(Long id, ZoneRequest request) {
        Zone zone = repository.findById(id).orElseThrow(() -> new RuntimeException("Zone not found"));
        zone.setName(request.getName());
        zone.setDescription(request.getDescription());
        Zone saved = repository.save(zone);

        ZoneResponse resp = new ZoneResponse();
        resp.setId(saved.getId());
        resp.setName(saved.getName());
        resp.setDescription(saved.getDescription());
        return resp;
    }

    public void deleteZone(Long id) {
        repository.deleteById(id);
    }
}