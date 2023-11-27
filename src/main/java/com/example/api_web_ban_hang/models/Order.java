package com.example.api_web_ban_hang.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "from_name")
    private String fromName;

    // Nhiều trường khác...

    @Column(name = "time_order", nullable = false)
    private LocalDateTime timeOrder;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

    @Column(name = "id_status_order", nullable = false)
    private int idStatusOrder;

    // Getters and setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public LocalDateTime getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(LocalDateTime timeOrder) {
        this.timeOrder = timeOrder;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public int getIdStatusOrder() {
        return idStatusOrder;
    }

    public void setIdStatusOrder(int idStatusOrder) {
        this.idStatusOrder = idStatusOrder;
    }
}
