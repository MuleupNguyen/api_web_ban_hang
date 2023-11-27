package com.example.api_web_ban_hang.models;
import javax.persistence.*;
import java.io.Serializable;

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

    // Getters and setters...

    public RoleDetailId getId() {
        return id;
    }

    public void setId(RoleDetailId id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


