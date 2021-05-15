package com.pandy.blog.domain;


import lombok.Data;

import javax.persistence.*;

@Table(name = "user_role")
@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;
}
