package com.pandy.blog.dto;

import com.pandy.blog.domain.Tag;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Pandy
 */
public class ArticleDTO implements Serializable {
    private Integer id;
    private String title;
    private Integer categoryId;
    private List<Tag> tagList;
    private List<Integer> tagIdList;
    private String categoryName;
    private List<String> tagsName;
    private Date createTime;
    private Date updateTime;
    private boolean isTop;
    private boolean isDraft;
    private String content;

    public ArticleDTO() {
    }

    public ArticleDTO(String content, Integer id, String title, String categoryName, List<String> tagIdList, Date createTime, Date updateTime, boolean isTop, boolean isDraft) {
        this.content = content;
        this.id = id;
        this.title = title;
        this.categoryName = categoryName;
        this.tagsName = tagIdList;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isTop = isTop;
        this.isDraft = isDraft;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(List<String> tagsName) {
        this.tagsName = tagsName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }
}
