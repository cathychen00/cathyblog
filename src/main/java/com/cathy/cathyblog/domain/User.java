package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String userEmail;
    private String userName;
    private String userURL;
    /**
     * 用户密码，MD5
     */
    private String userPassword;
    /**
     * 用户角色，管理员：adminRole，普通用户：defaultRole，访客用户：visitorRole
     */
    private String userRole;
    /**
     * 用户文章计数
     */
    private Integer userArticleCount;
    /**
     * 用户已发布文章计数
     */
    private Integer userPublishedArticleCount;
    /**
     * 头像
     */
    private String userAvatar;
    private String authorThumbnailURL;
}
