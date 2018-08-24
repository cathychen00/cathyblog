package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 分类-标签关联表
 */
@Entity
@Data
public class CategoryTag {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer categoryId;
    private Integer tagId;
}