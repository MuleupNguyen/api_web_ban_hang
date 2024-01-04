package com.example.api_web_ban_hang.models;

import lombok.Data;
@Data
public class AddressRequest {
        private String address;
        private String ward_name;
        private String district_name;
        private String province_name;
        private String ward_id;
        private String district_id;
        private String province_id;
}
