package com.plaza.hotel.service;

import com.plaza.hotel.dto.GuestDTO;
import com.plaza.hotel.dto.GuestResponseDTO;
import com.plaza.hotel.entity.Guest;
import com.plaza.hotel.exception.NotFoundException;
import com.plaza.hotel.mapper.GuestMapper;
import com.plaza.hotel.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private GuestRepository repository;
    private final GuestMapper mapper;

    public GuestService(GuestMapper mapper, GuestRepository hotelRepository) {
        this.mapper = mapper;
        this.repository = hotelRepository;
    }

    public Guest findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guest not found with id: " + id));
    }

    public Guest createGuest(GuestDTO dto) {
        Guest guest = mapper.map(dto, new Guest());
        return repository.save(guest);
    }

    public void updateGuest(GuestDTO dto, Long id) {
        Guest guest = findById(id);
        guest = mapper.map(dto, guest);
        repository.save(guest);
    }

    public void deleteGuest(Long id) {
        repository.delete(findById(id));
    }

    public List<GuestResponseDTO> search(String name,
                                         String document,
                                         String phoneNumber,
                                         Boolean inHotel) {
        return repository.search(name, document, phoneNumber, inHotel);
    }
}
