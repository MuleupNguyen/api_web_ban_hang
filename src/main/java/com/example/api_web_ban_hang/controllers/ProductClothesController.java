package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.common.Brand;
import com.example.api_web_ban_hang.common.Sex;
import com.example.api_web_ban_hang.common.StatusProduct;
import com.example.api_web_ban_hang.common.TypeProduct;
import com.example.api_web_ban_hang.dto.PagedResponse_Ver1;
import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;
import com.example.api_web_ban_hang.filter.PaginationFilter;
import com.example.api_web_ban_hang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller này dùng để handle các API liên quan đến sản phẩm quần áo
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductClothesController {
    private final ProductService _productService;

    @Autowired
    public ProductClothesController(ProductService productService) {
        this._productService = productService;
    }

    @GetMapping("/ds-ao-da-banh-moi")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtNew(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.MOI, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.MOI);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-hot")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtHot(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.HOT, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.HOT);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-khuyen-mai")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtPromotional(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.KHUYEN_MAI, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsByTypeAndStatus(TypeProduct.AO_DAU, StatusProduct.KHUYEN_MAI);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-nike-nam")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtNikeForMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.NIKE, Sex.NAM, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.NIKE, Sex.NAM);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-adidas-nam")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtAdidasForMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.ADIDAS, Sex.NAM, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.ADIDAS, Sex.NAM);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-puma-nam")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerShirtPumaForMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.PUMA, Sex.NAM, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.PUMA, Sex.NAM);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-nike-nu")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerNikeForWoMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.NIKE, Sex.NU, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.NIKE, Sex.NU);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-adidas-nu")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerAdidasForWoMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.ADIDAS, Sex.NU, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.ADIDAS, Sex.NU);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ds-ao-da-banh-puma-nu")
    public ResponseEntity<PagedResponse_Ver1<List<ProductDTO_Ver1>>> getListSoccerPumaForWoMen(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        try {
            var validFilter = new PaginationFilter(page, pageSize);

            var data = _productService.getListProductBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.PUMA, Sex.NU, validFilter.current_page, validFilter.page_size);

            if (data == null || data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            var total_items = _productService.countProductsBy_TypeAndBrandAndSex(TypeProduct.AO_DAU, Brand.PUMA, Sex.NU);

            return ResponseEntity.ok(new PagedResponse_Ver1<>(data, validFilter.current_page, validFilter.page_size, total_items));

        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
