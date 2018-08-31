package com.cathy.cathyblog.service;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathyblogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleServiceTests {
    @Autowired
    ArticleService articleService;
    @Test
    public void test(){
//        Article article=new Article();
//        article.setArticleTitle("test title");
//        article.setArticleContent("<p>test content</p>");
//        article.setArticleIsPublished(true);
//        article.setArticleHadBeenPublished(true);
//        article.setArticleCreateDate(new Date());
//        article.setArticleUpdateDate(new Date());
//        articleService.save(article);
        Page<Article> page=articleService.selectNoCritia(0,5);
        for(Article item:page){
            System.out.println(item.getId());
        }
    }
}
