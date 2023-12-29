package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Long> {
}