package com.plaza.hotel.dto;

import java.time.LocalDateTime;

public class CheckoutDTO {
    private LocalDateTime checkOutDate;

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
