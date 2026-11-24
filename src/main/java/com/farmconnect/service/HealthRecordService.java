
package com.farmconnect.service;

import com.farmconnect.entity.Animal;
import com.farmconnect.entity.HealthRecord;
import com.farmconnect.exception.ResourceNotFoundException;
import com.farmconnect.repository.AnimalRepository;
import com.farmconnect.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordService {

  private final HealthRecordRepository healthRecordRepository;
  private final AnimalRepository animalRepository;

  public HealthRecord addHealthRecord(Long animalId, HealthRecord record) {

    Animal animal = animalRepository.findById(animalId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Animal not found with id: " + animalId));

    record.setAnimal(animal);

    return healthRecordRepository.save(record);
  }

  public List<HealthRecord> getHealthRecordsByAnimal(Long animalId) {

    if (!animalRepository.existsById(animalId)) {
      throw new ResourceNotFoundException(
          "Animal not found with id: " + animalId);
    }

    return healthRecordRepository.findByAnimalId(animalId);
  }
}
