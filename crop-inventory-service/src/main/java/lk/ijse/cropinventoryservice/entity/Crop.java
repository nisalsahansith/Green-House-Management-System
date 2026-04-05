package lk.ijse.cropinventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "crops")
@Getter
@Setter
public class Crop {
    @Id
    private String cropId; // Manually assigned like CROP-001
    private String commonName;
    private String scientificName;
    private String category; // e.g., Vegetable, Fruit, Flower
    private String status;   // SEEDLING, GROWING, HARVESTED
    private Long zoneId;    // The ID from the Zone Management Service

    // Getters and Setters
}