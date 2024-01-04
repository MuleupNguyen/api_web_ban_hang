package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.dto.ProductDTO;

import java.util.List;

import com.example.api_web_ban_hang.models.entities.Product;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductDTO findById(long id);
    List<ProductDTO> findByNameProduct(String name,Pageable pageable);
    List<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findProductByBrandWithOptionSort(String name,Pageable pageable);

    List<ProductDTO> findByTypeProduct_IdOrBrand_IdOrIdSex(Integer idType, Long idBrand, Integer idSex, Pageable pageable);

    List<ProductDTO> findByTypeProduct_IdAndBrand_IdAndIdSex(Integer idType, Long idBrand, Integer idSex,Pageable pageable);

    List<ProductDTO> findByBrand_IdAndIdSex(Long idBrand, Integer idSex,Pageable pageable);
    List<ProductDTO> findByTypeProduct_IdAndIdSex(Integer idType, Integer idSex,Pageable pageable);
    List<ProductDTO> findByTypeProduct_IdAndBrand_Id(Integer idType, Long idBrand, Pageable pageable);
}
