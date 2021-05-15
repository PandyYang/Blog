package com.pandy.blog.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Pandy
 */
@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String nickname;
}
