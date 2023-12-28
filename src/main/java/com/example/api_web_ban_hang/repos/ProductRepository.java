package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.brand.nameBrand = :brandName")
    List<Product> findAllByBrand(@Param("brandName") String brandName);
}