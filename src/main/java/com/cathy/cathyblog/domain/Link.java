package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Link {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 链接表
     */
    private String link;
    /**
     * 链接地址
     */
    private String linkAddress;
    private String linkDescription;
    private Integer linkOrder;
    /**
     * 链接标题
     */
    private String linkTitle;
}