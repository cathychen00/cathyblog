package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Link;
import com.cathy.cathyblog.repository.LinkRepository;
import com.cathy.cathyblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    LinkRepository linkRepository;
    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public int queryMaxOrder() {
        return linkRepository.queryMaxOrder();
    }
}
