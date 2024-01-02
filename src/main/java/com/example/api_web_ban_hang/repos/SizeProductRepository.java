package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.SizeProduct;
import com.example.api_web_ban_hang.models.entities.SizeProductId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SizeProductRepository extends CrudRepository<SizeProduct, SizeProductId> {

    @Query("SELECT sp FROM SizeProduct sp WHERE sp.product.id = :id")
    List<SizeProduct> findByProductId(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO size_products (id_product, name_size, quantity_available) VALUES (:id_product, :name_size, :quantity)",
            nativeQuery = true)
    void insertSize(@Param("id_product") long idProduct, @Param("name_size") String nameSize, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM size_products WHERE id_product = :id", nativeQuery = true)
    int clearSizes(@Param("id") long idProduct);
}
