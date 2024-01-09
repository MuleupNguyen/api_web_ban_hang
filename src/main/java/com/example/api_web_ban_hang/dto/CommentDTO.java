package com.example.api_web_ban_hang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private int star;
    private String content;
    private String fullName;

}
