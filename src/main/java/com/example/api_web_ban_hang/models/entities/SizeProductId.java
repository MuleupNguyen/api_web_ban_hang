package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class SizeProductId implements Serializable {
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_size")
    private String nameSize;
}