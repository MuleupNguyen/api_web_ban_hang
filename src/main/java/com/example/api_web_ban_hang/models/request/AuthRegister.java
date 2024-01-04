package com.example.api_web_ban_hang.models.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class AuthRegister {
    @NotNull
    @Length(min = 1, max = 50)
    private String fullName;
    @NotNull
    @Length(min = 10, max = 50)
    private String username;
    @NotNull
    @Length(min = 5, max = 50)
    private String password;

}
