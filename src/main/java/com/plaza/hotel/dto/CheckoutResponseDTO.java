package com.plaza.hotel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CheckoutResponseDTO {
    private Long id;
    private LocalDateTime checkOutDate;
    private BigDecimal totalValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
