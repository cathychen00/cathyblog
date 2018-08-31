package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.repository.UserRepository;
import com.cathy.cathyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    RedisTemplate redisTemplate;
    @Override
    public User getAdmin() {
//        String cacheKey=CacheKey.ADMIN;
//        User admin=(User)redisTemplate.opsForValue().get(cacheKey);
//        if(admin!=null){
//            return admin;
//        }
        List<User> admins=userRepository.findAllByUserRole(UserRoles.ADMIN_ROLE);
        if(admins!=null&&admins.size()>0){
            User admin= admins.get(0);
//            redisTemplate.opsForValue().set(cacheKey,admin,1,TimeUnit.HOURS);
            return admin;
        }
        return null;
    }

    @Override
    public void save(User admin) {
        userRepository.save(admin);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users=userRepository.findAllByUserEmail(email);
        if(users==null||users.isEmpty()){
            return null;
        }
        return users.get(0);
    }
}
