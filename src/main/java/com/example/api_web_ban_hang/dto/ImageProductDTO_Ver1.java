package com.example.api_web_ban_hang.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageProductDTO_Ver1 {
    public long id_image;
    public String path_image;
    public ImageProductDTO_Ver1(long id_image, String path) {
        this.id_image = id_image;
        this.path_image = path;
    }
}
