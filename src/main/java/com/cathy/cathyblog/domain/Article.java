package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue
    private Integer id;
    private String articleTitle;
    /**
     * 文章摘要
     */
    private String articleAbstract;
    /**
     * 文章标签，英文逗号分隔
     */
    private String articleTags;
    /**
     * 文章作者邮箱
     */
    private String articleAuthorEmail;
    /**
     * 文章是否可以评论
     */
    private Boolean articleCommentable;
    /**
     * 文章评论计数
     */
    private Integer articleCommentCount;
    /**
     * 浏览数
     */
    private Integer articleViewCount;
    private String articleContent;
    /**
     * 文章访问路径
     */
    private String articlePermalink;
    /**
     * 文章是否已经发布过
     */
    private Boolean articleHadBeenPublished;
    /**
     * 文章是否处于已发布状态
     */
    private Boolean articleIsPublished;
    /**
     * 文章是否置顶
     */
    private Boolean articlePutTop;
    private Date articleCreateDate;
    private Date articleUpdateDate;
    /**
     * 文章随机数，用于快速查询随机文章列表
     */
    private double articleRandomDouble;
    /**
     * 文章关联的签名档 id
     */
    private Integer articleSignId;
    /**
     * 文章浏览密码，留空为不设置访问密码
     */
    private String articleViewPwd;
    @Transient
    private User author;
}
