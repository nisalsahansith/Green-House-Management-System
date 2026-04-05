package lk.ijse.cropinventoryservice.controller;

import lk.ijse.cropinventoryservice.entity.Crop;
import lk.ijse.cropinventoryservice.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropRepository repository;

    @PostMapping
    public ResponseEntity<Crop> saveCrop(@RequestBody Crop crop) {
        return new ResponseEntity<>(repository.save(crop), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCrop(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Crop> updateStatus(@PathVariable String id, @RequestBody String status) {
        return repository.findById(id).map(crop -> {
            crop.setStatus(status);
            return ResponseEntity.ok(repository.save(crop));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Crop> getAllCrops() {
        return repository.findAll();
    }
}