package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Option;
import org.springframework.data.repository.Repository;

public interface OptionRepository extends Repository<Option, String> {
    Option getByOptionKey(String key);
    void save(Option option);
}
