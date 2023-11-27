package com.example.api_web_ban_hang.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoleDetailId implements Serializable {
    @Column(name = "id_admin")
    private Long idAdmin;

    @Column(name = "id_role")
    private int idRole;

    // Getters and setters...

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}