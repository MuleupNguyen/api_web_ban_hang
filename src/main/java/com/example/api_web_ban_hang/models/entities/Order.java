package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "from_name")
    private String fromName;

    @Column(name = "from_phone")
    private String fromPhone;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "from_ward_name")
    private String fromWardName;

    @Column(name = "from_district_name")
    private String fromDistrictName;

    @Column(name = "from_province_name")
    private String fromProvinceName;

    @Column(name = "from_ward_id")
    private String fromWardId;

    @Column(name = "from_district_id")
    private String fromDistrictId;

    @Column(name = "from_province_id")
    private String fromProvinceId;

    @Column(name = "return_name")
    private String returnName;

    @Column(name = "return_phone")
    private String returnPhone;

    @Column(name = "return_address")
    private String returnAddress;

    @Column(name = "return_ward_name")
    private String returnWardName;

    @Column(name = "return_district_name")
    private String returnDistrictName;

    @Column(name = "return_province_name")
    private String returnProvinceName;

    @Column(name = "return_ward_id")
    private String returnWardId;

    @Column(name = "return_district_id")
    private String returnDistrictId;

    @Column(name = "return_province_id")
    private String returnProvinceId;

    @Column(name = "email_customer")
    private String emailCustomer;

    @Column(name = "to_name")
    private String toName;

    @Column(name = "to_phone")
    private String toPhone;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "to_ward_name")
    private String toWardName;

    @Column(name = "to_district_name")
    private String toDistrictName;

    @Column(name = "to_province_name")
    private String toProvinceName;

    @Column(name = "to_ward_id")
    private String toWardId;

    @Column(name = "to_district_id")
    private String toDistrictId;

    @Column(name = "to_province_id")
    private String toProvinceId;

    @Column(name = "note")
    private String note;

    @Column(name = "ship_price")
    private BigDecimal shipPrice;

    @Column(name = "order_value")
    private BigDecimal orderValue;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "time_order")
    private LocalDateTime timeOrder;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

    @Column(name = "id_status_order")
    private Integer idStatusOrder;

}
