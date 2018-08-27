package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface LinkRepository extends Repository<Link,Integer> {
    void save(Link link);
    @Query("select max(linkOrder) as maxorder FROM Link ")
    int queryMaxOrder();
}
