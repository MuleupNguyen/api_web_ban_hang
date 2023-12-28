package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "name_product", nullable = false)
    private String nameProduct;

    @Column(name = "star_review", nullable = false)
    private int starReview;

    @Column(name = "id_status_product", nullable = false)
    private int idStatusProduct;

    @Column(name = "listed_price", nullable = false, precision = 65, scale = 30)
    private BigDecimal listedPrice;

    @Column(name = "promotional_price", nullable = false, precision = 65, scale = 30)
    private BigDecimal promotionalPrice;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_type_product", nullable = false)
    private TypeProduct typeProduct;

    @Column(name = "id_sex", nullable = false)
    private int idSex;

    @Column(name = "time_created", nullable = false)
    private LocalDateTime timeCreated;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Set<ImageProduct> imageProducts = new HashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
