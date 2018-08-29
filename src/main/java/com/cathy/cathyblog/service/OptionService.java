package com.cathy.cathyblog.service;

import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;

import java.util.List;
import java.util.Map;

public interface OptionService {
    Option getByOptionKey(String key);

    void add(Option option);

    Map<String,String> getPreference() throws ServiceException;
}
