package com.example.api_web_ban_hang.models;

import javax.persistence.*;
import java.math.BigDecimal;

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

    // Getters and setters...


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(BigDecimal priceStart) {
        this.priceStart = priceStart;
    }

    public BigDecimal getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(BigDecimal priceEnd) {
        this.priceEnd = priceEnd;
    }
}
