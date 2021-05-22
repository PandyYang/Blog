package com.pandy.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Pandy
 */
@Data
public class TagDTO {
    private Integer tagId;
    private String tagName;
    private Date createTime;
    private Date updateTime;
}
