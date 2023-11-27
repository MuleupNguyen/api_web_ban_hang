package com.example.api_web_ban_hang.models;

import javax.persistence.*;

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

    // Getters and setters...

    public SizeProductId getId() {
        return id;
    }

    public void setId(SizeProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}

