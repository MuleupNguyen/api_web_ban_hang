package com.example.api_web_ban_hang.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SizeDTO {
    private String name_size;
    private long quantity_available;

}
