package com.pandy.blog.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Pandy
 */
public class ArticleDTO {
    private String title;
    private String categoryName;
    private List<String> tagsName;
    private Date createTime;
    private Date updateTime;
    private boolean isTop;
    private boolean isDraft;

    public ArticleDTO() {
    }

    public ArticleDTO(String title, String categoryName, List<String> tagIdList, Date createTime, Date updateTime, boolean isTop, boolean isDraft) {
        this.title = title;
        this.categoryName = categoryName;
        this.tagsName = tagIdList;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isTop = isTop;
        this.isDraft = isDraft;
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
