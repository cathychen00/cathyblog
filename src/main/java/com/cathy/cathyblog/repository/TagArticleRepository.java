package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.TagArticle;
import org.springframework.data.repository.Repository;

public interface TagArticleRepository extends Repository<TagArticle,Integer> {
    void save(TagArticle relation);
}
