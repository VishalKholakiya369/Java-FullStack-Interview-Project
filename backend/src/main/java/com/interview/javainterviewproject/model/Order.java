package com.interview.javainterviewproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String status;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime returnedAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
    private Integer numOfItem;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
