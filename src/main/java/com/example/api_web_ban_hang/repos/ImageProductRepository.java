package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Long> {

    @Query("SELECT ip FROM ImageProduct ip WHERE ip.product.id = :id")
    List<ImageProduct> findByProductId(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO image_products (path, time_created, id_product) VALUES (:path, NOW(), :id_product)",
            nativeQuery = true)
    void insertImage(@Param("path") String path, @Param("id_product") long productId);

    @Transactional
    int deleteByProduct_Id(long productId);
}
