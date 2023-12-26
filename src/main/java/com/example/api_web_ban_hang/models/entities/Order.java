package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "from_name")
    private String fromName;

    @Column(name = "time_order", nullable = false)
    private LocalDateTime timeOrder;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

    @Column(name = "id_status_order", nullable = false)
    private int idStatusOrder;
}
