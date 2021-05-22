package com.pandy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Pandy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    private Integer tagId;
    private String tagName;
    private Date createTime;
    private Date updateTime;
}
