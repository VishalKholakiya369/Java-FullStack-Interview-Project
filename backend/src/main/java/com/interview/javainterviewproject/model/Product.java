package com.interview.javainterviewproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cost;
    private String category;
    private String name;
    private String brand;
    private Double retailPrice;
    private String department;
    private String sku;

    @ManyToOne

    private DistributionCenter distributionCenter;

    @OneToMany(mappedBy = "product")
    private List<InventoryItem> inventoryItems;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;


}
