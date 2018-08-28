package com.cathy.cathyblog.service;

import com.cathy.cathyblog.domain.Tag;
import com.cathy.cathyblog.domain.TagArticle;

public interface TagService {
    void save(Tag tag);
    void saveTagArticle(TagArticle relation);
}
