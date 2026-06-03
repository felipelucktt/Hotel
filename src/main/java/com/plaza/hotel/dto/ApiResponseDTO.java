package com.plaza.hotel.dto;

public class ApiResponseDTO {
    private String message;

    public ApiResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}