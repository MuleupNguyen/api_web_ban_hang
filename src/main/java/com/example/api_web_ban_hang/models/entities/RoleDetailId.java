package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class RoleDetailId implements Serializable {
    @Column(name = "id_admin")
    private Long idAdmin;

    @Column(name = "id_role")
    private int idRole;
}