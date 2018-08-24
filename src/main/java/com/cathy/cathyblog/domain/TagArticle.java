package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TagArticle {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer tagId;
    private Integer articleId;
}
