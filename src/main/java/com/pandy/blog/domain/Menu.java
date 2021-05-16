package com.pandy.blog.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "menu")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "uri")
    private String uri;

    @Column(name = "icon")
    private String icon;

    //  @NotFound(action = NotFoundAction.IGNORE)
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "parentId", referencedColumnName = "id", insertable = true, updatable = false)
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "disabled")
    private boolean disabled;

    @Transient
    private List<Menu> children = new ArrayList<>();

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
