package com.farmconnect.repository;

import com.farmconnect.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository
    extends JpaRepository<Vaccination, Long> {

  List<Vaccination> findByAnimalId(Long animalId);

  List<Vaccination> findByNextDueDateBetween(
      LocalDate startDate,
      LocalDate endDate);
}