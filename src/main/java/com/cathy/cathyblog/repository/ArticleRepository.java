package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Article;
import org.springframework.data.repository.Repository;

public interface ArticleRepository extends Repository<Article,Integer> {
    void save(Article article);
}
