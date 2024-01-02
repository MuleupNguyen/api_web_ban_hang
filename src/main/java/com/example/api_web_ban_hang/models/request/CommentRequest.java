package com.example.api_web_ban_hang.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CommentRequest {

    private long id_product;
    private long id_user;
    private int star;
    private String content;
}
