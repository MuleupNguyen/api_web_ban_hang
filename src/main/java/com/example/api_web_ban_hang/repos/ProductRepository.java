package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.brand.nameBrand = :brandName")
    List<Product> findAllByBrand(@Param("brandName") String brandName);
    @Query("SELECT p FROM Product p WHERE LOWER(p.nameProduct) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
    
    List<Product> findByBrand_NameBrand(String name,Pageable pageable);
    List<Product> findByTypeProduct_IdOrBrand_IdOrIdSex(Integer idType, Long idBrand, Integer idSex,Pageable pageable);
    List<Product> findByTypeProduct_IdAndBrand_IdAndIdSex(Integer idType, Long idBrand, Integer idSex,Pageable pageable);
    List<Product> findByBrand_IdAndIdSex(Long idBrand, Integer idSex,Pageable pageable);
    List<Product> findByTypeProduct_IdAndIdSex(Integer idType, Integer idSex,Pageable pageable);

    List<Product> findByTypeProduct_IdAndBrand_Id(Integer idType, Long idBrand, Pageable pageable);


}