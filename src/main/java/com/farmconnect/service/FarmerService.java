package com.farmconnect.service;

import com.farmconnect.entity.Farmer;
import com.farmconnect.exception.ResourceNotFoundException;
import com.farmconnect.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FarmerService {
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer create(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

    public Farmer findById(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer not found with id: " + id));
    }

    @Transactional
    public Farmer update(Long id, Farmer updated) {
        Farmer existing = findById(id);
        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setAddress(updated.getAddress());
        return farmerRepository.save(existing);
    }

    public void delete(Long id) {
        farmerRepository.delete(findById(id));
    }
}
