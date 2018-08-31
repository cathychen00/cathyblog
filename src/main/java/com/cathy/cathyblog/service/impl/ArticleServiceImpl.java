package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Article;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.repository.ArticleRepository;
import com.cathy.cathyblog.service.ArticleService;
import com.cathy.cathyblog.service.UserService;
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
    @Autowired
    UserService userService;
    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Page<Article> selectNoCritia(int pageIndex, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        Page<Article> articles = articleRepository.findAll(pageable);
        //补全作者信息
        for(Article item:articles){
            User author=userService.getByEmail(item.getArticleAuthorEmail());
            item.setAuthor(author);
        }
        return articles;
    }
}
