package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "size_products")
public class SizeProduct {

    @EmbeddedId
    private SizeProductId id;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("nameSize")
    @JoinColumn(name = "name_size", nullable = false)
    private Size size;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;
}

