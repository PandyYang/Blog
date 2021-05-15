package com.pandy.blog.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "menu")
@Entity
@Data
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "menuName")
  private String menuName;
  @Column(name = "uri")
  private String uri;
  @Column(name = "icon")
  private String icon;
  @Column(name = "parent_id")
  private Integer parentId;
  @Column(name = "disabled")
  private Integer disabled;

}
