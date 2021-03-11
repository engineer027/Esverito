package com.test.esverito.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "machine")
@Data
public class Machine {
    @Id
    private Long id;
    private String owner;
    private boolean available;
    private String country;
    private String currency;
    @Column(name = "machine_info")
    private String machineInfo;
    @Column(name = "machine_type")
    private String machineType;
    private String photos;
    private Double price;
    private String source;
    private String url;
}
