package com.pandy.blog.domain;

import javax.persistence.*;

/**
 * @author Pandy
 */
@Table(name = "article_tag")
@Entity
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article_id")
    private Integer articleId;
//    @Column(name = "tag_id")
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
