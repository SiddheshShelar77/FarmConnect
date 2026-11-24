
package com.farmconnect.service;

import com.farmconnect.dto.AnimalProfileDTO;
import com.farmconnect.entity.Animal;
import com.farmconnect.entity.Farmer;
import com.farmconnect.exception.ResourceNotFoundException;
import com.farmconnect.repository.AnimalRepository;
import com.farmconnect.repository.FarmerRepository;
import com.farmconnect.repository.VaccinationRepository;
import com.farmconnect.repository.HealthRecordRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final FarmerRepository farmerRepository;
    private final VaccinationRepository vaccinationRepository;
    private final HealthRecordRepository healthRecordRepository;

    public AnimalService(
            AnimalRepository animalRepository,
            FarmerRepository farmerRepository,
            VaccinationRepository vaccinationRepository,
            HealthRecordRepository healthRecordRepository) {

        this.animalRepository = animalRepository;
        this.farmerRepository = farmerRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    // Get complete public animal profile
    public AnimalProfileDTO getPublicProfile(String tagNumber) {

        // Find animal
        Animal animal = identifyByTagNumber(tagNumber);

        // Fetch vaccination records
        List<AnimalProfileDTO.VaccinationInfoDTO> vaccinationInfo = vaccinationRepository.findByAnimalId(animal.getId())
                .stream()
                .map(v -> new AnimalProfileDTO.VaccinationInfoDTO(
                        v.getVaccineName(),
                        v.getDateGiven(),
                        v.getNextDueDate(),
                        v.getNotes()))
                .toList();

        // Fetch health records
        List<AnimalProfileDTO.HealthRecordInfoDTO> healthRecordInfo = healthRecordRepository
                .findByAnimalId(animal.getId())
                .stream()
                .map(h -> new AnimalProfileDTO.HealthRecordInfoDTO(
                        h.getDiagnosis(),
                        h.getTreatment(),
                        h.getVeterinarianName(),
                        h.getRecordDate()))
                .toList();

        // Return complete profile
        return new AnimalProfileDTO(
                animal.getTagNumber(),
                animal.getName(),
                animal.getSpecies().name(),
                animal.getBreed(),
                animal.getGender() != null
                        ? animal.getGender().name()
                        : null,
                animal.getDateOfBirth(),
                vaccinationInfo,
                healthRecordInfo);
    }

    // Create animal
    public Animal create(Long farmerId, Animal animal) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Farmer not found with id: " + farmerId));

        animal.setFarmer(farmer);

        return animalRepository.save(animal);
    }

    // Get all animals
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    // Get animal by ID
    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Animal not found with id: " + id));
    }

    // Get animals belonging to a farmer
    public List<Animal> findByFarmer(Long farmerId) {
        return animalRepository.findByFarmerId(farmerId);
    }

    // Update animal
    public Animal update(Long id, Animal updatedAnimal) {

        Animal animal = findById(id);

        animal.setTagNumber(updatedAnimal.getTagNumber());
        animal.setName(updatedAnimal.getName());
        animal.setSpecies(updatedAnimal.getSpecies());
        animal.setBreed(updatedAnimal.getBreed());
        animal.setGender(updatedAnimal.getGender());
        animal.setDateOfBirth(updatedAnimal.getDateOfBirth());

        return animalRepository.save(animal);
    }

    // Delete animal
    public void delete(Long id) {
        Animal animal = findById(id);
        animalRepository.delete(animal);
    }

    // Identify animal using tag number
    public Animal identifyByTagNumber(String tagNumber) {

        return animalRepository.findByTagNumber(tagNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Animal not found with tag number: " + tagNumber));
    }
}
