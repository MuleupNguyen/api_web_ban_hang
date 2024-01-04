package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.dto.ProductDTO;
import com.example.api_web_ban_hang.mapper.MapperProduct;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO findById(long id) {
        return Optional.ofNullable(productRepository.findById(id)
                        .orElse(null))
                .map(MapperProduct::mapperProductToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
    }


    @Override
    public List<ProductDTO> findByNameProduct(String name,Pageable pageable) {

        return Optional.ofNullable(productRepository.findByNameContainingIgnoreCase(name,pageable))
                .orElse(Collections.emptyList())
                .stream()
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        return Optional.ofNullable(productRepository.findAll(pageable)).orElse(null)
                .stream()
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());

    }


	@Override
	public List<ProductDTO> findProductByBrandWithOptionSort(String name, Pageable pageable) {
		return Optional.of(productRepository.findByBrand_NameBrand(name, pageable))
				.orElse(Collections.emptyList())
				.stream()
				.sorted(Comparator.comparing(Product::getTimeCreated).reversed())
				.map(MapperProduct::mapperProductToDTO)
				.collect(Collectors.toList());
	}

    @Override
    public List<ProductDTO> findByTypeProduct_IdOrBrand_IdOrIdSex(Integer idType, Long idBrand, Integer idSex, Pageable pageable) {
        return  Optional.of(productRepository.findByTypeProduct_IdOrBrand_IdOrIdSex(idType, idBrand, idSex, pageable))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(Product::getTimeCreated).reversed())
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByTypeProduct_IdAndBrand_IdAndIdSex(Integer idType, Long idBrand, Integer idSex, Pageable pageable) {
        return  Optional.of(productRepository.findByTypeProduct_IdAndBrand_IdAndIdSex(idType, idBrand, idSex, pageable))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(Product::getTimeCreated).reversed())
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByBrand_IdAndIdSex(Long idBrand, Integer idSex, Pageable pageable) {
        System.out.println("brand and sex");
        return  Optional.of(productRepository.findByBrand_IdAndIdSex(idBrand, idSex, pageable))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(Product::getTimeCreated).reversed())
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByTypeProduct_IdAndIdSex(Integer idType, Integer idSex, Pageable pageable) {
        System.out.println("type and sex");
        return  Optional.of(productRepository.findByTypeProduct_IdAndIdSex(idType, idSex, pageable))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(Product::getTimeCreated).reversed())
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByTypeProduct_IdAndBrand_Id(Integer idType, Long idBrand, Pageable pageable) {
        System.out.println("type and brand");
        return  Optional.of(productRepository.findByTypeProduct_IdAndBrand_Id(idType, idBrand, pageable))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(Product::getTimeCreated).reversed())
                .map(MapperProduct::mapperProductToDTO)
                .collect(Collectors.toList());
    }
}
