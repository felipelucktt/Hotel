package com.plaza.hotel.service;

import com.plaza.hotel.dto.AccommodationDTO;
import com.plaza.hotel.dto.CheckoutDTO;
import com.plaza.hotel.dto.CheckoutResponseDTO;
import com.plaza.hotel.entity.Accommodation;
import com.plaza.hotel.exception.BadRequestException;
import com.plaza.hotel.exception.NotFoundException;
import com.plaza.hotel.mapper.AccommodationMapper;
import com.plaza.hotel.repository.AccommodationRepository;
import com.plaza.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository repository;
    @Autowired
    private GuestRepository guestRepository;
    private final AccommodationMapper mapper;

    private static final BigDecimal WEEKDAY_VALUE = BigDecimal.valueOf(120);
    private static final BigDecimal WEEKEND_VALUE = BigDecimal.valueOf(150);

    private static final BigDecimal WEEKDAY_PARKING = BigDecimal.valueOf(15);
    private static final BigDecimal WEEKEND_PARKING = BigDecimal.valueOf(20);

    private static final LocalTime CHECKOUT_LIMIT = LocalTime.of(16, 30);

    public AccommodationService(AccommodationMapper mapper) {
        this.mapper = mapper;
    }

    public Accommodation createAccommodation(AccommodationDTO dto) {
        if(dto.getGuest() != null) {
            guestRepository.findById(dto.getGuest().getId())
                    .orElseThrow(() -> new NotFoundException("Guest not found with id: " + dto.getGuest().getId()));
        }

        if(dto.getCheckInDate() == null) {
            throw new BadRequestException("Check-in date must be informed");
        }

        Accommodation accommodation = mapper.map(dto, new Accommodation());
        return repository.save(accommodation);
    }

    public CheckoutResponseDTO checkout(CheckoutDTO dto, Long id) {
        if(dto.getCheckOutDate() == null) {
            throw new BadRequestException("The checkOutDate cannot be null");
        }

        Accommodation accommodation = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Accommodation not found with id: " + id));

        if(accommodation.getCheckOutDate() != null) {
            throw new BadRequestException("The guest has already checked out");
        }

        if (!dto.getCheckOutDate().isAfter(accommodation.getCheckInDate())) {
            throw new BadRequestException("Check-out must be after check-in");
        }

        accommodation.setCheckOutDate(dto.getCheckOutDate());
        BigDecimal totalValue = calculateTotalValue(accommodation);
        accommodation.setTotalValue(totalValue);
        repository.save(accommodation);

        CheckoutResponseDTO checkoutResponseDTO = new CheckoutResponseDTO();
        checkoutResponseDTO.setId(accommodation.getId());
        checkoutResponseDTO.setCheckOutDate(accommodation.getCheckOutDate());
        checkoutResponseDTO.setTotalValue(accommodation.getTotalValue());

        return checkoutResponseDTO;
    }

    private BigDecimal calculateTotalValue(Accommodation accommodation) {
        LocalDateTime startDate = accommodation.getCheckInDate();
        LocalDateTime endDate = accommodation.getCheckOutDate();
        BigDecimal total = BigDecimal.ZERO;

        long nights = ChronoUnit.DAYS.between(
                startDate.toLocalDate(),
                endDate.toLocalDate()
        );

        if (endDate.toLocalTime().isAfter(CHECKOUT_LIMIT)) {
            nights += 1;
        }

        for (int i = 0; i < nights; i++) {
            LocalDate date = startDate.toLocalDate().plusDays(i);
            total = total.add(calculateDailyRate(date, accommodation.getHasParkingSpot()));
        }

        return total;
    }

    private BigDecimal calculateDailyRate(LocalDate date, boolean hasParking) {
        boolean weekend = isWeekend(date);

        BigDecimal dailyRate = weekend ? WEEKEND_VALUE : WEEKDAY_VALUE;

        if (!hasParking) {
            return dailyRate;
        }

        BigDecimal parkingRate = weekend ? WEEKEND_PARKING : WEEKDAY_PARKING;

        return dailyRate.add(parkingRate);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
