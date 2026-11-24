
package com.farmconnect.service;

import com.farmconnect.entity.Animal;
import com.farmconnect.entity.Vaccination;
import com.farmconnect.exception.ResourceNotFoundException;
import com.farmconnect.repository.AnimalRepository;
import com.farmconnect.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationService {

  private final VaccinationRepository vaccinationRepository;
  private final AnimalRepository animalRepository;

  public Vaccination addVaccination(Long animalId, Vaccination vaccination) {

    Animal animal = animalRepository.findById(animalId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Animal not found with id: " + animalId));

    vaccination.setAnimal(animal);

    return vaccinationRepository.save(vaccination);
  }

  public List<Vaccination> getVaccinationsByAnimal(Long animalId) {

    if (!animalRepository.existsById(animalId)) {
      throw new ResourceNotFoundException(
          "Animal not found with id: " + animalId);
    }

    return vaccinationRepository.findByAnimalId(animalId);
  }

  public List<Vaccination> getUpcomingVaccinations() {

    LocalDate today = LocalDate.now();
    LocalDate nextMonth = today.plusDays(30);

    return vaccinationRepository.findByNextDueDateBetween(
        today, nextMonth);
  }
}
