package com.plaza.hotel.controller;

import com.plaza.hotel.dto.ApiResponseDTO;
import com.plaza.hotel.dto.GuestDTO;
import com.plaza.hotel.dto.GuestResponseDTO;
import com.plaza.hotel.entity.Guest;
import com.plaza.hotel.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/guest")
public class GuestController {

    private final GuestService service;

    public GuestController(GuestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Guest> saveGuest(@RequestBody GuestDTO dto) {
        Guest guest = service.createGuest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guest);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponseDTO> updateGuest(@RequestBody GuestDTO dto, @PathVariable Long id) {
        service.updateGuest(dto, id);
        return ResponseEntity.ok(new ApiResponseDTO("Guest updated successfully"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponseDTO> deleteGuest(@PathVariable Long id) {
        service.deleteGuest(id);
        return ResponseEntity.ok(new ApiResponseDTO("Guest deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String document,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Boolean inHotel) {

        return ResponseEntity.ok(
                service.search(name, document, phoneNumber, inHotel)
        );
    }
}
