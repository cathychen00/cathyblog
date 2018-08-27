package com.cathy.cathyblog.service;

import com.cathy.cathyblog.domain.Link;

public interface LinkService {
    void save(Link link);
    int queryMaxOrder();
}
