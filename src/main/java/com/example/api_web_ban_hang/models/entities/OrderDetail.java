package com.example.api_web_ban_hang.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "name_size")
    private String nameSize;

    @Column(name = "price", nullable = false, precision = 65, scale = 4)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
