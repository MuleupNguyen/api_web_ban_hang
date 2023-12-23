package com.example.api_web_ban_hang.services.interfaces;

public interface ICountProductService {

    /**
     * Đếm số lượng sản phẩm theo loại và trạng thái
     *
     * VD:
     * + Đếm số lượng sản phẩm áo đá banh có trạng thái MỚI
     * + Đếm số lượng áo đá banh có trạng thái HOT
     * */
    int countProductsByTypeAndStatus(int type_product,int status_product);
}
