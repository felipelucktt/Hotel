package com.plaza.hotel.dto;

import java.math.BigDecimal;

public class GuestResponseDTO {
    private Long id;
    private String name;
    private String document;
    private String phoneNumber;
    private BigDecimal totalSpent;
    private BigDecimal lastStayValue;

    public GuestResponseDTO(Long id, String name, String document, String phoneNumber,
                            BigDecimal totalSpent, BigDecimal lastStayValue) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.totalSpent = totalSpent;
        this.lastStayValue = lastStayValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public BigDecimal getLastStayValue() {
        return lastStayValue;
    }

    public void setLastStayValue(BigDecimal lastStayValue) {
        this.lastStayValue = lastStayValue;
    }
}
