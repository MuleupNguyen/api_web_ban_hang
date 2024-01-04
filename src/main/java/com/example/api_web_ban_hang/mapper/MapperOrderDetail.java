package com.example.api_web_ban_hang.mapper;

import com.example.api_web_ban_hang.dto.OrderDetailDTO;
import com.example.api_web_ban_hang.models.entities.OrderDetail;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperOrderDetail {

    public static List<OrderDetailDTO> mapOrderDetailsToDTO(Set<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(MapperOrderDetail::mapOrderDetailToDTO)
                .collect(Collectors.toList());
    }

    public static OrderDetailDTO mapOrderDetailToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setNameSize(orderDetail.getNameSize());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setProductDTO(MapperProduct.mapperProductToDTO(orderDetail.getProduct()));

        return orderDetailDTO;
    }
}