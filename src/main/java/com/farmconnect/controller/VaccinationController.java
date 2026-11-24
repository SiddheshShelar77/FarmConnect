
package com.farmconnect.controller;

import com.farmconnect.entity.Vaccination;
import com.farmconnect.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

  private final VaccinationService vaccinationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Vaccination addVaccination(
      @RequestParam Long animalId,
      @RequestBody Vaccination vaccination) {

    return vaccinationService.addVaccination(animalId, vaccination);
  }

  @GetMapping("/animal/{animalId}")
  public List<Vaccination> getVaccinations(
      @PathVariable Long animalId) {

    return vaccinationService.getVaccinationsByAnimal(animalId);
  }

  @GetMapping("/upcoming")
  public List<Vaccination> getUpcomingVaccinations() {

    return vaccinationService.getUpcomingVaccinations();
  }
}
