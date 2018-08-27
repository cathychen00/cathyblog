package com.cathy.cathyblog.service;

import com.cathy.cathyblog.domain.Option;

public interface OptionService {
    Option getByOptionKey(String key);

    void add(Option option);
}
