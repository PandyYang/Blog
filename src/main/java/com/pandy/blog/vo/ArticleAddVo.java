package com.pandy.blog.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pandy
 */
public class ArticleAddVo implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String picture;
    private Integer categoryId;
    private List<Integer> tagIdList;
    private boolean isTop;
    private boolean isDraft;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
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
