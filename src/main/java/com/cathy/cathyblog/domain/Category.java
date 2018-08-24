package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 分类表
 */
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 分类标题
     */
    private String categoryTitle;
    /**
     * 分类访问路径
     */
    private String categoryURI;
    /**
     * 分类描述
     */
    private String categoryDescription;
    /**
     * 分类展现的排序
     */
    private Integer categoryOrder;
    /**
     * 分类下聚合的标签计数
     */
    private Integer categoryTagCnt;
}
