package com.cathy.cathyblog.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Option {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 配置项值
     */
    private String optionValue;
    /**
     * 配置项分类
     */
    private String optionCategory;
}
