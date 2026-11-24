package com.farmconnect.controller;

import com.farmconnect.entity.Farmer;
import com.farmconnect.service.FarmerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Farmer create(@Valid @RequestBody Farmer farmer) {
        return farmerService.create(farmer);
    }

    @GetMapping
    public List<Farmer> findAll() {
        return farmerService.findAll();
    }

    @GetMapping("/{id}")
    public Farmer findById(@PathVariable Long id) {
        return farmerService.findById(id);
    }

    @PutMapping("/{id}")
    public Farmer update(@PathVariable Long id, @Valid @RequestBody Farmer farmer) {
        return farmerService.update(id, farmer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        farmerService.delete(id);
    }
}
