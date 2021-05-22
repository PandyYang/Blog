package com.pandy.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Pandy
 */
@Data
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
