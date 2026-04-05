package lk.ijse.cropinventoryservice.repository;

import lk.ijse.cropinventoryservice.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {

    // Custom query to find crops by their status (e.g., all "HARVESTED" crops)
    List<Crop> findByStatus(String status);

    // Custom query to find crops assigned to a specific Zone
    List<Crop> findByZoneId(Long zoneId);
}