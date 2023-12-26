package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "image_products")
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "time_created", nullable = false)
    private LocalDateTime timeCreated;
}
