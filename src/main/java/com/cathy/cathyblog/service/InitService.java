package com.cathy.cathyblog.service;

import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.User;

public interface InitService {
    /**
     * 站点是否已经初始化
     * @return
     */
    boolean isInited();

    /**
     * 执行站点初始化操作
     * @param user 初始化管理员
     */
    void init(User user) throws ServiceException;
}
