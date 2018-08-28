package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Tag;
import com.cathy.cathyblog.domain.TagArticle;
import com.cathy.cathyblog.repository.TagArticleRepository;
import com.cathy.cathyblog.repository.TagRepository;
import com.cathy.cathyblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagArticleRepository tagArticleRepository;
    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void saveTagArticle(TagArticle relation) {
        tagArticleRepository.save(relation);
    }
}
