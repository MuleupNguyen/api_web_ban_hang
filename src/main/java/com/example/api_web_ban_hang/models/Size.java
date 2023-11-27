package com.example.api_web_ban_hang.models;

import javax.persistence.*;

@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @Column(name = "name_size")
    private String nameSize;

    // Getters and setters...

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
}
