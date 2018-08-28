package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Tag;
import org.springframework.data.repository.Repository;

public interface TagRepository extends Repository<Tag,Integer> {
    void save(Tag tag);
}
