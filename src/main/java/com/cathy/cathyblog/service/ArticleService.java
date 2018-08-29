package com.cathy.cathyblog.service;

import com.cathy.cathyblog.domain.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {
    void save(Article article);
    Page<Article> selectNoCritia(int pageIndex,int pageSize);
}
