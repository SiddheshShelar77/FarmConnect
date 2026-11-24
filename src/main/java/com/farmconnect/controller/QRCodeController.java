
package com.farmconnect.controller;

import com.farmconnect.entity.Animal;
import com.farmconnect.exception.ResourceNotFoundException;
import com.farmconnect.repository.AnimalRepository;
import com.farmconnect.service.QRCodeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class QRCodeController {

  private final AnimalRepository animalRepository;
  private final QRCodeService qrCodeService;

  @GetMapping(value = "/qr/{tagNumber}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> generateQRCode(
      @PathVariable String tagNumber) {

    Animal animal = animalRepository.findByTagNumber(tagNumber)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Animal not found: " + tagNumber));

    String identificationUrl = "http://localhost:8081/api/animals/identify/"
        + animal.getTagNumber();

    byte[] qrImage = qrCodeService.generateQRCode(
        identificationUrl);

    return ResponseEntity.ok()
        .contentType(MediaType.IMAGE_PNG)
        .body(qrImage);
  }
}
