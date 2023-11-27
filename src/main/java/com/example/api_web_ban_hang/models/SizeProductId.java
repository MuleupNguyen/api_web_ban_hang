package com.example.api_web_ban_hang.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SizeProductId implements Serializable {
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_size")
    private String nameSize;

    // Getters and setters...

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
}