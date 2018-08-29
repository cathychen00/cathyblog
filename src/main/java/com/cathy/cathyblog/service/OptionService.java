package com.cathy.cathyblog.service;

import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;

import java.util.List;

public interface OptionService {
    Option getByOptionKey(String key);

    void add(Option option);

    List<Option> getPreference() throws ServiceException;
}
