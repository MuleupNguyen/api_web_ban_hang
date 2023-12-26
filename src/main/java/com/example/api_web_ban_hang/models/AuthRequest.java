package com.example.api_web_ban_hang.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequest {
    @NotNull
    @Length(min = 5, max = 50)
    private String username;
    @NotNull
    @Length(min = 5, max = 10)
    private String password;
}