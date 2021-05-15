package com.pandy.blog.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "role")
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "create_time")
    private Date createTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")}
    )
    private List<Menu> menus;
}
