package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.dto.ProductDTO_Ver1;

import java.util.List;

/**
 * Interface này chứa các phương thức dùng để lấy dữ liệu từ database
 */
public interface IGetProductService {

    /**
     * Lấy ra danh sách sản phẩm theo loại và trạng thái
     * <p>
     * VD:
     * + Lấy ra danh sách áo đá banh đang có trạng thái MỚI
     * + Lấy ra danh sách áo đá banh đang có trạng thái HOT
     */
    List<ProductDTO_Ver1> getListProductByTypeAndStatus(int type_product, int status_product, int page, int page_size);

    /**
     * Lấy ra danh sách sản phẩm theo loại,thương hiệu và giới tính
     * <p>
     * VD:
     * + Lấy ra danh sách áo đá banh của hãng Nike dành cho Nam
     * + Lấy ra danh sách áo đá banh của hãng Adidas dành cho Nữ
     */

    List<ProductDTO_Ver1> getListProductBy_TypeAndBrandAndSex(int type_product, int brand, int sex, int page, int page_size);
}
