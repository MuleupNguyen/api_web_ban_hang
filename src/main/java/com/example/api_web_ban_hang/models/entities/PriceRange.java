package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "price_ranges")
public class PriceRange {
    @Id
    @Column(name = "name_price_range")
    private String name;

    @Column(name = "price_start", nullable = false, precision = 65, scale = 30)
    private BigDecimal priceStart;

    @Column(name = "price_end", nullable = false, precision = 65, scale = 30)
    private BigDecimal priceEnd;
}
