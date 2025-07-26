package com.interview.javainterviewproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class DistributionCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "distributionCenter")
    private List<Product> products;
}
