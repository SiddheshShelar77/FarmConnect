
package com.farmconnect.controller;

import com.farmconnect.dto.AnimalProfileDTO;
import com.farmconnect.entity.Animal;
import com.farmconnect.service.AnimalService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Create animal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal create(
            @RequestParam Long farmerId,
            @Valid @RequestBody Animal animal) {

        return animalService.create(farmerId, animal);
    }

    // Get all animals or animals by farmer
    @GetMapping
    public List<Animal> findAll(
            @RequestParam(required = false) Long farmerId) {

        return farmerId == null
                ? animalService.findAll()
                : animalService.findByFarmer(farmerId);
    }

    // Get animal by ID
    @GetMapping("/{id}")
    public Animal findById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    // Update animal
    @PutMapping("/{id}")
    public Animal update(
            @PathVariable Long id,
            @Valid @RequestBody Animal animal) {

        return animalService.update(id, animal);
    }

    // Delete animal
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        animalService.delete(id);
    }

    // Identify animal by QR tag
    @GetMapping("/identify/{tagNumber}")
    public Animal identifyAnimal(@PathVariable String tagNumber) {
        return animalService.identifyByTagNumber(tagNumber);
    }

    // Get public animal profile
    @GetMapping("/public/{tagNumber}")
    public AnimalProfileDTO getPublicProfile(
            @PathVariable String tagNumber) {

        return animalService.getPublicProfile(tagNumber);
    }
}
