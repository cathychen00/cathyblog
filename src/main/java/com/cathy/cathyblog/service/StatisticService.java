package com.cathy.cathyblog.service;

import com.cathy.cathyblog.common.exceptions.RepositoryException;

public interface StatisticService {

    void initStatistic();

    void incStatistic(String key) throws RepositoryException;
}
