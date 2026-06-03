package com.plaza.hotel.controller;

import com.plaza.hotel.dto.AccommodationDTO;
import com.plaza.hotel.dto.CheckoutDTO;
import com.plaza.hotel.dto.CheckoutResponseDTO;
import com.plaza.hotel.entity.Accommodation;
import com.plaza.hotel.service.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accommodation")
public class AccommodationController {

    private final AccommodationService service;

    public AccommodationController(AccommodationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Accommodation> saveAccommodation(@RequestBody AccommodationDTO dto) {
        Accommodation accommodation = service.createAccommodation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accommodation);
    }

    @PutMapping(value = "/checkout/{id}")
    public ResponseEntity<CheckoutResponseDTO> checkout(@RequestBody CheckoutDTO dto, @PathVariable Long id) {
        CheckoutResponseDTO response = service.checkout(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
