package com.example.api_web_ban_hang.mapper;

import com.example.api_web_ban_hang.dto.OrderDTO;
import com.example.api_web_ban_hang.models.entities.Order;

public class MapperOrder {
    public static OrderDTO mapperOrdertoDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(order.getIdOrder());
        orderDTO.setAddress(order.getToAddress());
        orderDTO.setDistrict(order.getToDistrictName());
        orderDTO.setNameCustomer(order.getToName());
        orderDTO.setPhoneCustomer(order.getToPhone());
        orderDTO.setAddressCustomer(order.getToAddress());
        orderDTO.setShipPrice(order.getShipPrice());
        orderDTO.setOrderValue(order.getOrderValue());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setList_order(MapperOrderDetail.mapOrderDetailsToDTO(order.getListOrderDatail()));
        orderDTO.setStatus(order.getIdStatusOrder());
        orderDTO.setTimeOrder(order.getTimeOrder().toString());

        return orderDTO;
    }

    }



