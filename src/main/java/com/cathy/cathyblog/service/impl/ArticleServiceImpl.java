package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Article;
import com.cathy.cathyblog.repository.ArticleRepository;
import com.cathy.cathyblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }
}
