
package com.farmconnect.dto;

import java.time.LocalDate;
import java.util.List;

public record AnimalProfileDTO(

    String tagNumber,
    String name,
    String species,
    String breed,
    String gender,
    LocalDate dateOfBirth,

    List<VaccinationInfoDTO> vaccinations,

    List<HealthRecordInfoDTO> healthRecords

) {

  public record VaccinationInfoDTO(
      String vaccineName,
      LocalDate dateGiven,
      LocalDate nextDueDate,
      String notes) {
  }

  public record HealthRecordInfoDTO(
      String diagnosis,
      String treatment,
      String veterinarianName,
      LocalDate recordDate) {
  }
}
