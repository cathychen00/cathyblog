package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.domain.Option;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OptionRepository extends Repository<Option, String> {
    Option getByOptionKey(String key);
    void save(Option option);
    List<Option> getByOptionCategory(String category);
}
