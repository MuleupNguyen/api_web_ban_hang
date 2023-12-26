package com.example.api_web_ban_hang.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "src", nullable = false)
    private String src;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "id_address", nullable = false)
    private String idAddress;

    @Column(name = "web_browser", nullable = false)
    private String webBrowser;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;

    @Column(name = "id_level_log", nullable = false)
    private int idLevelLog;
}
