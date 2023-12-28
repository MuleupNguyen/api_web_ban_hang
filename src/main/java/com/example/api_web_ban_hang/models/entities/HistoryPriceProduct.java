package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "history_price_products")
public class HistoryPriceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price_product")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "listed_price", nullable = false, precision = 65, scale = 30)
    private BigDecimal listedPrice;

    @Column(name = "promotional_price", nullable = false, precision = 65, scale = 30)
    private BigDecimal promotionalPrice;

    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;
}
