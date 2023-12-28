package com.example.api_web_ban_hang.services.interfaces;

public interface ICountProductService {

    /**
     * Đếm số lượng sản phẩm theo loại và trạng thái
     * <p>
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh có trạng thái MỚI
     * + Đếm số lượng áo đá banh có trạng thái HOT
     */
    int countProductsByTypeAndStatus(int type_product, int status_product);

    /**
     * Đếm số lượng sản phẩm theo loại,thương hiệu và giới tính
     *
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh của hãng Nike dành cho Nam
     * + Đếm số lượng sản phẩm áo đá banh của hãng Adidas dành cho Nữ
     */
    int countProductsBy_TypeAndBrandAndSex(int type_product, int brand, int sex);
}
