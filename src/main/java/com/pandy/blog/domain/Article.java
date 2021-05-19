package com.pandy.blog.domain;


import com.pandy.blog.vo.ArticleAddVo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "article")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String picture;

    @Column(name = "category_id")
    private Integer categoryId;

    private String content;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "delete_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    @Column(name = "is_top")
    private boolean isTop;

    @Column(name = "is_draft")
    private boolean isDraft;

    public Article(ArticleAddVo articleAddVo) {
        this.id = articleAddVo.getId();
        this.title = articleAddVo.getTitle();
        this.picture = articleAddVo.getPicture();
        this.content = articleAddVo.getContent();
        this.categoryId = articleAddVo.getCategoryId();
        this.isTop = articleAddVo.isTop();
        this.isDraft = articleAddVo.isDraft();
    }

    public Article() {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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


    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}
