package com.plaza.hotel.dto;

import com.plaza.hotel.entity.Guest;

import java.time.LocalDateTime;

public class AccommodationDTO {
    private Boolean hasParkingSpot;
    private LocalDateTime checkInDate;
    private Guest guest;

    public Boolean isHasParkingSpot() {
        return hasParkingSpot;
    }

    public void setHasParkingSpot(Boolean hasParkingSpot) {
        this.hasParkingSpot = hasParkingSpot;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Boolean getHasParkingSpot() {
        return hasParkingSpot;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
