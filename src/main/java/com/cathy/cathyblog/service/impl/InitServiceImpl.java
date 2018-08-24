package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.repository.UserRepository;
import com.cathy.cathyblog.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    UserRepository userRepository;

    /**
     * 站点是否已经初始化
     *
     * @return
     */
    @Override
    public boolean isInited() {
        try {
            List<User> admins=userRepository.findAllByUserRole(UserRoles.ADMIN_ROLE);
            return admins!=null&&admins.size()>0;
        } catch (final Exception e) {
//            todo LOGGER.log(Level.WARN, "Solo has not been initialized");
            return false;
        }
    }
}
