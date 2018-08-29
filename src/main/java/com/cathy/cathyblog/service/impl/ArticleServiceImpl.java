package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Article;
import com.cathy.cathyblog.repository.ArticleRepository;
import com.cathy.cathyblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Page<Article> selectNoCritia(int pageIndex, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles;
    }
}
