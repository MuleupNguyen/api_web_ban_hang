package com.example.api_web_ban_hang.models.entities;

import com.example.api_web_ban_hang.models.entities.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "history_update_products")
public class HistoryUpdateProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_update_product")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "time_updated", nullable = false)
    private LocalDateTime timeUpdated;
}
