package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
