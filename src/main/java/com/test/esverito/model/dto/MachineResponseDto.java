package com.test.esverito.model.dto;

import lombok.Data;

@Data
public class MachineResponseDto {
    private Long id;
    private String owner;
    private boolean available;
    private String country;
    private String currency;
    private String machineInfo;
    private String machineType;
    private String photos;
    private Double price;
    private String source;
    private String url;
}
