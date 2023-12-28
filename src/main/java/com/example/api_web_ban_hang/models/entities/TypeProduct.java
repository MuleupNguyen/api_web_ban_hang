package com.example.api_web_ban_hang.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "type_products")
public class TypeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int id;

    @Column(name = "name_type", nullable = false)
    private String nameType;
}