package com.example.api_web_ban_hang.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public Set<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(Set<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }


    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
// Getters and setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getStarReview() {
        return starReview;
    }

    public void setStarReview(int starReview) {
        this.starReview = starReview;
    }

    public int getIdStatusProduct() {
        return idStatusProduct;
    }

    public void setIdStatusProduct(int idStatusProduct) {
        this.idStatusProduct = idStatusProduct;
    }

    public BigDecimal getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(BigDecimal listedPrice) {
        this.listedPrice = listedPrice;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(BigDecimal promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public int getIdSex() {
        return idSex;
    }

    public void setIdSex(int idSex) {
        this.idSex = idSex;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }
}
