package com.pandy.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Pandy
 */
@Data
public class MenuDTO {

    private Integer id;
    private String menuName;
    private String uri;
    private String icon;
    private String parentId;
    private boolean disabled;
    private List<MenuDTO> children;
}
