package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Long> {

    @Query("SELECT ip FROM ImageProduct ip WHERE ip.product.id = :id")
    List<ImageProduct> findByProductId(@Param("id") long id);
}
