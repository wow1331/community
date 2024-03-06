package com.boot.community.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;
    private Timestamp date;
    private int hit;
    private int recommend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}