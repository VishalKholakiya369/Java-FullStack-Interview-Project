package com.interview.javainterviewproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime soldAt;
    private Double cost;

    private String productCategory;
    private String productName;
    private String productBrand;
    private Double productRetailPrice;
    private String productDepartment;
    private String productSku;

    @ManyToOne
    @JoinColumn(name = "product_distribution_center_id")
    private DistributionCenter productDistributionCenter;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
