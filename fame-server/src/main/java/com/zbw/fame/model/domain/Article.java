package com.zbw.fame.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 文章 Model
 *
 * @author zbw
 * @since 2017/7/8 9:29
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends BaseEntity {

    /**
     * 内容标题
     */
    @Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    /**
     * 内容所属用户id
     */
    @Column(name = "author_id", columnDefinition = "INT")
    private Integer authorId;

    /**
     * 点击量
     */
    @Column(name = "hits", columnDefinition = "INT DEFAULT 0 NOT NULL")
    private Integer hits;

    /**
     * 标签列表
     */
    @Column(name = "tags", columnDefinition = "VARCHAR(500)")
    private String tags;

    /**
     * 文章分类
     */
    @Column(name = "category", columnDefinition = "VARCHAR(500)")
    private String category;

    /**
     * 内容状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(32)")
    private String status;

    /**
     * 内容类别
     */
    @Column(name = "type", columnDefinition = "VARCHAR(32)")
    private String type;

    /**
     * 是否允许评论
     */
    @Column(name = "allow_comment", columnDefinition = "BOOLEAN DEFAULT TRUE NOT NULL")
    private Boolean allowComment;

    /**
     * 评论数量
     */
    @Column(name = "comment_count", columnDefinition = "INT DEFAULT 0 NOT NULL")
    private Integer commentCount;

    @Override
    protected void prePersist() {
        super.prePersist();
        if (null == hits) {
            hits = 0;
        }
        if (null == allowComment) {
            allowComment = true;
        }
        if (null == commentCount) {
            commentCount = 0;
        }
    }
}
