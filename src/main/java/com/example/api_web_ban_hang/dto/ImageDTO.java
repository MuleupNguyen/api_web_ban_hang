package com.example.api_web_ban_hang.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDTO {
    private long id_image;
    private String path_image;
}
