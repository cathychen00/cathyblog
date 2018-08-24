package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue
    private Integer tagId;
    private String tagTitle;
    /**
     * 标签关联的已发布文章计数
     */
    private Integer tagPublishedRefCount;
    /**
     * 标签关联的文章计数
     */
    private Integer tagReferenceCount;
}
