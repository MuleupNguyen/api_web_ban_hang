package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role_details")
public class RoleDetail {
    @EmbeddedId
    private RoleDetailId id;

    @ManyToOne
    @MapsId("idAdmin")
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @ManyToOne
    @MapsId("idRole")
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;
}