
package com.farmconnect.controller;

import com.farmconnect.entity.HealthRecord;
import com.farmconnect.service.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-records")
@RequiredArgsConstructor
public class HealthRecordController {

  private final HealthRecordService healthRecordService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public HealthRecord addHealthRecord(
      @RequestParam Long animalId,
      @RequestBody HealthRecord record) {

    return healthRecordService.addHealthRecord(animalId, record);
  }

  @GetMapping("/animal/{animalId}")
  public List<HealthRecord> getHealthRecords(
      @PathVariable Long animalId) {

    System.out.println("Fetching health records for animal: " + animalId);

    return healthRecordService.getHealthRecordsByAnimal(animalId);
  }
}
