package com.pandy.blog.domain;


import lombok.Data;

import javax.persistence.*;

@Table(name = "role_menu")
@Entity
@Data
public class RoleMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "menu_id")
  private Integer menuId;
  @Column(name = "role_id")
  private Integer roleId;
}
